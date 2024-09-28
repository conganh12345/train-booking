package com.backend.train_booking_backend.services.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.backend.train_booking_backend.models.Admin;
import com.backend.train_booking_backend.repositories.AdminRepository;
import com.backend.train_booking_backend.services.IAdminService;


@Service
public class AdminService implements IAdminService
{
	@Autowired
    private AdminRepository adminRepo;
	
	@Override
	public List<Admin> getAllAdmins() 
	{
		return adminRepo.findAll();
	}

	@Override
	public Admin getAdmin(Integer id) 
	{
		return adminRepo.findById(id).get();
	}

	@Override
	@Transactional
	public Admin addAdmin(Admin admin) 
	{
		try {
			return adminRepo.save(admin);
		}catch (Exception e) {
			throw new RuntimeException("Đã xảy ra lỗi khi thêm người dùng.", e);
		}
	}

	@Override
	@Transactional
	public Admin updateAdmin(Integer id, Admin admin) 
	{
		try { 
            Optional<Admin> oldUserOpt = adminRepo.findById(id);
            if (oldUserOpt.isPresent()) {
            	admin.setId(id);
            }
            return adminRepo.save(admin);
	    } catch (Exception e) {
	        throw new RuntimeException("Đã xảy ra lỗi khi sửa người dùng.", e); 
	    }
	}

	@Override
	public Admin getAdminByAdminname(String adminname)
	{
		return adminRepo.findAdminByAdminname(adminname);
	}

	@Override
	@Transactional
	public List<Admin> deleteAdmin(Integer[] ids) {
		List<Admin> adminDeletes = new ArrayList<>();

        try {
            for (Integer id : ids) {
                Optional<Admin> adminOpt = adminRepo.findById(id);
                if (adminOpt.isPresent()) {
                    Admin admin = adminOpt.get();
                    adminDeletes.add(admin);
                    adminRepo.deleteById(id);
                } else {
                    System.out.println("Admin with ID " + id + " not found.");
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Đã xảy ra lỗi khi xóa admin.", e);
        }
        return adminDeletes;
    }
}
	
