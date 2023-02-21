package com.fdmgroup.couponService.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fdmgroup.couponService.model.Coupon;
import com.fdmgroup.couponService.model.User;
import com.fdmgroup.couponService.service.ICouponService;
import com.fdmgroup.couponService.service.IUserService;


@Controller
public class UserController {
	
	@Autowired 
	private IUserService userService;
	
	@Autowired 
	private ICouponService couponService;
	
	@GetMapping("/")
	public String goToIndexPage(ModelMap model) {
		
		model.addAttribute("users", userService.getAllUsers());
		model.addAttribute("coupon",couponService.getAllCoupons());
		
		return "index";
	}
		
	@PostMapping("/usecoupon")
	public String showUserCoupons(ModelMap model, @RequestParam(defaultValue = "0") Integer userID) {
		
						
		Optional<User> user = userService.getUserByID(userID);
		
		if (user.isEmpty()) {
			return "userNotFound";
		}
		
		model.addAttribute("user", user.get());
		model.addAttribute("couponsList",couponService.findByOwnerId(userID));
		return "usecoupon";
	}
	
	@PostMapping("/couponused")
	public String coupoHaveBeenUsed (ModelMap model, @RequestParam Integer couponID) {
		
		
		
		Optional<Coupon> optCoupon =couponService.getCouponById(couponID);
		Coupon coupon = optCoupon.get();
		User user=coupon.getOwner();
		
		if((coupon.getUsageLeft()<1) || (coupon.getValue()>user.getTotalPrice())) {
			return "couponNotAvailable";
		}
		else {
		couponService.useCoupon(coupon,user);
		
		model.addAttribute("coupon",coupon);
		model.addAttribute("user",user);
		
		
		return "couponused";
		}
	}

	
	
}
