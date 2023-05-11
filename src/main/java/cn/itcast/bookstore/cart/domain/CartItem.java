package cn.itcast.bookstore.cart.domain;

import cn.itcast.bookstore.book.domain.Book;

import java.math.BigDecimal;

public class CartItem {
    private Book book;
    private int count;

    public double getSubTotal() {
        BigDecimal price = new BigDecimal(book.getPrice() + "");
        BigDecimal cnt = new BigDecimal(count + "");
        return price.multiply(cnt).doubleValue();
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
