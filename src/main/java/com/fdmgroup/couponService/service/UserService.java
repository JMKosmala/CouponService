package com.fdmgroup.couponService.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.couponService.model.Coupon;
import com.fdmgroup.couponService.model.User;
import com.fdmgroup.couponService.repository.UserRepository;

@Service
public class UserService implements IUserService {

	@Autowired
	UserRepository repo;
	
	
	@Override
	public Optional<User> getUserByID(int id) {
		Optional<User> optUser =repo.findByID(id);
		return optUser;
		
	}

	@Override
	public List<User> getAllUsers() {
		return repo.findAll();
	}




}
