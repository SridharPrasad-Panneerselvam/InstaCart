package com.instacart.dao;



import java.util.List;
import com.instacart.model.User;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.instacart.exception.UserLoginException;

@Repository
public class UserDao extends Dao {
	
public User getUser(String userName, String userPassword) {
		try {
			
			begin();
			Query query = getSession().createQuery("from User where userName = :userName and userPassword = :userPassword");
			query.setString("userName", userName);
			query.setString("userPassword", userPassword);	
			
			User user = (User) query.uniqueResult();
			commit();
			close();
			return user;
		} catch (HibernateException e) {
			System.out.println(e);
			
			rollback();
		}
		return null;
	}

public void addToDb(User user) throws UserLoginException {

	
	try {
	begin();
	getSession().save(user);
	commit();
	}
	catch(HibernateException e) {
		rollback();
		throw new UserLoginException("Error while adding user", e);
	} finally {
		close();
	}
}

public boolean validateExistingUser(String userName)throws UserLoginException {
	try {
		begin();
		Query query = getSession().createQuery("from User where userName = :userName");
		query.setString("userName", userName);
		
		Object object = query.uniqueResult();
		if(object!= null) {
			return true;
		}else {
			return false;
		}
	}catch (HibernateException e) {
		rollback();
		throw new UserLoginException("Error during user validation",e);
	}finally {
		close();
	}
}


}