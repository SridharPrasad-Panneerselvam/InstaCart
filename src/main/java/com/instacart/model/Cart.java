package com.instacart.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="Cart")
public class Cart implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int cid;
	
	@Column(name="cartQuantity")
	private int cartQuantity;
	
	@ManyToOne
	@JoinColumn(name="pid")
	private Product product;
	
	@ManyToOne
	@JoinColumn(name="userId")
	private User user;
	
	@Column(name="cartStatus")
	private String cartStatus;

	public String getCartStatus() {
		return cartStatus;
	}

	public void setCartStatus(String cartStatus) {
		this.cartStatus = cartStatus;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public int getCartQuantity() {
		return cartQuantity;
	}

	public void setCartQuantity(int cartQuantity) {
		this.cartQuantity = cartQuantity;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
