package cn.itcast.bookstore.user.service;

import cn.itcast.bookstore.user.dao.UserDao;
import cn.itcast.bookstore.user.domain.User;

public class UserService {
    private UserDao userDao = new UserDao();

    public void userRegister(User user) throws UserException {
        User found = userDao.findByUsername(user.getUsername());
        if (found != null) {
            throw new UserException("Username already exists");
        }
        found = userDao.findByEmail(user.getEmail());
        if (found != null) {
            throw new UserException("Email already registered");
        }
        userDao.addUser(user);
    }

    public User userLogin(User user) throws UserException {
        User found = userDao.findByUsername(user.getUsername());
        if (found == null) {
            throw new UserException("Username does not exist！");
        }
        if (!found.getPassword().equals(user.getPassword())) {
            throw new UserException("Incorrect password！");
        }
        return found;
    }
}
