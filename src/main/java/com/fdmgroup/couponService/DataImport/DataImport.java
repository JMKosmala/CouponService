package com.fdmgroup.couponService.DataImport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.fdmgroup.couponService.model.Coupon;
import com.fdmgroup.couponService.model.User;
import com.fdmgroup.couponService.repository.CouponRepository;
import com.fdmgroup.couponService.repository.UserRepository;

@Component
public class DataImport implements ApplicationRunner {
	
	
	private UserRepository userRepo;
	private CouponRepository couponRepo;
	
	
	@Autowired
	public DataImport(UserRepository userRepo, CouponRepository couponRepo) {
		this.userRepo = userRepo;
		this.couponRepo = couponRepo;
		
	}




	@Override
	public void run(ApplicationArguments args) throws Exception {
		User user1 = new User("Adam","Malysz","Was", 1050.5);
		userRepo.save(user1);
		User user2 = new User("Jas", "Fasola","MrBean",500);
		userRepo.save(user2);
		User user3 = new User("Robert","Kubica","Drajwer",5000);
		userRepo.save(user3);
		
		
		
		userRepo.save(user3);
				
		Coupon coupon1 = new Coupon(100.0, 3, user1);
		Coupon coupon2 = new Coupon(200.0, 1, user3);
		Coupon coupon3 = new Coupon(250.5, 2, user2);
		
		Coupon coupon4 = new Coupon(10.0, 4, user2);
		Coupon coupon5 = new Coupon(50.0, 2, user3);
		Coupon coupon6 = new Coupon(25.0, 3, user1);
		
		Coupon coupon7 = new Coupon(15.0, 4, user2);
		Coupon coupon8 = new Coupon(55.0, 0, user1);
		Coupon coupon9 = new Coupon(115.0, 3, user3);
		
		
		couponRepo.save(coupon1);
		couponRepo.save(coupon2);
		couponRepo.save(coupon3);
		couponRepo.save(coupon4);
		couponRepo.save(coupon5);
		couponRepo.save(coupon6);
		couponRepo.save(coupon7);
		couponRepo.save(coupon8);
		couponRepo.save(coupon9);
		
		
	}
}