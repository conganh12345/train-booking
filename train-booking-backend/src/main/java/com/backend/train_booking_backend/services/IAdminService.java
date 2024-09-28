package com.backend.train_booking_backend.services;


import java.util.List;
import com.backend.train_booking_backend.models.Admin;

public interface IAdminService {
	/**
     * Retrieve a list of all admins.
     *
     * @return a list of admins.
     */
	List<Admin> getAllAdmins();
	
	Admin getAdmin(Integer id);
	
	/**
     * Add a new admin.
     *
     * @param admin the user object to be added.
     * @return the admin that was added.
     */
	
	Admin addAdmin(Admin admin);
	
	Admin updateAdmin(Integer id, Admin admin);
	
	Admin getAdminByAdminname(String adminname);
	
	List<Admin> deleteAdmin(Integer[] ids);
	
}
