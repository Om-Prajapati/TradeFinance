package com.bridgeit.TradeFinanceApp.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bridgeit.TradeFinanceApp.model.User;

@Component
public class UserDaoImpl implements UserDao {

	@Autowired
	SessionFactory factory;

	public SessionFactory getFactory() {
		return factory;
	}

	public void setFactory(SessionFactory factory) {
		this.factory = factory;
	}
	
	public int registration(User user) {
		Session session = factory.openSession();
		Transaction transaction = null;
		try {
			
			transaction = session.beginTransaction();
			int id= (Integer) session.save(user);
			return id;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			transaction.commit();
			session.close();
		}
		return 0;
	}

	public User login(User user) {
		Session session = factory.openSession();
		@SuppressWarnings("deprecation")
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("email", user.getEmail()));
		criteria.add(Restrictions.eq("password", user.getPassword()));
		criteria.add(Restrictions.eq("isActive", true));
		User finalUser = (User) criteria.uniqueResult();
		if (finalUser == null) {
			session.close();
			return null;
		}
		session.close();
		return finalUser;
	}

	public User updateUser(User user) {
		Session session = factory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.update(user);
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			transaction.commit();
			session.close();
		}
		return user;
	}

	public User getUserById(int id) {
		Session session = factory.openSession();
		User user = session.get(User.class, id);
		session.close();
		return user;
	}

	public User getUserByAuthenticateKey(String authenticateKey) {
		Session session = factory.openSession();
		@SuppressWarnings("deprecation")
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("authenticate_user_key", authenticateKey));
		User user = (User) criteria.uniqueResult();
		if (user == null) {
			session.close();
			return null;
		}
		session.close();
		return user;
	}
	
	public User emailValidation(String email) {
		Session session = factory.openSession();
		@SuppressWarnings("deprecation")
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("email", email));
		User user = (User) criteria.uniqueResult();
		session.close();
		return user;
	}
	

	
}
