package com.backend.train_booking_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.backend.train_booking_backend.models.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {
	public Admin findAdminByAdminname(String adminname);
}
