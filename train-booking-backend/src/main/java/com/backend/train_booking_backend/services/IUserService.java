package com.backend.train_booking_backend.services;

import java.util.List;
import com.backend.train_booking_backend.models.User;

public interface IUserService 
{
	/**
     * Retrieve a list of all users.
     *
     * @return a list of users.
     */
    List<User> getAllUsers();
    
    User getUser(Integer id);
    
    /**
     * Add a new user.
     *
     * @param user the user object to be added.
     * @return the user that was added.
     */
    User addUser(User user);
    
    /**
     * Update the information of an existing user.
     *
     * @param id ID of the user to be updated.
     * @param user the user object containing the updated information.
     * @return the updated user or null if the user is not found.
     */
    User updateUser(Integer id, User user);
    
    /**
     * Retrieve a user by their username.
     *
     * @param username the username of the user to be found.
     * @return the user object or null if not found.
     */
    User getUserByUsername(String username);
    
    /**
     * Delete a user by their ID.
     *
     * @param id ID of the user to be deleted.
     */
    List<User> deleteUser(Integer[] ids);
}
