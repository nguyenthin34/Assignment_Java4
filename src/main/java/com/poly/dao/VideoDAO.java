package com.poly.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import com.poly.entity.User;
import com.poly.entity.Video;

public class VideoDAO{
	
	public void insert(Video video) {
		EntityManager em = JpaUtils.getEntityManager();
		EntityTransaction trans = em.getTransaction();
		try {
			trans.begin();
			em.persist(video);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		} finally {
			em.close();
		}
	}

	public void update(Video video) {
		EntityManager em = JpaUtils.getEntityManager();
		EntityTransaction trans = em.getTransaction();
		try {
			trans.begin();
			em.merge(video);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		} finally {
			em.close();
		}
	}

	public void delete(String videoID) throws Exception {
		EntityManager em = JpaUtils.getEntityManager();
		EntityTransaction trans = em.getTransaction();
		try {
			trans.begin();
			User video = em.find(User.class, videoID);
			if (video != null) {
				em.remove(video);
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
	public List<Video> findAll() {
		EntityManager em = JpaUtils.getEntityManager();

		TypedQuery<Video> query = em.createQuery("SELECT v FROM Video v", Video.class);
		return query.getResultList();
		
	}
	public Video findByID(String videoID) {
		try {
			EntityManager em = JpaUtils.getEntityManager();
			Video video = em.find(Video.class, videoID);
			return video;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public List<Video> findpage() {
		EntityManager em = JpaUtils.getEntityManager();
		
		TypedQuery<Video> query = em.createQuery("SELECT v FROM Video v", Video.class);
		query.setFirstResult(0);
		query.setMaxResults(6);
		return query.getResultList();

	}
	public List<Video> findnotexists(int first, String id) {
		EntityManager em = JpaUtils.getEntityManager();
		String jpql = "SELECT v FROM Video v "
				+ " where not exists (SELECT f FROM Favorite f WHERE "
				+ " v.id = f.video.id AND f.user.id = :id)";
		TypedQuery<Video> query = em.createQuery(jpql, Video.class);
		query.setParameter("id", id);
		query.setFirstResult(first);
		query.setMaxResults(6);
		return query.getResultList();

	}
	public List<Video> findmyfavorite(String id) {
		EntityManager em = JpaUtils.getEntityManager();
		String jpql = "SELECT v FROM Video v "
				+ " where exists (SELECT f FROM Favorite f WHERE "
				+ " v.id = f.video.id AND f.user.id = :id)";
		TypedQuery<Video> query = em.createQuery(jpql, Video.class);
		query.setParameter("id", id);
		return query.getResultList();

	}
	public List<Video> findpage(int first) {
		EntityManager em = JpaUtils.getEntityManager();
		String jpql = "SELECT v FROM Video v ";
		TypedQuery<Video> query = em.createQuery(jpql, Video.class);
		query.setFirstResult(first * 6);
		query.setMaxResults(6);
		return query.getResultList();

	}
	public Long countvideo() {
		EntityManager em = JpaUtils.getEntityManager();
		
		TypedQuery<Long> query = em.createQuery("SELECT count(v) FROM Video v", Long.class);
		return query.getSingleResult();

	}
	
}
