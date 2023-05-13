package cn.itcast.bookstore.category.dao;

import cn.itcast.bookstore.category.domain.Category;
import cn.itcast.jdbc.TxQueryRunner;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class CategoryDao {
    private QueryRunner qr = new TxQueryRunner();

    public List<Category> showAll() {
        String sql = "SELECT * FROM category";
        try {
            return qr.query(sql, new BeanListHandler<>(Category.class));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void add(Category c) {
        String sql = "INSERT INTO category VALUES (?,?)";
        try {
            qr.update(sql, c.getCid(), c.getCname());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void delete(String cid) {
        String sql = "DELETE from category WHERE cid=?";
        try {
            qr.update(sql, cid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Category getByCid(String cid) {
        String sql = "SELECT * from category WHERE cid=?";
        try {
            return qr.query(sql, new BeanHandler<>(Category.class), cid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void edit(Category editedCategory) {
        String sql = "UPDATE category SET cname=? WHERE cid=?";
        try {
            qr.update(sql, editedCategory.getCname(), editedCategory.getCid());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
