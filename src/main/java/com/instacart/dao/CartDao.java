package com.instacart.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.instacart.exception.CartException;
import com.instacart.exception.ProductException;
import com.instacart.model.Cart;
import com.instacart.model.Product;

@Repository
public class CartDao extends Dao{
	
	public int validateExistingCart(int productId, int userId, String cartStatus) throws CartException{
		
		try {
			begin();
			Query query = getSession().createQuery("from Cart where pid =:pid and userId =:userId and cartStatus =:cartStatus");
			query.setInteger("pid", productId);
			query.setInteger("userId", userId);
			query.setString("cartStatus", cartStatus);
			
			List<Cart> cartSize = query.list();
			
			if(cartSize.size()==0) {
				return 0;
			}else {
				return 1;
			}
		}catch(HibernateException e){
			rollback();
			throw new CartException("Exception while validation cart: " + e.getMessage());
		}
		finally {
			close();
		}	
		
	}
	
	public void addCart(Cart cart) throws CartException {
		try {
			begin();
			getSession().save(cart);
			commit();

		} catch (HibernateException e) {
			rollback();
			throw new CartException("Exception while adding cart: " + e.getMessage());
		}
		finally {
			close();
		}
	}
	
	public List<Cart> getAllCartProductsbyId(int userId) throws CartException{
		
		List<Cart> list = new ArrayList<Cart>();
    	try
    	{
    		
    		begin();
    		Query query = getSession().createQuery("from Cart where userId =:userId and cartStatus =:cartStatus");
    		query.setInteger("userId", userId);
    		query.setString("cartStatus", "In Cart");
    		list = query.list();
    		return list;
    	}
    	catch(HibernateException e)
    	{
    		rollback();
    		throw new CartException("List of Products in cart could not be received");
    	}
    	finally {
			close();
		}
    	
    }

	public int editCart(int cartQuantity, int cid) throws CartException {
		// TODO Auto-generated method stub
	
		try {
			begin();
			Query query = getSession().createQuery("from Cart where cid =:cid");
			query.setInteger("cid", cid);
			Cart ob = (Cart) query.uniqueResult();
			ob.setCartQuantity(cartQuantity);
			commit();
			return 1;

		} catch (HibernateException e) {
			rollback();
			throw new CartException("Exception while adding product: " + e.getMessage());
		}
		finally {
			close();
		}
		
	}

	public int removeCart(int cid)throws CartException  {
		// TODO Auto-generated method stub
		try {
			begin();
			Query query = getSession().createQuery("from Cart where cid =:cid");
			query.setInteger("cid", cid);
			Cart cart = (Cart)query.uniqueResult();
			getSession().delete(cart);
			commit();
			return 1;
			
		}
		catch(HibernateException e) {
			rollback();
			throw new CartException("Cart delete failed");
		}
		finally {
			close();
		}
	}

	public void changeStatus(Cart c)throws CartException  {
		// TODO Auto-generated method stub
		try {
			begin();
			Query query = getSession().createQuery("from Cart where cid =:cid and cartStatus =:cartStatus and userId =:userId");
			query.setInteger("cid", c.getCid());
			query.setInteger("userId", c.getUser().getUserId());
			query.setString("cartStatus", "In Cart");
			Cart cart = (Cart)query.uniqueResult();
			cart.setCartStatus("Ordered");
			getSession().save(cart);
			commit();
			
		}
		catch(HibernateException e) {
			rollback();
			throw new CartException("Cart status change failed");
		}
		finally {
			close();
		}
}

	public List<Cart> getAllCartProductsbyStatus(int userId, String cartStatus)throws CartException {
		// TODO Auto-generated method stub
		List<Cart> list = new ArrayList<Cart>();
		try {
			begin();
			Query query = getSession().createQuery("from Cart where userId =:userId and cartStatus =:cartStatus");
			query.setInteger("userId", userId);
			query.setString("cartStatus", cartStatus);
			list = query.list();
			return list;
		}catch(HibernateException e) {
			rollback();
			throw new CartException("Cart status change failed");
		}
		finally {
			close();
		}
		
	}
}