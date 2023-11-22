package controller;

import entity.User;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import service.TestService;

import java.util.List;

@RestController
@AllArgsConstructor
public class TestController {

    private final TestService testService;

    @GetMapping(value = "api/test")
    public String test() {
        String json = "{name : 'icecream'}";
        return json;
    }

    @GetMapping(value = "/api/user")
    public List<User> getUserList() {
        return testService.getUserList();
    }

    /**
     * @GetMapping(value = "/api/user/{user_Id}")
     *     public String getUser(@PathVariable Long userId) {
     *         return testService.getUser(user_id);
     *     }
     */
}