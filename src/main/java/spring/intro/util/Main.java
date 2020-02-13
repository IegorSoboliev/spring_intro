package spring.intro.util;

import spring.intro.config.AppConfig;
import spring.intro.model.User;
import spring.intro.service.UserService;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        User user = new User();
        user.setEmail("sofia@yahoo.com");
        user.setPassword("1");
        userService.add(user);

        List<User> users = userService.listUsers();
        System.out.println(users);
    }
}
