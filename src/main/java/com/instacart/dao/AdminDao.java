package com.instacart.dao;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.instacart.model.Admin;
import com.instacart.model.User;

@Repository
public class AdminDao extends Dao{

	public Admin getAdmin(String adminName, String adminPassword) {
		try {
			
			begin();
			Query query = getSession().createQuery("from Admin where adminName = :adminName and adminPassword = :adminPassword");
			query.setString("adminName", adminName);
			query.setString("adminPassword", adminPassword);	
			
			Admin admin = (Admin) query.uniqueResult();
			commit();
			close();
			return admin;
		} catch (HibernateException e) {
			System.out.println(e);
			
			rollback();
		}
		return null;
	}
}
