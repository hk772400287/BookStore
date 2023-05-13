package cn.itcast.bookstore.order.service;

import cn.itcast.bookstore.order.dao.OrderDao;
import cn.itcast.bookstore.order.domain.Order;
import cn.itcast.bookstore.order.domain.OrderException;
import cn.itcast.jdbc.JdbcUtils;

import java.sql.SQLException;
import java.util.List;

public class OrderService {
    private final OrderDao orderDao = new OrderDao();

    public void add(Order order) {
        try {
            JdbcUtils.beginTransaction();
            orderDao.addOrder(order);
            orderDao.addOrderItemList(order.getOrderItemList());
            JdbcUtils.commitTransaction();
        } catch (SQLException e) {
            try {
                JdbcUtils.rollbackTransaction();
            } catch (SQLException ee) {
                throw new RuntimeException(ee);
            }
        }
    }

    public List<Order> myOrders(String uid) {
        return orderDao.findByUid(uid);
    }

    public Order loadOrder(String oid) {
        return orderDao.findByOid(oid);
    }

    public void confirm(String oid) throws OrderException {
        int state = orderDao.getStateByOid(oid);
        if (state != 3) {
            throw new OrderException("确认收货失败！");
        }
        orderDao.updateState(oid, 4);
    }

    public void pay(String oid) {
        if (orderDao.getStateByOid(oid) == 1) {
            orderDao.updateState(oid, 2);
        }
    }

    public void addAddress(String address, String oid) {
        orderDao.addAddress(address, oid);
    }

    public List<Order> loadOrdersByState(int state) {
        return orderDao.findByState(state);
    }

    public List<Order> loadAllOrders() {
        return orderDao.findAll();
    }
}
