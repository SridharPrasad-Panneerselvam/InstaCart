package com.instacart.model;



import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "Product")
public class Product {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="pid", unique = true, nullable = false)
    private int pid;
	
	@Column(name="productName", unique= true, nullable=false)
	private String productName;
	
	@Column(name="productPrice")
	private int productPrice;
	
	@Column(name="productQuantity")
	private int productQuantity;
	
	@Column(name="productStatus")
	private String productStatus;

	@Column(name="productImagePath")
	private String productImagePath;
	


	public int getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(int productQuantity) {
		this.productQuantity = productQuantity;
	}

	@ManyToOne
	User user;
	
	public Product() {
		
	}
	
	public Product(String productName, int productPrice, int productQuantity, String productStatus, String productImagePath) {
		this.productName=productName;
		this.productPrice=productPrice;
		this.productQuantity= productQuantity;
		this.productStatus= productStatus;
		this.productImagePath= productImagePath;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}

	public String getProductStatus() {
		return productStatus;
	}

	public void setProductStatus(String productStatus) {
		this.productStatus = productStatus;
	}


	public String getProductImagePath() {
		return productImagePath;
	}

	public void setProductImagePath(String productImagePath) {
		this.productImagePath = productImagePath;
	}


}