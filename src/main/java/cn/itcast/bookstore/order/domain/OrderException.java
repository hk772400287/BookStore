package cn.itcast.bookstore.order.domain;

public class OrderException extends Exception{
    public OrderException() {
        super();
    }

    public OrderException(String message) {
        super(message);
    }
}
