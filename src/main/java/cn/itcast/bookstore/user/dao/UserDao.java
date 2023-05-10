package cn.itcast.bookstore.user.dao;

import cn.itcast.bookstore.user.domain.User;
import cn.itcast.jdbc.TxQueryRunner;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

public class UserDao {
    private QueryRunner qr = new TxQueryRunner();

    public User findByUsername(String username) {
        try {
            String sql = "SELECT * FROM tb_user WHERE username=?";
            return qr.query(sql, new BeanHandler<User>(User.class), username);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public User findByEmail(String email) {
        try {
            String sql = "SELECT * FROM tb_user WHERE email=?";
            return qr.query(sql, new BeanHandler<User>(User.class), email);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addUser(User user) {
        try {
            String sql = "INSERT INTO tb_user VALUES (?,?,?,?,?,?)";
            Object[] params = {user.getUid(), user.getUsername(), user.getPassword(),
                    user.getEmail(), user.getCode(), user.isState()};
            qr.update(sql, params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
