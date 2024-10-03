package com.backend.train_booking_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.backend.train_booking_backend.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	public User findUserByUsername(String username);
	
    public User findByEmailAndPassword(String email, String password);
}
