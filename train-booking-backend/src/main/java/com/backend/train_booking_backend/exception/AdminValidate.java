package com.backend.train_booking_backend.exception;

import com.backend.train_booking_backend.models.Admin;
import jakarta.validation.ValidationException;

public class AdminValidate 
{
	public void validate(Admin admin)
	{
		validateAdminname(admin.getAdminname());
	    validateEmail(admin.getEmail());
	    validatePassword(admin.getPassword());	
	}
	
	private void validateAdminname(String adminname)
	{
		if (adminname == null || adminname.isEmpty())
		{
            throw new ValidationException("Adminname cannot be empty");
        }
	}
	
	private void validateEmail(String email) 
	{
        if (email == null || email.isEmpty() || !isValidEmail(email)) 
        {
            throw new ValidationException("Invalid email format");
        }
    }
	
	private void validatePassword(String password) 
	{
        if (password == null || password.length() < 6) 
        {
            throw new ValidationException("Password must be at least 6 characters");
        }
    }
	
	public boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        
        return email.matches(emailRegex);
    }
}
