import DAO.BaseDao;
import DAO.LiquidDao;
import DAO.LiquidLineDao;
import DAO.LiquidTasteDao;
import DAO.ManufacturerDao;
import DAO.NicConcentrationDao;
import DAO.NicTypeDao;
import DAO.OrderDao;
import DAO.OriginCountryDao;
import DAO.UserDao;
import entity.Base;
import entity.Liquid;
import entity.LiquidLine;
import entity.LiquidTaste;
import entity.Manufacturer;
import entity.NicConcentration;
import entity.NicType;
import entity.Order;
import entity.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

public class DaoRunner {
    public static void main(String[] args) {
//        saveUserTest();

//        deleteUserTest(4L);

//      findAllUsersTest();

//        updateUserTest();

//        saveOrderTest();

//        deleteOrderTest(9L);
//
//            updateOrderTest();
//

//        findAllOrdersTest();

//        saveOrderTest();


        //        findAllLiquidLinesTest();
        findByIdLiquidTest(1L);
//        deleteLiquidTest(1L);
//        saveLiquidTest();
//        deleteLiquidLineTest(12L);

//        findAllLiquidLinesTest();
//        findByIdLiquidLineTest(1L);
        //        updateLiquidLineTest();
////        saveLiquidLineTest();
//        deleteLiquidLineTest(1L);



//        findByIdBaseTest(1L);
//        findAllBaseTest();
//        updateBaseTest();
//        saveBaseTest();
//        deleteBaseTest();

//        findByIdBLiquidTasteTest(1L);
//        findAllLiquidTastesTest();
//        updateLiquidTasteTest();
//        saveLiquidTasteTest();
//        deleteLiquidTasteTest();

//        findByIdManufacturerTest(1L);
//        findAllManufacturerTest();
//        updateManufacturerTest();
//        saveManufacturerTest();
//        deleteManufacturerTest();

//        findByIdNicConcentrationTest(1L);
//        findAllNicConcentrationTest();
//        updateNicConcentrationTest();
//        saveNicConcentrationTest();
//        deleteNicConcentrationTest();

//        findByIdNicTypeTest(1L);
//        findAllNicTypeTest();
//        saveNicTypeTest();
//        updateNicTypeTest();
//        deleteNicTypeTest();
    }


    private static void findByIdLiquidLineTest(Long id) {
        var liquidLineDao = LiquidLineDao.getInstance();
        var mayBeLiquidLine = liquidLineDao.findById(1L);
        System.out.println(mayBeLiquidLine);
    }

    private static void findByIdLiquidTasteTest(Long id) {
        var liquidTasteDao = LiquidTasteDao.getInstance();
        var mayBeLiquidTaste = liquidTasteDao.findById(1L);
        System.out.println(mayBeLiquidTaste);
    }

    private static void findByIdBaseTest(Long id) {
        var baseDao = BaseDao.getInstance();
        var mayBeBase = baseDao.findById(1L);
        System.out.println(mayBeBase);
    }

    private static void findByIdManufacturerTest(Long id) {
        var manufacturerDao = ManufacturerDao.getInstance();
        var mayBeManufacturer = manufacturerDao.findById(1L);
        System.out.println(mayBeManufacturer);
    }

    private static void findByIdNicConcentrationTest(Long id) {
        var nicConcentrationDao = NicConcentrationDao.getInstance();
        var mayNicConcentrationDao = nicConcentrationDao.findById(1L);
        System.out.println(mayNicConcentrationDao);
    }

    private static void findByIdNicTypeTest(Long id) {
        var nicTypeDao = NicTypeDao.getInstance();
        var mayBeNicType = nicTypeDao.findById(1L);
        System.out.println(mayBeNicType);
    }

    private static void findByIdLiquidTest(Long id) {
        var instance = LiquidDao.getInstance();
        var liquid = instance.findById(id);
    }

    private static void updateUserTest() {
        var userDao = UserDao.getInstance();
        var mayBeUser = userDao.findById(5L);
        System.out.println(mayBeUser);

        mayBeUser.ifPresent(user -> {
            user.setFirstName("Petr");
            user.setLastName("Petrov");
            userDao.update(user);
        });
    }

    private static void updateOrderTest() {
        var orderDao = OrderDao.getInstance();
        var mayBeOrder = orderDao.findById(2L);
        System.out.println(mayBeOrder);

        mayBeOrder.ifPresent(order -> {
            order.setPaid(true);
            order.setOrderDate(LocalDateTime.now().minusDays(300));
            order.setPaymentDate(LocalDateTime.now().plusDays(300));
            orderDao.update(order);
        });
        System.out.println(orderDao.findById(2L));
    }

    private static void updateLiquidLineTest() {
        var liquidLineDao = LiquidLineDao.getInstance();
        var mayBeLiquidLIne = liquidLineDao.findById(2L);
        System.out.println(mayBeLiquidLIne);

        mayBeLiquidLIne.ifPresent(liquidLine -> {
            liquidLine.setLiquidLine("Boshki Boshki");
            liquidLineDao.update(liquidLine);
        });
        System.out.println(liquidLineDao.findById(2L));
    }

    public static void updateBaseTest() {
        var baseDao = BaseDao.getInstance();
        var mayBeBase = baseDao.findById(1L);
        System.out.println(mayBeBase);

        mayBeBase.ifPresent(liquidLine -> {
            liquidLine.setPrVgRatio("50/50");
            baseDao.update(liquidLine);
        });
        System.out.println(baseDao.findById(2L));
    }

    private static void updateLiquidTasteTest() {
        var liquidTasteDao = LiquidTasteDao.getInstance();
        var mayBeLiquidTaste = liquidTasteDao.findById(1L);
        System.out.println(mayBeLiquidTaste);
        mayBeLiquidTaste.ifPresent(liquidTaste -> {
            liquidTaste.setLiquidTaste("Shoria");
            liquidTasteDao.update(liquidTaste);
        });
        System.out.println(liquidTasteDao.findById(1L));
    }

    private static void updateManufacturerTest() {
        var manufacturerDao = ManufacturerDao.getInstance();
        var mayBeManufacturer = manufacturerDao.findById(1L);
        System.out.println(mayBeManufacturer);
        mayBeManufacturer.ifPresent(manufacturer -> {
            manufacturer.setManufacturer("HASKUIIII");
            manufacturerDao.update(manufacturer);
        });
        System.out.println(manufacturerDao.findById(1L));
    }

    private static void updateNicConcentrationTest() {
        var nicConcentrationDao = NicConcentrationDao.getInstance();
        var mayBeNicConcentration = nicConcentrationDao.findById(8L);
        System.out.println(mayBeNicConcentration);
        mayBeNicConcentration.ifPresent(concentration -> {
            concentration.setNicConcentration("20 SUPER STRONG");
            nicConcentrationDao.update(concentration);
        });
        System.out.println(nicConcentrationDao.findById(8L));
    }

    private static void updateNicTypeTest() {
        var niceType = NicTypeDao.getInstance();
        var mayBeNicType = niceType.findById(5L);
        System.out.println(mayBeNicType);
        mayBeNicType.ifPresent(nicType -> {
            nicType.setNicType("SUPER DUPER NEW");
            niceType.update(nicType);
        });
        System.out.println(niceType.findById(5L));
    }

    private static void saveUserTest() {
        var userDao = UserDao.getInstance();
        var user = new User();
        user.setFirstName("Petr");
        user.setLastName("Petr");
        user.setDateOfBirth(LocalDateTime.of(1991, 2, 2, 0, 0));
        user.setAddress("Petr");
        user.setEmail("Petr@af.com");
        user.setMobilePhone(String.valueOf(895165455547L));
        user.setPassword("Petr");
        var savedUser = userDao.save(user);
        System.out.println(savedUser);
    }

    private static void saveOrderTest() {
        var orderDao = OrderDao.getInstance();
        var order = new Order();
        UserDao.getInstance().findById(2L).ifPresent(order::setUser);
        order.setOrderDate(LocalDateTime.of(2022, 12, 12, 0, 0));
        order.setPaid(false);
        order.setPaymentDate(LocalDateTime.of(0, 1, 1, 0, 0));
        var savedOrder = orderDao.save(order);
        System.out.println(savedOrder);
    }

    private static void saveLiquidLineTest() {
        var liquidLineDao = LiquidLineDao.getInstance();
        var liquidLine = new LiquidLine();
        liquidLine.setLiquidLine("Sweet Sour ");
        var savedLiquidLine = liquidLineDao.save(liquidLine);
        System.out.println(savedLiquidLine);
    }

    private static void saveLiquidTasteTest() {
        var liquidTasteDao = LiquidTasteDao.getInstance();
        var liquidTaste = new LiquidTaste();
        liquidTaste.setLiquidTaste("Forest home");
        var savedLiquidTaste = liquidTasteDao.save(liquidTaste);
        System.out.println(savedLiquidTaste);
    }

    private static void saveBaseTest() {
        var baseDao = BaseDao.getInstance();
        var base = new Base();
        base.setPrVgRatio("65/35");
        var savedBase = baseDao.save(base);
        System.out.println(savedBase);
    }

    private static void saveManufacturerTest() {
        var manufacturerDao = ManufacturerDao.getInstance();
        var manufacturer = new Manufacturer();
        manufacturer.setManufacturer("BOSHKIIIOO");
        var savedManufacturer = manufacturerDao.save(manufacturer);
        System.out.println(savedManufacturer);
    }

    private static void saveNicConcentrationTest() {
        var nicConcentrationDao = NicConcentrationDao.getInstance();
        var nicConcentration = new NicConcentration();
        nicConcentration.setNicConcentration("50");
        var savedNicConcentration = nicConcentrationDao.save(nicConcentration);
        System.out.println(savedNicConcentration);
    }

    private static void saveNicTypeTest() {
        var nicTypeDao = NicTypeDao.getInstance();
        var nicType = new NicType();
        nicType.setNicType("NEW TYPE OF NIC!");
        var savedNicType = nicTypeDao.save(nicType);
        System.out.println(savedNicType);
    }

    private static void saveLiquidTest() {
        var liquidDao = LiquidDao.getInstance();
        var newLiquid = new Liquid();
       ManufacturerDao.getInstance().findById(1L).ifPresent(newLiquid::setManufacturer);
       LiquidLineDao.getInstance().findById(2L).ifPresent(newLiquid::setLiquidLine);
       LiquidTasteDao.getInstance().findById(3L).ifPresent(newLiquid::setLiquidTaste);
       newLiquid.setDescription("This is the best liquid!");
       NicTypeDao.getInstance().findById(1L).ifPresent(newLiquid::setNicType);
       NicConcentrationDao.getInstance().findById(6L).ifPresent(newLiquid::setNicConcentration);
       BaseDao.getInstance().findById(1L).ifPresent(newLiquid::setBase);
       OriginCountryDao.getInstance().findById(1L).ifPresent(newLiquid::setOriginCountry);
       newLiquid.setPrice(BigDecimal.valueOf(666));
       newLiquid.setStock(333L);
        var savedLiquid = liquidDao.save(newLiquid);
        System.out.println(savedLiquid);
    }

    private static void deleteUserTest(Long id) {
        var userDao = UserDao.getInstance();
        var deletedUser = userDao.delete(id);
        System.out.println(deletedUser);
    }

    private static void deleteLiquidTest(Long id) {
        var liquidDao = LiquidDao.getInstance();
        var deletedLiquid = liquidDao.delete(1L);
        System.out.println(deletedLiquid);
    }

    private static void deleteBaseTest() {
        var baseDao = BaseDao.getInstance();
        var deletedBase = baseDao.delete(6L);
        System.out.println(deletedBase);
    }

    private static void deleteLiquidTasteTest() {
        var liquidTasteDao = LiquidTasteDao.getInstance();
        var deletedTaste = liquidTasteDao.delete(11L);
        System.out.println(deletedTaste);
    }

    private static void deleteNicConcentrationTest() {
        var nicConcentrationDao = NicConcentrationDao.getInstance();
        var deletedNicConcentration = nicConcentrationDao.delete(8L);
        System.out.println(deletedNicConcentration);
    }

    private static void findAllOrdersTest() {
        var orders = OrderDao.getInstance().findAll();
        for (Order order : orders) {
            System.out.println(order);
        }
    }

    private static void findAllLiquidLinesTest() {
        var liquidLines = LiquidLineDao.getInstance().findAll();
        for (LiquidLine liquidLine : liquidLines) {
            System.out.println(liquidLine);
        }
    }


    private static void findAllBaseTest() {
        var baseLines = BaseDao.getInstance().findAll();
        for (Base baseLine : baseLines) {
            System.out.println(baseLine);
        }
    }

    private static void findAllLiquidTastesTest() {
        var liquidTastes = LiquidTasteDao.getInstance().findAll();
        for (LiquidTaste liquidTaste : liquidTastes) {
            System.out.println(liquidTaste);
        }
    }

    private static void findAllManufacturerTest() {
        var manufacturers = ManufacturerDao.getInstance().findAll();
        for (Manufacturer manufacturer : manufacturers) {
            System.out.println(manufacturer);
        }
    }

    private static void findAllNicConcentrationTest() {
        var nicConcentrationDao = NicConcentrationDao.getInstance().findAll();
        for (NicConcentration nicConcentration : nicConcentrationDao) {
            System.out.println(nicConcentration);
        }
    }

    private static void findAllNicTypeTest() {
        var nicTypes = NicTypeDao.getInstance().findAll();
        for (NicType nicType : nicTypes) {
            System.out.println(nicType);
        }
    }


    private static void deleteOrderTest(Long id) {
        var orderDao = OrderDao.getInstance();
        var deleteResult = orderDao.delete(6L);
        System.out.println(deleteResult);
    }

    private static void deleteLiquidLineTest(Long id) {
        var liquidLineDao = LiquidLineDao.getInstance();
        var deleteResult = liquidLineDao.delete(12L);
        System.out.println(deleteResult);
    }

    private static void deleteManufacturerTest() {
        var manufacturerDao = ManufacturerDao.getInstance();
        var deletedManufacturer = manufacturerDao.delete(16L);
        System.out.println(deletedManufacturer);
    }

    private static void deleteNicTypeTest() {
        var nicTypeDao = NicTypeDao.getInstance();
        var deletedNicType = nicTypeDao.delete(7L);
        System.out.println(deletedNicType);
    }
}

