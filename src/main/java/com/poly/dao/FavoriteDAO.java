package com.poly.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import com.poly.entity.Favorite;
import com.poly.entity.Video;

public class FavoriteDAO {
	public void insert(Favorite favorite) {
		EntityManager em = JpaUtils.getEntityManager();
		EntityTransaction trans = em.getTransaction();
		try {
			trans.begin();
			em.persist(favorite);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		} finally {
			em.close();
		}
	}
	
	
	public void update(Favorite favorite) {
		EntityManager em = JpaUtils.getEntityManager();
		EntityTransaction trans = em.getTransaction();
		try {
			trans.begin();
			em.merge(favorite);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		} finally {
			em.close();
		}
	}

	public void delete(int favoriteID) throws Exception {
		EntityManager em = JpaUtils.getEntityManager();
		EntityTransaction trans = em.getTransaction();
		try {
			trans.begin();
			Favorite favorite = em.find(Favorite.class, favoriteID);
			if (favorite != null) {
				em.remove(favorite);
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
	
	public void removeByID(String videoId, String userId) {
		EntityManager em = JpaUtils.getEntityManager();
		EntityTransaction trans = em.getTransaction();
		Favorite favorite = findLikeVideoCookie(videoId, userId);
		try {
			trans.begin();
			if (favorite != null) {
				em.remove(favorite);
			} else {
				throw new Exception("favorite cannot found");
			}
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
		} finally {
			em.close();
		}
	}
	public Favorite findLikeVideoCookie(String videoId, String userId) {
		try {
			EntityManager em = JpaUtils.getEntityManager();
			String jpql = "SELECT f FROM Favorite f WHERE "
					+ " f.video.id=:videoId AND f.user.id=:userId";
			TypedQuery<Favorite> query = em.createQuery(jpql, Favorite.class);
			query.setParameter("videoId", videoId);
			query.setParameter("userId", userId);
			return query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public Favorite findLikeVideo(String videoId, String userId) {		
		EntityManager em = JpaUtils.getEntityManager();
			String jpql = "SELECT f FROM Favorite f WHERE "
					+ " f.video.id=:videoId AND f.user.id=:userId";
			TypedQuery<Favorite> query = em.createQuery(jpql, Favorite.class);
			query.setParameter("videoId", videoId);
			query.setParameter("userId", userId);
			return query.getSingleResult();
	}
}