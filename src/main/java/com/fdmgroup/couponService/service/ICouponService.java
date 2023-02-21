package com.fdmgroup.couponService.service;

import java.util.ArrayList;
import java.util.Optional;

import com.fdmgroup.couponService.model.Coupon;
import com.fdmgroup.couponService.model.User;

public interface ICouponService {
	
	
	public ArrayList<Coupon> getAllCoupons();
	public ArrayList<Coupon> findByOwnerId(Integer userId);
	public Optional<Coupon> getCouponById(int id);
	public Double useCoupon(Coupon coupon, User owner);
	
}
