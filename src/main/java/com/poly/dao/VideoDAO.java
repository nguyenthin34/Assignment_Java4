package com.poly.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import com.poly.entity.Video;

public class VideoDAO {
	EntityManager em = JpaUtils.getEntityManager();
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
		}
	}

	public void delete(String videoID) throws Exception {
		EntityManager em = JpaUtils.getEntityManager();
		EntityTransaction trans = em.getTransaction();
		try {
			trans.begin();
			Video video = em.find(Video.class, videoID);
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
		} 
	}

	public ArrayList<Video> findAll() {
		
			TypedQuery<Video> query = em.createQuery("SELECT v FROM Video v", Video.class);
			return (ArrayList<Video>) query.getResultList();
	}

	public Video findByID(String videoID) {
		EntityManager em = JpaUtils.getEntityManager();
		try {
			
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
			List<Video> list = query.getResultList();
			return list;
	}

	public ArrayList<Video> findnotexists(int first, String id) {
		EntityManager em = JpaUtils.getEntityManager();
		try {
			String jpql = "SELECT v FROM Video v "
					+ " where not exists (SELECT f FROM Favorite f WHERE  v.id = f.video.id AND f.user.id = :id)";
			TypedQuery<Video> query = em.createQuery(jpql, Video.class);
			query.setParameter("id", id);
			query.setFirstResult(first);
			query.setMaxResults(6);
			return (ArrayList<Video>) query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<Video> findallnotexists(String id) {
		EntityManager em = JpaUtils.getEntityManager();
		try {
			
			String jpql = "SELECT v FROM Video v " + " where not exists (SELECT f FROM Favorite f WHERE "
					+ " v.id = f.video.id AND f.user.id = :id)";
			TypedQuery<Video> query = em.createQuery(jpql, Video.class);
			query.setParameter("id", id);
			return (ArrayList<Video>) query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<Video> findpageallnotexists(String id, int first, int max) {
		EntityManager em = JpaUtils.getEntityManager();
		try {
			
			String jpql = "SELECT v FROM Video v " + " where not exists (SELECT f FROM Favorite f WHERE "
					+ " v.id = f.video.id AND f.user.id = :id)";
			TypedQuery<Video> query = em.createQuery(jpql, Video.class);
			query.setParameter("id", id);
			query.setFirstResult(first);
			query.setMaxResults(max);
			return (ArrayList<Video>) query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Video> findmyfavorite(String id) {
		EntityManager em = JpaUtils.getEntityManager();
		try {	
			String jpql = "SELECT v FROM Video v " + " where exists (SELECT f FROM Favorite f WHERE "
					+ " v.id = f.video.id AND f.user.id = :id)";
			TypedQuery<Video> query = em.createQuery(jpql, Video.class);
			query.setParameter("id", id);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Video> findpage(int first, int maxx) {
		EntityManager em = JpaUtils.getEntityManager();
		try {
			String jpql = "SELECT v FROM Video v ";
			TypedQuery<Video> query = em.createQuery(jpql, Video.class);
			query.setFirstResult(first);
			query.setMaxResults(maxx);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Video> findbyVideoTitle(String title){
		EntityManager em = JpaUtils.getEntityManager();
		try {
			String jpql = "SELECT v FROM Video v "
					+ " WHERE v.title like :keyword";
			TypedQuery<Video> query = em.createQuery(jpql, Video.class);
			query.setParameter("keyword", "%" + title + "%");
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Video> findbyVideoTitleNotlike(String title, String userid){
		EntityManager em = JpaUtils.getEntityManager();
		try {
			String jpql = "SELECT v FROM Video v "
					+ " WHERE not "
					+ " exists (SELECT f FROM Favorite f WHERE "
					+ "	v.id = f.video.id AND f.user.id = :id) "
					+ " AND v.title like :keyword";
			TypedQuery<Video> query = em.createQuery(jpql, Video.class);
			query.setParameter("id", userid);
			query.setParameter("keyword", "%" + title + "%");
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Video> topview(){
			EntityManager em = JpaUtils.getEntityManager();
		try {
			String jpql  = "SELECT v FROM Video v "
					+ " ORDER BY v.views DESC ";
			TypedQuery<Video> query = em.createQuery(jpql, Video.class);
			query.setFirstResult(0);
			query.setMaxResults(10);
			List<Video> list = query.getResultList();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
}
