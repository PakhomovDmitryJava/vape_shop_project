import DAO.OrderDao;
import DAO.UserDao;
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

//        var userDao = UserDao.getInstance();
//        var user = UserDao.getInstance().findById(1L);
//        System.out.println(user);
//
//        user.ifPresent(user1 -> {
//            user1.setFirstName("Dima");
//            user1.setLastName("Ivanov");
//            userDao.update(user1);
//        });

//        saveOrderTest();

//        deleteOrderTest(9L);

        var orderDao = OrderDao.getInstance();
        var mayBeOrder = OrderDao.getInstance().findById(13L);
        System.out.println(mayBeOrder);

        mayBeOrder.ifPresent(o -> {
            o.setPaid(false);
            o.setPaymentDate(LocalDateTime.now().minusDays(15));
            orderDao.update(o);
        });
//
        System.out.println(mayBeOrder);

    }

    private static void saveUserTest() {
        var userDao = UserDao.getInstance();
        var user = new User();
        user.setFirstName("Petr");
        user.setLastName("Petr");
        user.setDateOfBirth(LocalDateTime.of(1991, 2, 2, 0,0));
        user.setAddress("Petr");
        user.setEmail("Petr@af.com");
        user.setMobilePhone(String.valueOf(895165455547L));
        user.setPassword("Petr");
        var saved = userDao.save(user);
        System.out.println(saved);
    }

        private static void deleteUserTest(Long id) {
        var userDao = UserDao.getInstance();
        var deleteResult = userDao.delete(id);
        System.out.println(deleteResult);
    }
    private static void findAllUsersTest() {
        var users = UserDao.getInstance().findAll();
        for (User user : users) {
            System.out.println(user);
        }
    }

    private static void deleteOrderTest(Long id) {
        var orderDao = OrderDao.getInstance();
        var deleteResult = orderDao.delete(6L);
        System.out.println(deleteResult);
    }

    private static void saveOrderTest()   {
        var orderDao = OrderDao.getInstance();
        var order = new Order();
        order.setUser(UserDao.getInstance().findById(2L).get());
        order.setOrderDate(LocalDateTime.of(2022,11,11,0,0 ));
        order.setPaid(false);
        order.setPaymentDate(LocalDateTime.of(0,1,1,0,0));
        var saved = orderDao.save(order);
        System.out.println(saved);
    }
}

