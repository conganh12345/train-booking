package com.frontend.train_booking_frontend_customer.services;

import java.util.List;

import com.frontend.train_booking_frontend_customer.models.User;

public interface IUserService {
	List<User> getAllUsers();
	User getUserByEmailPassword(String email, String password);
}
