package services;


import dao.OrderDao;
import models.Order;
import java.util.List;

public class OrderService {

    private OrderDao orderDao = new OrderDao();

    public OrderService() {
    }

    public Order findUser(int id) {
        return orderDao.findById(id);
    }

    public void saveUser(Order user) {
        orderDao.save(user);
    }

    public void deleteUser(Order user) {
        orderDao.delete(user);
    }

    public void updateUser(Order user) {
        orderDao.update(user);
    }

    public List<Order> findAllUsers() {
        return orderDao.findAll();
    }

}