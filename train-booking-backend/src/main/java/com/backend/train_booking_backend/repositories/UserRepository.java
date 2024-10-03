package com.backend.train_booking_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.backend.train_booking_backend.models.User;
import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	public User findUserByUsername(String username);
	
	@Query("SELECT u FROM User u WHERE u.email = :email")
	public List<User> findByEmail(@Param("email") String email);
	
    public User findByEmailAndPassword(String email, String password);
    
}
