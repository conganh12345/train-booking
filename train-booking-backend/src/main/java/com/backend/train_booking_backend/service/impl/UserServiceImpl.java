package com.backend.train_booking_backend.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.train_booking_backend.models.User;
import com.backend.train_booking_backend.repositories.UserRepository;
import com.backend.train_booking_backend.service.UserService;

@Service
public class UserServiceImpl implements UserService 
{
    @Autowired
    private UserRepository userRepo;

    @Override
    public List<User> getAllUsers() 
    {
        return userRepo.findAll();
    }

    @Override
    public User addUser(User user) 
    {
        return userRepo.save(user);
    }

    @Override
    public User updateUser(Integer id, User user) 
    {
        if (userRepo.findById(id).isPresent()) {
            user.setId(id);
            return userRepo.save(user);
        }
        return null; 
    }

    @Override
    public User getUserByUsername(String username) 
    {
        return userRepo.findUserByUsername(username);
    }
    
    @Override
    public void deleteUser(Integer id) {
        userRepo.deleteById(id); 
    }
}
