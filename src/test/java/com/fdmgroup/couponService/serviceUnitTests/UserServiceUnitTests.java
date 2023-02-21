package com.fdmgroup.couponService.serviceUnitTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.fdmgroup.couponService.model.Coupon;
import com.fdmgroup.couponService.model.User;
import com.fdmgroup.couponService.repository.CouponRepository;
import com.fdmgroup.couponService.repository.UserRepository;
import com.fdmgroup.couponService.service.CouponService;
import com.fdmgroup.couponService.service.UserService;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class UserServiceUnitTests {

		@InjectMocks
		UserService userService;
		@InjectMocks
		CouponService couponService;
		@MockBean
		UserRepository mockUserRepo;
		@MockBean
		CouponRepository mockCouponRepo;
		
		User user;
		Optional<User> optUser;
		ArrayList<User> users;
		
		@BeforeEach
		public void Setup() {
			user = new User();
			optUser = Optional.of(user);
			users = new ArrayList<User>();
			users.add(user);	
		}
		
		
		@Test
		public void test_getUserByID_expectSucces(){
			
			Integer userId = 3;
	        User user = new User();
	        user.setID(userId);
	        Optional<User> expectedUser = Optional.of(user);
	        when(mockUserRepo.findByID(userId)).thenReturn(expectedUser);

	        Optional<User> actualUser = userService.getUserByID(userId);
	        assertTrue(actualUser.isPresent());
	        assertEquals(expectedUser.get(), actualUser.get());
	    }
		
		 @Test
		 
		    public void test_getUserByIDReturnsEmptyOptionalWhenNotExists() {
		     
		        Integer userId = 1;
		        Optional<User> expectedUser = Optional.empty();
		        when(mockUserRepo.findById(userId)).thenReturn(expectedUser);
		        Optional<User> actualUser = userService.getUserByID(userId);
		        assertFalse(actualUser.isPresent());
		    }


		
		
				
		@Test
		public void test_getAllUsers_expectSucces(){
		userService.getAllUsers();
		verify(mockUserRepo,times(1)).findAll();
		}
		
				
			
}	
