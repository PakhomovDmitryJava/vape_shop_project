package DAO;

import entity.User;
import exceptiom.DaoException;
import util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDao implements Dao<Long, User> {
    private static final UserDao INSTANCE = new UserDao();

    private static final String DELETE_SQL = """
            DELETE FROM "user"
            WHERE id = ?
            """;

    private static final String SAVE_SQL = """
            INSERT INTO "user" (first_name, last_name, date_of_birth, address, email, mobile_phone, "password")
            VALUES (?, ?, ?, ?, ?, ?, ?);
            """;

    private static final String UPDATE_SQL = """
            UPDATE "user"
            SET
            first_name = ? ,
            last_name = ? ,
            date_of_birth = ? ,
            address = ? ,
            email = ? ,
            mobile_phone = ? ,
            "password" = ?
            WHERE id = ?
            """;

    private static final String FIND_ALL_SQL = """
            SELECT u.id,
                   u.first_name ,
                   u.last_name    ,
                   u.date_of_birth,
                   u.address      ,
                   u.email        ,
                   u.mobile_phone ,
                   u."password"   ,
                   o.id,
                   o.user_id,
                   o.date_of_payment,
                   o.is_paid,
                   o.date_of_order
            FROM "user" u
                    left join "order" o on u.id = o.user_id
                        """;

    private static final String FIND_BY_ID_SQL = FIND_ALL_SQL + """
            WHERE u.id = ?
            """;

    private final OrderDao orderDao = OrderDao.getInstance();

    public UserDao() {
    }

    public static UserDao getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean delete(Long id) {
        try (var connection = ConnectionManager.get(); var preparedStatement = connection.prepareStatement(DELETE_SQL)) {
            preparedStatement.setLong(1, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public User save(User user) {
        try (Connection connection = ConnectionManager.get(); PreparedStatement preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            setUserFields(user, preparedStatement);
            preparedStatement.executeUpdate();
            var generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                user.setId(generatedKeys.getLong("id"));
            }
            return user;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }


    @Override
    public List<User> findAll() {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_ALL_SQL)) {
            var resultSet = preparedStatement.executeQuery();
            List<User> users = new ArrayList<>();
            while (resultSet.next()) {
                users.add(buildUser(resultSet));
            }
            return users;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void update(User user) {
        try (var connetcion = ConnectionManager.get();
             var preparedStatement = connetcion.prepareStatement(UPDATE_SQL)) {
            setUserFields(user, preparedStatement);
            preparedStatement.setLong(8, user.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private void setUserFields(User user, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, user.getFirstName());
        preparedStatement.setString(2, user.getLastName());
        preparedStatement.setTimestamp(3, Timestamp.valueOf(user.getDateOfBirth()));
        preparedStatement.setString(4, user.getAddress());
        preparedStatement.setString(5, user.getEmail());
        preparedStatement.setString(6, user.getMobilePhone());
        preparedStatement.setString(7, user.getPassword());
    }

    @Override
    public Optional<User> findById(Long id) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            preparedStatement.setLong(1, id);
            var resultSet = preparedStatement.executeQuery();
            User user = null;
            if (resultSet.next()) {
                user = buildUser(resultSet);
            }
            return Optional.ofNullable(user);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private User buildUser(ResultSet resultSet) {
        try {
            return new User(resultSet.getLong("id"),
                    resultSet.getString("first_name"),
                    resultSet.getString("last_name"),
                    resultSet.getTimestamp("date_of_birth").toLocalDateTime(),
                    resultSet.getString("address"),
                    resultSet.getString("email"),
                    resultSet.getString("mobile_phone"),
                    resultSet.getString("password"));
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

}
