package cn.itcast.bookstore.order.domain;

import cn.itcast.bookstore.user.domain.User;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class Order {
    private String oid;
    private LocalDateTime ordertime;
    private double total;
    private int state;
    private User owner;
    private String address;
    private List<OrderItem> orderItemList;

    public List<OrderItem> getOrderItemList() {
        return orderItemList;
    }

    public void setOrderItemList(List<OrderItem> orderItemList) {
        this.orderItemList = orderItemList;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public LocalDateTime getOrdertime() {
        return ordertime;
    }

    public void setOrdertime(LocalDateTime ordertime) {
        this.ordertime = ordertime;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
