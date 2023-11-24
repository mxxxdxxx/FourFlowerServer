package controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import service.TestService;

import java.util.List;

@Controller
public class UserController {
    /**
     * When Spring is launched, this member controller object is created and placed in the Spring container, and Spring manages it.
     * Register with the container when managed by Spring
     * The method must be changed to receive and write from the container.
     * Register with the container instead of using new
     * Problems occur when other controllers use member services
     * There is no need to create multiple objects; you must create one so that it can be used publicly.
     */
    private final TestService userService;

    /**
     * Connects member services in the member container
     * Autowired: Import member services from Spring container
     */

    @Autowired
    public UserController(TestService userService) {
        this.userService = userService;  // Corrected the variable name here
    }

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(UserForm form) {

        /**
         * The value entered in the form is saved in the private name field of MemberForm through setName.
         */
        User user = new User();
        user.setName(form.getName());

        // System.out.println("member = " + member.getName());

        //pass member
        userService.join(user);

        // Since you have signed up, send it to the home screen
        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        /**
         * findMembers(): Can get all members
         * addAttribute(): Pass it to the view template
         */
        List<User> members = userService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}