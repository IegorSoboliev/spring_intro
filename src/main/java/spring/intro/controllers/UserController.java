package spring.intro.controllers;

import spring.intro.model.User;
import spring.intro.service.UserService;
import spring.intro.util.UserResponseDto;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/inject")
    private String inject() {
        User user = new User();
        user.setEmail("sofia@yahoo.com");
        user.setPassword("1");
        userService.add(user);

        User user1 = new User();
        user1.setEmail("pavlo@yahoo.com");
        user1.setPassword("1");
        userService.add(user1);

        User user2 = new User();
        user2.setEmail("bodya@yahoo.com");
        user2.setPassword("1");
        userService.add(user2);

        User user3 = new User();
        user3.setEmail("serhiy@yahoo.com");
        user3.setPassword("1");
        userService.add(user3);

        return "Data was injected";
    }

    @GetMapping("/{userId}")
    private UserResponseDto get(@RequestParam(name = "userId") Long userId) {
        User user = userService.get(userId);
        return transformToUserResponseDto(user);
    }

    @GetMapping("/")
    private List<UserResponseDto> getAll() {
        return userService.listUsers()
                .stream()
                .map(this::transformToUserResponseDto)
                .collect(Collectors.toList());
    }

    private UserResponseDto transformToUserResponseDto (User user) {
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setEmail(user.getEmail());
        userResponseDto.setPassword(user.getPassword());
        return userResponseDto;
    }
}
