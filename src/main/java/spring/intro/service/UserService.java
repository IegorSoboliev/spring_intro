package spring.intro.service;

import spring.intro.model.User;

import java.util.List;

public interface UserService {

    void add(User user);

    List<User> listUsers();

}
