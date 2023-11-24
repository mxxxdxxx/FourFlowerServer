package controller;

import entity.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.TestService;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/api")
public class TestController {

    private final TestService testService;

    @GetMapping("/test")
    public String test() {
        return "test";
    }

    @GetMapping("/user")
    public String getUserList(Model model) {
        List<User> userList = testService.getUserList();
        model.addAttribute("users", userList);
        return "user-list";
    }

    @GetMapping("/user/{userId}")
    public String getUser(@PathVariable Long userId, Model model) {
        User user = testService.getUser(userId);
        model.addAttribute("user", user);
        return "user-detail";
    }
}
