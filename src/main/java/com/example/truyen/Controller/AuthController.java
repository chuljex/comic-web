package com.example.truyen.Controller;

import com.example.truyen.DTO.RegisterFormDto;
import com.example.truyen.Entity.AuthRequest;
import com.example.truyen.Entity.User;
import com.example.truyen.Service.JwtService;
import com.example.truyen.Service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
    public String showRegistrationForm(HttpServletRequest request, Model model) {
        String token = getCookie(request);
        if(token != null) {
            return "redirect:/";
        }
        model.addAttribute("user", new AuthRequest());
        return "auth/login";
    }

    @GetMapping("/register")
    public String showRegisterForm(HttpServletRequest request, Model model) {
        String token = getCookie(request);
        if(token != null) {
            return "redirect:/";
        }
        model.addAttribute("user", new RegisterFormDto());
        return "auth/register";
    }

    @GetMapping("/auth/welcome")
    @ResponseBody
    public String welcome() {
        return "Welcome this endpoint is not secure";
    }

    @PostMapping("/register")
    public String addNewUser(@ModelAttribute("user") @Valid RegisterFormDto userInfo,
                             BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "/auth/register";
        }
        try {
            User user = service.addUser(userInfo);
            model.addAttribute("successMessage", "Đăng ký thành công");
            return "/auth/login";
        }
        catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "/auth/register";
        }
    }

    public String getCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("token".equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    public void setCookie(HttpServletResponse response, @RequestParam String value) {
        Cookie cookie = new Cookie("token", value);
        cookie.setMaxAge(60 * 60 * 24 * 7);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    @PostMapping("/login")
    public String authenticateAndGetToken(@ModelAttribute("user") AuthRequest authRequest, HttpServletResponse response, Model model) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
        );
        System.out.print("test22");
        if (authentication.isAuthenticated()) {
            String token = jwtService.generateToken(authRequest.getUsername());
            setCookie(response, token);
            return "redirect:/";
        } else {
            System.out.print("test");
            model.addAttribute("errorMessage", "Sai tên đăng nhập hoặc mật khẩu");
            return "auth/login";
        }
    }
}
