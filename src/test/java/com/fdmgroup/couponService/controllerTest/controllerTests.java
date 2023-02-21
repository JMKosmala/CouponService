package com.fdmgroup.couponService.controllerTest;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fdmgroup.couponService.CouponServiceApplication;
import com.fdmgroup.couponService.controller.UserController;
import com.fdmgroup.couponService.model.Coupon;
import com.fdmgroup.couponService.model.User;
import com.fdmgroup.couponService.service.ICouponService;
import com.fdmgroup.couponService.service.IUserService;

@SpringBootTest (classes= {UserController.class})
@ExtendWith (SpringExtension.class)
@AutoConfigureMockMvc (addFilters=false)
@ContextConfiguration (classes= CouponServiceApplication.class)

public class controllerTests {

	@Autowired
	MockMvc mockMvc;
	
	
	@MockBean
	IUserService userService;
	@MockBean
	ICouponService couponService;
	@InjectMocks
	private UserController userController;
	
	
	@Test
	public void test_IndexPage() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.get("/"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().string(""));
	
	}
	
	@Test
	public void test_goToIndex_statusOk() throws Exception {
		List<User> users = Arrays.asList(new User("Dan", "Ban", "daba", 1000.0));
		
		when(userService.getAllUsers()).thenReturn(users);

		
		mockMvc.perform(get("/"))
		.andExpect(status().isOk())
		.andExpect(model().attribute("users", users))
		.andExpect(view().name("index"));
	}

	
	@Test
	void test_formSubmitToMyCoupons() throws Exception {
		
		User user = new User("Dan", "Ban", "daba", 1000.0);
		user.setID(1);
		List<Coupon> coupons = Arrays.asList(new Coupon());
		user.setCouponsList(coupons);
		
		when(userService.getUserByID(1)).thenReturn(Optional.of(user));
		mockMvc.perform(MockMvcRequestBuilders.post("/usecoupon")
				.param("userID","1"))
				.andExpect(MockMvcResultMatchers.view().name("usecoupon"));
	}
	
	@Test
	void test_formSubmitUserNotFoundWhenUserDontExists() throws Exception {
		
		
		mockMvc.perform(MockMvcRequestBuilders.post("/usecoupon")
				.param("userID","5"))
				.andExpect(MockMvcResultMatchers.view().name("userNotFound"));
	}
	
	@Test
	void test_formSubmitToUseChosenCoupons() throws Exception {
		
		User user = new User("Dan", "Ban", "daba", 1000.0);
		user.setID(1);
		Coupon coupon=new Coupon(100.0, 2, user);
		coupon.setID(1);
				
		when(couponService.getCouponById(1)).thenReturn(Optional.of(coupon));
		mockMvc.perform(MockMvcRequestBuilders.post("/couponused")
				.param("couponID","1"))
				.andExpect(MockMvcResultMatchers.view().name("couponused"));
	}
	
	
	
}
