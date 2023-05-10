package cn.itcast.bookstore.user.service;

import cn.itcast.bookstore.user.dao.UserDao;
import cn.itcast.bookstore.user.domain.User;

public class UserService {
    private UserDao userDao = new UserDao();

    public void userRegister(User user) throws UserException {
        User found = userDao.findByUsername(user.getUsername());
        if (found != null) {
            throw new UserException("用户名已被注册");
        }
        found = userDao.findByEmail(user.getEmail());
        if (found != null) {
            throw new UserException("email已被注册");
        }
        userDao.addUser(user);
    }

    public User userLogin(User user) throws UserException {
        User found = userDao.findByUsername(user.getUsername());
        if (found == null) {
            throw new UserException("用户名不存在！");
        }
        if (!found.getPassword().equals(user.getPassword())) {
            throw new UserException("密码错误！");
        }
        return found;
    }
}
