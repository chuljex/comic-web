package com.example.truyen.Controller;

import com.example.truyen.Entity.User;
import com.example.truyen.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Optional;

@ControllerAdvice
public class GlobalControllerAdvice {

    @Autowired
    private UserRepository userRepository;

    @ModelAttribute
    public void addAttributes(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() && !(authentication.getPrincipal() instanceof String)) {
            Optional<User> displayName = userRepository.findByUsername(authentication.getName());
            String displayNameText = null;
            if (displayName.isPresent()) {
                displayNameText = displayName.get().getDisplayName();
            }
            model.addAttribute("displayName", displayNameText);
        }
    }
}

