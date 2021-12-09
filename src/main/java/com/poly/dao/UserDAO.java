package com.poly.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import com.poly.entity.User;

public class UserDAO {

//	public UserDAO() {
//		super(User.class);
//	}
	public void insert(User user) {
		EntityManager em = JpaUtils.getEntityManager();
		EntityTransaction trans = em.getTransaction();
		try {
			trans.begin();
			em.persist(user);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		} finally {
			em.close();
		}
	}

	public void update(User user) {
		EntityManager em = JpaUtils.getEntityManager();
		EntityTransaction trans = em.getTransaction();
		try {
			trans.begin();
			em.merge(user);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		} finally {
			em.close();
		}
	}

	public void delete(String userID) throws Exception {
		EntityManager em = JpaUtils.getEntityManager();
		EntityTransaction trans = em.getTransaction();
		try {
			trans.begin();
			User user = em.find(User.class, userID);
			if (user != null) {
				em.remove(user);
			} else {
				throw new Exception("User cannot found");
			}
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		} finally {
			em.close();
		}
	}

	public User findByID(String userID) {
		EntityManager em = JpaUtils.getEntityManager();
		try {

			User user = em.find(User.class, userID);
			return user;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		return null;
	}

	public List<User> findAll() {
		EntityManager em = JpaUtils.getEntityManager();
		try {

			TypedQuery<User> query = em.createNamedQuery("User.findAll", User.class);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		return null;
	}

	public User checklogin(String id, String password) {
		try {
			EntityManager em = JpaUtils.getEntityManager();
			// Câu lệnh truy vấn JPQL
			String jpql = "SELECT o FROM User o WHERE o.id=:id";
			// Tạo đối tượng truy vấn
			TypedQuery<User> query = em.createQuery(jpql, User.class);
			query.setParameter("id", id);
			// Truy vấn một thực thể
			User user = query.getSingleResult();
			return user;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public User findByEmail(String userID, String email) {
		EntityManager em = JpaUtils.getEntityManager();
		try {

			String jpql = "SELECT u FROM User u WHERE u.id =:id AND u.email =:email";
			TypedQuery<User> query = em.createQuery(jpql, User.class);
			query.setParameter("id", userID);
			query.setParameter("email", email);
			return query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		return null;
	}

	public User findByEmail2(String email) {
		EntityManager em = JpaUtils.getEntityManager();
		try {

			String jpql = "SELECT u FROM User u WHERE u.email =:email";
			TypedQuery<User> query = em.createQuery(jpql, User.class);
			query.setParameter("email", email);
			return query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		return null;
	}

	public User findByPass(String userID, String pass) {
		try {
			EntityManager em = JpaUtils.getEntityManager();
			String jpql = "SELECT u FROM User u WHERE u.id =:id AND u.passwords =:password";
			TypedQuery<User> query = em.createQuery(jpql, User.class);
			query.setParameter("id", userID);
			query.setParameter("password", pass);
			return query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
