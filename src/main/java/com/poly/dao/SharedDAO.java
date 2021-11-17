package com.poly.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.poly.entity.Favorite;
import com.poly.entity.Shared;

public class SharedDAO {
	public void insert(Shared shared) {
		EntityManager em = JpaUtils.getEntityManager();
		EntityTransaction trans = em.getTransaction();
		try {
			trans.begin();
			em.persist(shared);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		} finally {
			em.close();
		}
	}
	
	
	public void update(Shared shared) {
		EntityManager em = JpaUtils.getEntityManager();
		EntityTransaction trans = em.getTransaction();
		try {
			trans.begin();
			em.merge(shared);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		} finally {
			em.close();
		}
	}

	public void delete(int sharedID) throws Exception {
		EntityManager em = JpaUtils.getEntityManager();
		EntityTransaction trans = em.getTransaction();
		try {
			trans.begin();
			Shared shared = em.find(Shared.class, sharedID);
			if (shared != null) {
				em.remove(shared);
			} else {
				throw new Exception("favorite cannot found");
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
	

}
