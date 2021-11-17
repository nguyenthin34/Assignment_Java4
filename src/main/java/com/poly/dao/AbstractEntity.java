package com.poly.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;

import com.poly.entity.User;

public abstract class AbstractEntity<T> {
	private Class<T> entityclass;
	public AbstractEntity(Class<T> cls){
		this.entityclass = cls;
	}
	public void insert(T entity) {
		EntityManager em = JpaUtils.getEntityManager();
		EntityTransaction trans = em.getTransaction();
		try {
			trans.begin();
			em.persist(entity);
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
		}finally {
			em.close();
		}
	}
	public void update(T entity) {
		EntityManager em = JpaUtils.getEntityManager();
		EntityTransaction trans = em.getTransaction();
		try {
			trans.begin();
			em.merge(entity);
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
		}finally {
			em.close();
		}
	}
	public void remove(String id) {
		EntityManager em = JpaUtils.getEntityManager();
		EntityTransaction trans = em.getTransaction();
		try {
			trans.begin();
			T entity = em.find(entityclass, id);
			em.remove(entity);
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
		}finally {
			em.close();
		}
	}
	public T findByID(String id) {
		EntityManager em = JpaUtils.getEntityManager();
			T entity = em.find(entityclass, id);
			return entity;
	}
	
	@SuppressWarnings("unchecked")
	public List<T> findByAll(){
		EntityManager em = JpaUtils.getEntityManager();
		try {
			
			@SuppressWarnings("rawtypes")
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(entityclass));
			return em.createQuery(cq).getResultList();
		} finally {
			em.close();
			}
	}
	
	
}
