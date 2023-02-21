package com.fdmgroup.couponService.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.couponService.model.Coupon;
import com.fdmgroup.couponService.model.User;
import com.fdmgroup.couponService.repository.CouponRepository;
import com.fdmgroup.couponService.repository.UserRepository;

@Service
public class CouponService implements ICouponService{

	@Autowired
	CouponRepository couponRepo;
	@Autowired
	UserRepository userRepo;
	@Autowired
	UserService userService;
	
	@Override
	public ArrayList<Coupon> getAllCoupons() {
		return new ArrayList<Coupon>(couponRepo.findAll());
	}
	
	@Override
	public Double useCoupon(Coupon coupon, User owner) {
		
		if(coupon.getUsageLeft()<1) {
			System.out.println("You cannot use this coupon anymore.");
			return owner.getTotalPrice();
		}
		
		owner.setTotalPrice(owner.getTotalPrice()-coupon.getValue());
		coupon.setUsageLeft(coupon.getUsageLeft()-1);
		
		System.out.println("new Price: "+ owner.getTotalPrice());
		
		couponRepo.save(coupon);
		userRepo.save(owner);
		
		return owner.getTotalPrice();
		
		
	}
	
	@Override
	public Optional<Coupon> getCouponById(int id) {
		Optional<Coupon>optCoupon=couponRepo.findById(id);
		return optCoupon;
			
	}
	@Override
	public ArrayList<Coupon> findByOwnerId(Integer userId) {
		Optional<User> owner = userService.getUserByID(userId);
		if(owner.isPresent())  
			return couponRepo.findByOwner(owner.get());
		else {
			return null;
		}	
	}


}

	
