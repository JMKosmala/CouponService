package com.fdmgroup.couponService.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fdmgroup.couponService.model.Coupon;
import com.fdmgroup.couponService.model.User;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Integer> {

	public ArrayList<Coupon> findAll();
	public ArrayList<Coupon> findByUsageLeft(Integer usageLeft);
	public ArrayList<Coupon> findByOwner(User user);

}
