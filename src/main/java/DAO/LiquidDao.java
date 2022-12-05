package DAO;

import entity.Base;
import entity.Liquid;
import entity.LiquidLine;
import entity.LiquidTaste;
import entity.Manufacturer;
import entity.NicConcentration;
import entity.NicType;
import entity.Order;
import entity.OriginCountry;
import exceptiom.DaoException;
import lombok.NoArgsConstructor;
import util.ConnectionManager;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor
public class LiquidDao implements Dao<Long, Liquid> {
    private static final LiquidDao INSTANCE = new LiquidDao();

    private static final String DELETE_SQL = """
            DELETE FROM "liquid"
            WHERE id = ?
            """;

    private static final String SAVE_SQL = """
            INSERT INTO "liquid" (manufacturer_id,
                                liquid_line_id,
                                liquid_taste_id,
                                description,
                                nicotine_type_id,
                                nicotine_concentration_id,
                                base_id,
                                country_id,
                                price,
                                stock)
            VALUES (?,?,?,?,?,?,?,?,?,?);
            """;

    private static final String UPDATE_SQL = """
            UPDATE "liquid"
            SET
            manufacturer_id = ? ,
            liquid_line_id = ? ,
            liquid_taste_id = ? ,
            description = ? ,
            nicotine_type_id = ? ,
            nicotine_concentration_id = ? ,
            base_id = ? ,
            country_id = ? ,
            price = ? ,
            stock = ?
            WHERE id = ?
            """;


    /*переписать*/
    private static final String FIND_ALL_SQL = """  
        SELECT l.id,
                m.manufacturer_name,
                ll.liquid_line,
                lt.type_of_taste,
                l.description,
                nt.nicotine_type,
                nc.concentration,
                b.pg_vg_ratio,
                oc.country,
                l.price,
                l.stock
        from liquid l
                 join manufacturer m on m.id = l.manufacturer_id
                 join liquid_line ll on ll.id = l.liquid_line_id
                 join liquid_taste lt on lt.id = l.liquid_taste_id
                 join nicotine_type nt on nt.id = l.nicotine_type_id
                 join nicotine_concentration nc on nc.id = l.nicotine_concentration_id
                 join base b on b.id = l.base_id
                 join origin_country oc on oc.id = l.country_id
            """;

    private static final String FIND_BY_ID_SQL = FIND_ALL_SQL + """
            WHERE l.id = ?
            """;

    public static LiquidDao getInstance() {
        return INSTANCE;
    }
    private final ManufacturerDao manufacturerDao = ManufacturerDao.getInstance();
    private final LiquidLineDao liquidLineDao = LiquidLineDao.getInstance();
    private final LiquidTasteDao liquidTasteDao = LiquidTasteDao.getInstance();
    private final NicTypeDao nicTypeDao = NicTypeDao.getInstance();
    private final NicConcentrationDao nicConcentrationDao = NicConcentrationDao.getInstance();
    private final BaseDao baseDao = BaseDao.getInstance();
    private final OriginCountryDao originCountryDao = OriginCountryDao.getInstance();

    @Override
    public boolean delete(Long id) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(DELETE_SQL)) {
            preparedStatement.setLong(1, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Liquid save(Liquid liquid) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, liquid.getManufacturer().getId());
            preparedStatement.setLong(2, liquid.getLiquidLine().getId());
            preparedStatement.setLong(3, liquid.getLiquidTaste().getId());
            preparedStatement.setString(4, liquid.getDescription());
            preparedStatement.setLong(5, liquid.getNicType().getId());
            preparedStatement.setLong(6, liquid.getNicConcentration().getId());
            preparedStatement.setLong(7, liquid.getBase().getId());
            preparedStatement.setLong(8, liquid.getOriginCountry().getId());
            preparedStatement.setBigDecimal(9, liquid.getPrice());
            preparedStatement.setLong(10, liquid.getStock());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                liquid.setId(generatedKeys.getLong("id"));
            }
            return liquid;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void update(Liquid liquid) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            setLiquidFields(liquid,preparedStatement);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<Liquid> findById(Long id) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            preparedStatement.setLong(1, id);
            var resultSet = preparedStatement.executeQuery();
            Liquid liquid = null;
            if (resultSet.next()) {
                liquid = buildLiquid(resultSet);
            }
            return Optional.ofNullable(liquid);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private Liquid buildLiquid(ResultSet resultSet) {
        try {
            return new Liquid(
                    resultSet.getLong("id"),
                    manufacturerDao.findById(resultSet.getLong("manufacturer_name")).orElse(null),
                    liquidLineDao.findById(resultSet.getLong       ("liquid_line")).orElse(null),
                    liquidTasteDao.findById(resultSet.getLong       ( "type_of_taste")).orElse(null),
                    resultSet.getString     ("description"),
                    nicTypeDao.findById(resultSet.getLong       ("nicotine_type")).orElse(null),
                    nicConcentrationDao.findById(resultSet.getLong       ("concentration")).orElse(null),
                    baseDao.findById(resultSet.getLong       ("pg_vg_ratio")).orElse(null),
                    originCountryDao.findById(resultSet.getLong       ("country")).orElse(null),
                    (resultSet.getBigDecimal ("price")),
                    resultSet.getLong       ("stock")
            );
        } catch (SQLException e){
            throw new DaoException(e);
        }
    }

    @Override
    public List<Liquid> findAll() {
        return null;
    }


    private void setLiquidFields(Liquid liquid, PreparedStatement preparedStatement) throws SQLException {


    }
}
