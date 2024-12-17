package com.example.truyen.Controller;

import com.example.truyen.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserController {

//    @Autowired
//    private UserService userService;

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("user", new User());
        return "form/login";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "form/register";
    }

//    @PostMapping("/register")
//    public String registerUser(User user, Model model) {
//        userService.saveUser(user);
//        model.addAttribute("message", "Đăng ký thành công!");
//        return "form/login";
//    }
}
