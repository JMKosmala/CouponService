package com.fdmgroup.couponService.service;


import java.util.List;
import java.util.Optional;


import com.fdmgroup.couponService.model.User;

public interface IUserService {
	
	Optional<User> getUserByID (int id);
	List<User> getAllUsers();
	

}
