package com.instacart.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;


import com.instacart.exception.ProductException;
import com.instacart.exception.UserLoginException;
import com.instacart.model.Product;

@Repository
public class ProductDao extends Dao{

	public void addProduct(Product product) throws ProductException {
		try {
			begin();
			getSession().save(product);
			commit();

		} catch (HibernateException e) {
			rollback();
			throw new ProductException("Exception while adding product: " + e.getMessage());
		}
		finally {
			close();
		}
	}
	
	public boolean validateExistingProduct(String productName)throws ProductException {
		try {
			begin();
			Query query = getSession().createQuery("from Product where productName = :productName");
			query.setString("productName", productName);
			
			Object object = query.uniqueResult();
			if(object!= null) {
				return true;
			}else {
				return false;
			}
			
		}catch (HibernateException e) {
			rollback();
			throw new ProductException("Error during product validation",e);
		}
		finally {
			close();
		}
	}
	
	public List<Product> getAllProducts() throws ProductException{
		
		List<Product> list = new ArrayList<Product>();
    	try
    	{
    		
    		begin();
    		Query query = getSession().createQuery("from Product");
    		list = query.list();
    		return list;
    	}
    	catch(HibernateException e)
    	{
    		rollback();
    		throw new ProductException("List of Product could not be received");
    	}
    	finally {
			close();
		}
    	
    }
	
	public List<Product> productSearch(String productSearch) throws ProductException{
		
		List<Product> list = new ArrayList<Product>();
		
		try
		{
			begin();
			Query query = getSession().createQuery("from Product where productName like :like");
			query.setString("like", "%"+productSearch+"%");
			list = query.list();
			return list;
		}catch(HibernateException e)
		{
			rollback();
			throw new ProductException("Product Search failed");
		}
		finally {
			close();
		}
		
		
	}
	
	public Product editProduct(Product product) throws ProductException{
		try {
			begin();
			Query query = getSession().createQuery("from Product where pid =:pid");
			query.setInteger("pid", product.getPid());
			Product p = (Product)query.uniqueResult();
			p.setProductName(product.getProductName());
			p.setProductPrice(product.getProductPrice());
			p.setProductQuantity(product.getProductQuantity());
			p.setProductImagePath(product.getProductImagePath());
			p.setProductStatus(product.getProductStatus());
			getSession().save(p);
			commit();
			return product;

		} catch (HibernateException e) {
			rollback();
			throw new ProductException("Exception while adding product: " + e.getMessage());
		}
		finally {
			close();
		}
	}
	
	public Product productSearchById(int pid) throws ProductException{
		
		
		try
		{
			begin();
			Query query = getSession().createQuery("from Product where pid =:pid");
			query.setInteger("pid", pid);
			Product product = (Product)query.uniqueResult();
			return product;
			
		}catch(HibernateException e)
		{
			rollback();
			throw new ProductException("Product Search failed");
		}
		finally {
			close();
		}
		
		
	}
	
	public void removeProduct(int pid) throws ProductException{
		try {
			begin();
			Query query = getSession().createQuery("from Product where pid =:pid");
			query.setInteger("pid", pid);
			Product product = (Product)query.uniqueResult();
			getSession().delete(product);
			commit();
			
		}
		catch(HibernateException e) {
			rollback();
			throw new ProductException("Product delete failed");
		}
		finally {
			close();
		}
		
	}

	public void updateQuantity(int quantity, int pid) throws ProductException{
		// TODO Auto-generated method stub
		
		try {
			begin();
			Query query = getSession().createQuery("from Product where pid =:pid");
			query.setInteger("pid", pid);
			Product product = (Product)query.uniqueResult();
			product.setProductQuantity(quantity);
			String productStatus="";
			if(quantity==0) {
				productStatus = "Out of Stock"; 
			}else if(quantity>0 && quantity<10) {
				productStatus = "Low In Stock";
			}else {
				productStatus = "In Stock";
			}
			product.setProductStatus(productStatus);
			getSession().save(product);
			commit();
			
		}
		catch(HibernateException e) {
			rollback();
			throw new ProductException("Product delete failed");
		}
		finally {
			close();
		}
		
	}
	
	
}
