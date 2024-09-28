package com.backend.train_booking_backend.exception;

import com.backend.train_booking_backend.models.User;
import jakarta.validation.ValidationException;

public class UserValidate 
{
	public void validate(User user) 
	{
	    validateUsername(user.getUsername());
	    validateEmail(user.getEmail());
	    validatePassword(user.getPassword());
	    validatePhoneNumber(user.getPhoneNumber());
	    validateAddress(user.getAddress());
	}
	 
    private void validateUsername(String username) {
        if (username == null || username.isEmpty()) {
            throw new ValidationException("Username cannot be empty");
        }
    }

    private void validateEmail(String email) {
        if (email == null || email.isEmpty() || !isValidEmail(email)) {
            throw new ValidationException("Invalid email format");
        }
    }

    private void validatePassword(String password) {
        if (password == null || password.length() < 6) {
            throw new ValidationException("Password must be at least 6 characters");
        }
    }

    private void validatePhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.isEmpty() || !isValidPhoneNumber(phoneNumber)) {
            throw new ValidationException("Invalid phone number format");
        }
    }

    private void validateAddress(String address) {
        if (address == null || address.isEmpty()) {
            throw new ValidationException("Address cannot be empty");
        }
    }
    
    public boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        
        return email.matches(emailRegex);
    }

    public boolean isValidPhoneNumber(String phoneNumber) {
        String phoneRegex = "^(\\+?[0-9]{1,3})?[0-9]{10}$"; 
        
        return phoneNumber.matches(phoneRegex);
    }
}
