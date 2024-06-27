package com.test.userlogin.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.test.userlogin.dao.UserRepository;
import com.test.userlogin.entity.Users;
import com.test.userlogin.exception.UserLoginException;
import com.test.userlogin.request.LoginDto;
import com.test.userlogin.service.UserService;
import com.test.userlogin.util.ConstantsUtil;

// Implement the UserService here 
@Service
public class UserServiceImplementation implements UserService {

	// Automatically inject the bean that will use the UserRepository Dependency
	// here
	@Autowired
	private UserRepository userRepository;

	// Override the getAllUsers function that was declared in UserService
	@Override
	public List<Users> getAllUsers() {
		return userRepository.findAll();
	}

	// Override the loginUser String type Abstract Method(using the Login DTO here)

	@Override
	public String loginUser(LoginDto loginDto) throws UserLoginException {
		// Create an optional user so that it handles Null Pointer exception error
		// Find the user from userRepo using findByUsername takes in the parameters
		// accepted by login DTO
		Optional<Users> userOptional = userRepository.findByUsername(loginDto.getUsername());
		// Check if the user is present in the database
		if (userOptional.isPresent()) {
			// Initialize the user
			Users user = userOptional.get();
			// if the user is blocked, get the user blocked time and the date
			// if the password and the original passwords are same, set attempts to 0 again

			if (user.isBlocked()) {
				LocalDateTime lockTime = user.getBlockTime();
				LocalDateTime now = LocalDateTime.now();

				// if the LockTime ! null and the lock time is before 24 hours
				if (lockTime != null && now.isBefore(lockTime.plusHours(ConstantsUtil.LOCK_TIME_DURATION))) {

					throw new UserLoginException(HttpStatus.FORBIDDEN, ConstantsUtil.USER_BLOCKED_MESSAGE);

				} else {

					// Set the blocked to false
					// Reset the LoginAttempts to 0
					// Set the BlockTime to Null
					user.setBlocked(false);
					user.setLoginAttempts(0);
					user.setBlockTime(null);

					// Save the User after that
					userRepository.save(user);
				}
			}

			if (!loginDto.getPassword().equals(user.getPassword())) {

				user.setLoginAttempts(user.getLoginAttempts() + 1);

				// Check the Login Attempts, if greater than max, set user to blocked, save the
				// block time
				if (user.getLoginAttempts() >= ConstantsUtil.MAX_FAILED_ATTEMPTS) { // only if the user has attempted
																					// more than 5
					// times
					user.setBlocked(true);
					user.setBlockTime(LocalDateTime.now());
					userRepository.save(user);
//                    return "User is blocked due to multiple failed login attempts. Try again after 24 hours.";
					throw new UserLoginException(HttpStatus.UNAUTHORIZED, ConstantsUtil.USER_BLOCKED_MESSAGE);
				}

				userRepository.save(user);
//                    return "Incorrect Password";
				throw new UserLoginException(HttpStatus.UNAUTHORIZED, ConstantsUtil.USER_PASSWORD_INCORRECT);
			}

			user.setLoginAttempts(0); // REset to 0
			userRepository.save(user);
			return "Logged In Successfully";

		}

		// If Wrong password is sent, increase the LoginAttempts

		// if the user is not Present in the database
		else {
			throw new UserLoginException(HttpStatus.NOT_FOUND, ConstantsUtil.USER_NOT_AVAILABLE);
		}

	}

	// override the reset password method from service

}
