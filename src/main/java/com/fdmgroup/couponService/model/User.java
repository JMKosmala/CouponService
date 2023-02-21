package com.fdmgroup.couponService.model;


import java.util.List;
import java.util.Objects;


import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name="users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer ID;
	private String firstname;
	private String lastname;
	private String username;
	private double totalPrice;
	@ElementCollection
	private List<Coupon> couponsList;

	
	
	

		//constructors
		public User(String firstname, String lastname, String username, double totalPrice) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
		this.totalPrice = totalPrice;
	}
	
	public User() {
		super();
	}
	//getters and setters
	
	public Integer getID() {
		return ID;
	}
	
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
		
	


	//hashCode and equals
	
	
	@Override
	public int hashCode() {
		return Objects.hash(ID, firstname, lastname, totalPrice, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(ID, other.ID) && Objects.equals(firstname, other.firstname)
				&& Objects.equals(lastname, other.lastname) && Double.doubleToLongBits(totalPrice) == Double.doubleToLongBits(other.totalPrice)
				&& Objects.equals(username, other.username);
	}

	@Override
	public String toString() {
		return "User [ID=" + ID + ", firstname=" + firstname + ", lastname=" + lastname + ", username=" + username
				+ ", totalPrice=" + totalPrice + ", myCoupons=" + "]";
	}

	public void setID(Integer iD) {
		ID = iD;
	}
	public List<Coupon> getCouponsList() {
		return couponsList;
	}

	public void setCouponsList(List<Coupon> couponsList) {
		this.couponsList = couponsList;
	}

	
	
	
}	