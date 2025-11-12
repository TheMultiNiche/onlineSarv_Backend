package com.example.authapp.service;

import com.example.authapp.entity.User;
import com.example.authapp.repository.UserRepository;
import com.example.authapp.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private JwtUtil jwtUtil;
    
    public User registerUser(String email, String password) {
        if (userRepository.existsByEmail(email)) {
            throw new RuntimeException("User already exists with email: " + email);
        }
        
        User user = new User();
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        
        return userRepository.save(user);
    }
    
    public String authenticateUser(String email, String password) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        
        if (userOptional.isEmpty()) {
            throw new RuntimeException("User not found with email: " + email);
        }
        
        User user = userOptional.get();
        
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }
        
        return jwtUtil.generateToken(email);
    }
    
    public void initiatePasswordReset(String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        
        if (userOptional.isEmpty()) {
            throw new RuntimeException("User not found with email: " + email);
        }
        
        User user = userOptional.get();
        String resetToken = UUID.randomUUID().toString();
        user.setResetToken(resetToken);
        userRepository.save(user);
        
        // In a real application, you would send an email with the reset token
        System.out.println("Password reset token for " + email + ": " + resetToken);
    }
    
    public void resetPassword(String resetToken, String newPassword) {
        Optional<User> userOptional = userRepository.findByResetToken(resetToken);
        
        if (userOptional.isEmpty()) {
            throw new RuntimeException("Invalid reset token");
        }
        
        User user = userOptional.get();
        user.setPassword(passwordEncoder.encode(newPassword));
        user.setResetToken(null);
        userRepository.save(user);
    }
    
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));
    }
}
