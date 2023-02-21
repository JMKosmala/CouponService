package com.fdmgroup.couponService.serviceUnitTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fdmgroup.couponService.model.Coupon;
import com.fdmgroup.couponService.model.User;
import com.fdmgroup.couponService.repository.CouponRepository;
import com.fdmgroup.couponService.repository.UserRepository;
import com.fdmgroup.couponService.service.CouponService;
import com.fdmgroup.couponService.service.UserService;

@ExtendWith(MockitoExtension.class)
@ExtendWith(MockitoExtension.class)
public class CouponServiceUnitTests {

	@InjectMocks
	CouponService couponService;
	@Mock
	UserService mockUserService;
	@Mock
	CouponRepository mockCouponRepo;
	@Mock
	UserRepository mockUserRepo;
	
	Coupon coupon;
	Optional<Coupon> optCoupon;
	ArrayList<Coupon> coupons;
	
	@BeforeEach
	public void Setup() {
		coupon = new Coupon();
		optCoupon = Optional.of(coupon);
		coupons=new ArrayList<>();
		coupons.add(coupon);
	}
	
	@Test
	public void test_getAllCoupons_expectSucces() {
		when(mockCouponRepo.findAll()).thenReturn(coupons);
		ArrayList<Coupon>result = couponService.getAllCoupons();
		assertEquals(coupons,result);	
		}
	
	@Test
	public void test_getCouponById_expectSucces() {
		
	 Integer couponId = 1;
	 Coupon expectedCoupon = new Coupon();
	 expectedCoupon.setID(couponId);
	 Optional<Coupon> expectedOptional = Optional.of(expectedCoupon);
	 when(mockCouponRepo.findById(couponId)).thenReturn(expectedOptional);

	 Optional<Coupon> actualOptional = couponService.getCouponById(couponId);

	 assertTrue(actualOptional.isPresent());
	 assertEquals(expectedOptional.get(), actualOptional.get());
		 

	}
	@Test
	public void test_findByOwnerId_expectSucces() {
		
		User owner = new User();
		owner.setID(1);
		when(mockUserService.getUserByID(owner.getID())).thenReturn(Optional.of(owner));
		
		ArrayList<Coupon> coupons = new ArrayList<Coupon>();
		coupons.add(new Coupon());
		when(mockCouponRepo.findByOwner(owner)).thenReturn(coupons);
		
		List<Coupon> result = couponService.findByOwnerId(owner.getID());
		
		assertEquals(coupons, result);
		verify(mockCouponRepo, times(1)).findByOwner(owner);
		verify(mockUserService, times(1)).getUserByID(owner.getID());

	}
	
	@Test
    public void test_FindByOwnerIdReturnsNullWhenOwnerDoesNotExist_expectSucces() {
        
        Integer ownerId = 1;
        Optional<User> optionalOwner = Optional.empty();
        when(mockUserService.getUserByID(ownerId)).thenReturn(optionalOwner);

        
        List<Coupon> actualList = couponService.findByOwnerId(ownerId);

        assertNull(actualList);
        verify(mockUserService, times(1)).getUserByID(ownerId);
        verify(mockCouponRepo, never()).findByOwner(Mockito.any());
    }
	@Test 
	public void test_UseCoupon_expectSucces() {
		
		User user = new User("Dan", "Ban", "daba", 1000.0);
		user.setID(1);
		Coupon coupon=new Coupon(100.0, 2, user);
		coupon.setID(1);
		coupon.setOwner(user);
		
		Double newPrice = couponService.useCoupon(coupon, user);
				
		verify(mockUserRepo, times(1)).save(user);
		verify(mockCouponRepo, times(1)).save(coupon);
		
		assertEquals(user, coupon.getOwner());
		assertEquals(Integer.valueOf(1),coupon.getUsageLeft());
		assertEquals((double)newPrice,user.getTotalPrice(),0.1);

		
	}
	@Test 
	public void test_UseCouponWhenThereIsNoUsageLeft_expectSucces() {
		
		User user = new User("Dan", "Ban", "daba", 1000.0);
		user.setID(1);
		Coupon coupon=new Coupon(100.0, 0, user);
		coupon.setID(1);
		coupon.setOwner(user);
		
		Double newPrice = couponService.useCoupon(coupon, user);
				
		verify(mockUserRepo, times(0)).save(user);
		verify(mockCouponRepo, times(0)).save(coupon);
		
		assertEquals(user, coupon.getOwner());
		assertEquals(Integer.valueOf(0),coupon.getUsageLeft());
		assertEquals((double)newPrice,user.getTotalPrice(),0.1);

		
	}

	
	
	
	
	
}