package cn.itcast.bookstore.order.dao;

import cn.itcast.bookstore.book.domain.Book;
import cn.itcast.bookstore.order.domain.Order;
import cn.itcast.bookstore.order.domain.OrderItem;
import cn.itcast.commons.CommonUtils;
import cn.itcast.jdbc.TxQueryRunner;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OrderDao {
    private final QueryRunner qr = new TxQueryRunner();

    public void addOrder(Order order) {
        String sql = "INSERT INTO orders VALUES(?, ?, ?, ?, ?, ?)";
        Object[] params = {order.getOid(), order.getOrdertime(), order.getTotal(), order.getState(),
            order.getOwner().getUid(), order.getAddress()};
        try {
            qr.update(sql, params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addOrderItemList(List<OrderItem> orderItemList) {
        String sql = "INSERT INTO orderitem VALUES(?, ?, ?, ?, ?)";
        Object[][] params = new Object[orderItemList.size()][];
        for (int i = 0; i < orderItemList.size(); i++) {
            OrderItem orderItem = orderItemList.get(i);
            params[i] = new Object[]{orderItem.getIid(), orderItem.getCount(), orderItem.getSubtotal(),
             orderItem.getOrder().getOid(), orderItem.getBook().getBid()};
        }
        try {
            qr.batch(sql, params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Order> findByUid(String uid) {
        String sql = "SELECT * FROM orders WHERE uid=?";
        try {
            List<Order> orderList = qr.query(sql, new BeanListHandler<Order>(Order.class), uid);
            for (Order order : orderList) {
                List<OrderItem> orderItemList = getOrderItemList(order);
                order.setOrderItemList(orderItemList);
            }
            return orderList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private List<OrderItem> getOrderItemList(Order order) {
        String sql = "SELECT * FROM orderitem o, book b WHERE o.bid=b.bid AND oid=?";
        try {
            List<Map<String, Object>> mapList = qr.query(sql, new MapListHandler(), order.getOid());
            List<OrderItem> orderItemList = new ArrayList<>();
            for (Map<String, Object> map : mapList) {
                OrderItem orderItem = toOrderItem(map);
                orderItemList.add(orderItem);
            }
            return orderItemList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private OrderItem toOrderItem(Map<String, Object> map) {
        OrderItem orderItem = CommonUtils.toBean(map, OrderItem.class);
        Book book = CommonUtils.toBean(map, Book.class);
        orderItem.setBook(book);
        return orderItem;
    }

    public Order findByOid(String oid) {
        String sql = "SELECT * FROM orders WHERE oid=?";
        try {
            Order order = qr.query(sql, new BeanHandler<>(Order.class), oid);
            order.setOrderItemList(getOrderItemList(order));
            return order;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void updateState(String oid, int newState) {
        String sql = "UPDATE orders SET state=? WHERE oid=?";
        try {
            qr.update(sql, newState, oid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int getStateByOid(String oid) {
        String sql = "SELECT state FROM orders WHERE oid=?";
        try {
            return qr.query(sql, new ScalarHandler<>(), oid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addAddress(String address, String oid) {
        String sql = "UPDATE orders SET address=? WHERE oid=?";
        try {
            qr.update(sql, address, oid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Order> findByState(int state) {
        String sql = "SELECT * FROM orders WHERE state=?";
        try {
            List<Order> orderList = qr.query(sql, new BeanListHandler<>(Order.class), state);
            for (Order o : orderList) {
                o.setOrderItemList(getOrderItemList(o));
            }
            return orderList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Order> findAll() {
        String sql = "SELECT * FROM orders";
        try {
            List<Order> orderList = qr.query(sql, new BeanListHandler<>(Order.class));
            for (Order o : orderList) {
                o.setOrderItemList(getOrderItemList(o));
            }
            return orderList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
