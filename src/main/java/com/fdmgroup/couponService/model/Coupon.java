package com.fdmgroup.couponService.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



@Entity
@Table(name="coupons")
public class Coupon {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer ID;
	private Double value;
	private Integer usageLeft;
	@ManyToOne
	private User owner;
	
	

	//constructors
	public Coupon(Double value, Integer usageLeft, User user) {
		super();
		this.value = value;
		this.usageLeft = usageLeft;
		this.owner=user;
		
	}
	public User getOwner() {
		return owner;
	}
	public void setOwner(User owner) {
		this.owner = owner;
	}
	public Coupon() {
		super();
	}
	
	//getters and setters
	public Integer getID() {
		return ID;
	}
	public void setID(Integer iD) {
		ID = iD;
	}
	public Double getValue() {
		return value;
	}
	public void setValue(Double value) {
		this.value = value;
	}
	public Integer getUsageLeft() {
		return usageLeft;
	}
	public void setUsageLeft(Integer usageLeft) {
		this.usageLeft = usageLeft;
	}
	

	//hashCode and equals
	@Override
	public int hashCode() {
		return Objects.hash(ID, usageLeft, value);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coupon other = (Coupon) obj;
		return Objects.equals(ID, other.ID) && Objects.equals(usageLeft, other.usageLeft) && Objects.equals(value, other.value);
	}
	
	//toString
	@Override
	public String toString() {
		return "Coupon [ID=" + ID + ", value=" + value + ", usageLeft=" + usageLeft + "]";
	}

	
}
