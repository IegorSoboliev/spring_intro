package spring.intro.service.impl;

import spring.intro.dao.UserDao;
import spring.intro.model.User;
import spring.intro.service.UserService;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void add(User user) {
        userDao.add(user);
    }

    @Override
    public List<User> listUsers() {
        return userDao.listUsers();
    }
}
