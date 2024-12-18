package com.example.truyen.Controller;

import com.example.truyen.Entity.AuthRequest;
import com.example.truyen.Entity.User;
import com.example.truyen.Service.JwtService;
import com.example.truyen.Service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class AuthController {
    @Autowired
    private UserService service;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/login")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new AuthRequest());
        return "form/login";
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new User());
        return "form/register";
    }

    @GetMapping("/auth/welcome")
    @ResponseBody
    public String welcome() {
        return "Welcome this endpoint is not secure";
    }

    @PostMapping("/register")
    public String addNewUser(@ModelAttribute("user") User userInfo) {
        String message = service.addUser(userInfo);
        return "redirect:/login";
    }

    public void setCookie(HttpServletResponse response, @RequestParam String value) {
        Cookie cookie = new Cookie("token", value);
        cookie.setMaxAge(60 * 60 * 24 * 7);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    @PostMapping("/login")
    public String authenticateAndGetToken(@ModelAttribute("user") AuthRequest authRequest, HttpServletResponse response) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
        );
        if (authentication.isAuthenticated()) {
            String token = jwtService.generateToken(authRequest.getUsername());
            setCookie(response, token);
            System.out.print("success");
            return "redirect:/";
        } else {
            throw new UsernameNotFoundException("Invalid user request!");
        }
    }

    @GetMapping("/user/userProfile")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @ResponseBody
    public String userProfile() {
        return "Welcome to User Profile";
    }

    @GetMapping("/admin/adminProfile")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @ResponseBody
    public String adminProfile() {
        return "Welcome to Admin Profile";
    }
}