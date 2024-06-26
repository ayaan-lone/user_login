package com.test.userlogin.service.impl;

import com.test.userlogin.entity.Users;
import com.test.userlogin.request.LoginDto;
import com.test.userlogin.request.ResetPasswordDto;
import com.test.userlogin.dao.UserRepository;
import com.test.userlogin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }
    private static final int MAX_FAILED_ATTEMPTS = 5;
    private static final int LOCK_TIME_DURATION = 24; 

    @Override
    public String loginUser(LoginDto loginDto) {
        Optional<Users> userOptional = userRepository.findByUsername(loginDto.getUsername());
        if (userOptional.isPresent()) {
            Users user = userOptional.get();

            if (user.isBlocked()) {
                LocalDateTime lockTime = user.getBlockTime();
                LocalDateTime now = LocalDateTime.now();
                if (lockTime != null && now.isBefore(lockTime.plusHours(LOCK_TIME_DURATION))) {
                    return "User is blocked. Try again later.";
                } else {
                    user.setBlocked(false);
                    user.setLoginAttempts(0);
                    user.setBlockTime(null);
                    userRepository.save(user);
                }
            }

            if (loginDto.getPassword().equals(user.getPassword())) {
                user.setLoginAttempts(0);
                userRepository.save(user);
                return "Successfully logged in";
            } else {
                user.setLoginAttempts(user.getLoginAttempts() + 1);
                if (user.getLoginAttempts() >= MAX_FAILED_ATTEMPTS) {
                    user.setBlocked(true);
                    user.setBlockTime(LocalDateTime.now());
                    userRepository.save(user);
                    return "User is blocked due to multiple failed login attempts. Try again after 24 hours.";
                } else {
                    userRepository.save(user);
                    return "Incorrect Password";
                }
            }
        } else {
            return "User not found";
        }
    }
    @Override
    public String resetPassword(ResetPasswordDto resetPasswordDto) {
        Optional<Users> userOptional = userRepository.findByUsername(resetPasswordDto.getUsername());
        if (userOptional.isPresent()) {
            Users user = userOptional.get();
            if (resetPasswordDto.getOldPassword().equals(user.getPassword())) {
                user.setPassword(resetPasswordDto.getNewPassword());
                userRepository.save(user);
                return "Password successfully reset";
            } else {
                return "Old password doesn't match";
            }
        } else {
            return "User not found";
        }
    }
}
