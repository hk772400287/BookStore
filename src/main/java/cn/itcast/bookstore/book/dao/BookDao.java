package cn.itcast.bookstore.book.dao;

import cn.itcast.bookstore.book.domain.Book;
import cn.itcast.bookstore.category.domain.Category;
import cn.itcast.commons.CommonUtils;
import cn.itcast.jdbc.TxQueryRunner;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class BookDao {
    private QueryRunner qr = new TxQueryRunner();

    public List<Book> findAll() {
        String sql = "SELECT * FROM book WHERE del=false";
        try {
            return qr.query(sql, new BeanListHandler<Book>(Book.class));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Book> findByCategory(String cid) {
        String sql = "SELECT * FROM book WHERE cid=? AND del=false";
        try {
            return qr.query(sql, new BeanListHandler<Book>(Book.class), cid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Book findByBid(String bid) {
        String sql = "SELECT * FROM book WHERE bid=?";
        try {
            Map<String, Object> map = qr.query(sql, new MapHandler(), bid);
            Category category = CommonUtils.toBean(map, Category.class);
            Book book = CommonUtils.toBean(map, Book.class);
            book.setCategory(category);
            return book;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int getBookCountByCid(String cid) {
        String sql = "SELECT COUNT(*) FROM book WHERE cid=?";
        try {
            Number n = (Number)qr.query(sql, new ScalarHandler(), cid);
            return n.intValue();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void add(Book book) {
        String sql = "INSERT INTO book VALUES(?,?,?,?,?,?,?)";
        try {
            Object[] params = {book.getBid(), book.getBname(), book.getPrice(),
            book.getAuthor(), book.getImage(), book.getCategory().getCid(), false};
            qr.update(sql, params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(String bid) {
        String sql = "UPDATE book SET del=true WHERE bid=?";
        try {
            qr.update(sql, bid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void edit(Book book) {
        String sql = "UPDATE book SET bname=?, price=?, author=?, image=?, cid=? WHERE bid=?";
        try {
            Object[] params = {book.getBname(), book.getPrice(), book.getAuthor(),
            book.getImage(), book.getCategory().getCid(), book.getBid()};
            qr.update(sql, params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
