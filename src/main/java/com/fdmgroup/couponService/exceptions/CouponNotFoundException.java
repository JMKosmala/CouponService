package com.fdmgroup.couponService.exceptions;

public class CouponNotFoundException extends Exception {
	public CouponNotFoundException(int couponId) {
		super("The coupon with id " + couponId + " could not be found.");
	}
}
