package com.backend.train_booking_backend.services;

import java.util.List;
import com.backend.train_booking_backend.models.Admin;

public interface IAdminService {

	List<Admin> getAllAdmins();

	Admin getAdmin(Integer id);

	Admin addAdmin(Admin admin);

	Admin updateAdmin(Integer id, Admin admin);

	Admin getAdminByAdminname(String adminname);

	List<Admin> deleteAdmin(Integer[] ids);

}
