package com.backend.train_booking_backend.services;

import java.util.List;
import java.util.Optional;

import com.backend.train_booking_backend.models.Admin;

public interface IAdminService {

	List<Admin> getAllAdmins();

	Admin getAdmin(Integer id);

	Admin addAdmin(Admin admin);

	Admin updateAdmin(Integer id, Admin admin);

	Admin getAdminByAdminname(String adminname);

	Optional<Admin> deleteAdmin(Integer id);

}
