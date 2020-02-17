package spring.intro.service;

import java.util.List;

import spring.intro.model.User;

public interface UserService {

    void add(User user);

    User get(Long userId);

    List<User> listUsers();

}
