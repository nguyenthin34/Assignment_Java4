package com.poly.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaUtils {
//	public static EntityManager getEntityManager() {
//		return Persistence.createEntityManagerFactory("Assigment").createEntityManager();
//	}
	
	private static EntityManagerFactory factory;
	private static EntityManager entityManager;
	
	public static EntityManagerFactory getEntityManagerFactory() {
		if ( factory == null || factory.isOpen() == false ) {
			factory = Persistence.createEntityManagerFactory("Assigment");
		}

		return factory;
	}
	
	public static EntityManager getEntityManager() {
		if (entityManager == null || entityManager.isOpen() == false) {
			entityManager = getEntityManagerFactory().createEntityManager();
		}
		
		return entityManager;
	}
}
