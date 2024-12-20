package com.example.truyen.Service;

import com.example.truyen.DTO.RegisterFormDto;
import com.example.truyen.Entity.AccountStatus;
import com.example.truyen.Entity.Role;
import com.example.truyen.Entity.User;
import com.example.truyen.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userDetail = userRepository.findByUsername(username); // Assuming 'email' is used as username

        // Converting UserInfo to UserDetails
        return userDetail.map(UserDetailsImpl::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
    }

    public User addUser(RegisterFormDto userInfo) throws Exception {
        if (!userInfo.isPasswordMatching()) {
            throw new Exception("Mật khẩu và xác nhận mật khẩu không khớp");
        }
        // Encode password before saving the user
        userInfo.setPassword(encoder.encode(userInfo.getPassword()));

        User user = new User();

        user.setUsername(userInfo.getUsername());
        user.setPassword(userInfo.getPassword());
        user.setEmail(userInfo.getEmail());
        user.setDisplayName(userInfo.getDisplayName());

        ZoneId zone = ZoneId.of("Asia/Ho_Chi_Minh");
        LocalDateTime currentDateAndTime = LocalDateTime.now(zone);
        user.setCreatedAt(currentDateAndTime);
        user.setUpdatedAt(currentDateAndTime);

        Role role = new Role(3L, null);
        AccountStatus accountStatus = new AccountStatus(1L, null, 0);
        user.setRole(role);
        user.setUserStatus(accountStatus);

        return userRepository.save(user);
    }
}
