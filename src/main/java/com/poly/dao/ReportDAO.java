package com.poly.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.poly.entity.Favorite;
import com.poly.entity.Report;
import com.poly.entity.Report2;
import com.poly.entity.Report3;
import com.poly.entity.TopLike;
import com.poly.entity.TopShare;

public class ReportDAO {

	public List<Report> favorites() {
		EntityManager em = JpaUtils.getEntityManager();
		try {

			String jpql = "SELECT new Report(o.video.title, count(o), max(o.likeDate), min(o.likeDate))"
					+ " FROM Favorite o " + " GROUP BY o.video.title";
			TypedQuery<Report> query = em.createQuery(jpql, Report.class);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		return null;
	}

	public List<Report3> listShare() {
		EntityManager em = JpaUtils.getEntityManager();
		try {

			String jpql = "SELECT new Report3(o.user.fullname, o.user.email, o.email, o.sharedDate)" + " FROM Shared o";
			TypedQuery<Report3> query = em.createQuery(jpql, Report3.class);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		return null;
	}

	public List<Report2> favoritesuser() {
		EntityManager em = JpaUtils.getEntityManager();
		try {

			String jpql = "SELECT new Report2(o.user.id, o.user.fullname, o.user.email, o.likeDate)"
					+ " FROM Favorite o";
			TypedQuery<Report2> query = em.createQuery(jpql, Report2.class);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		return null;
	}

	public List<Report> findfavorite(String keyword) {
		EntityManager em = JpaUtils.getEntityManager();
		try {
			String jpql = "SELECT new Report(o.video.title, count(o), max(o.likeDate), min(o.likeDate))"
					+ " FROM Favorite o WHERE o.video.title LIKE :keyword" + " GROUP BY o.video.title";
			TypedQuery<Report> query = em.createQuery(jpql, Report.class);
			query.setParameter("keyword", "%" + keyword + "%");
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		return null;
	}

	public List<Report2> findfavoriteUser(String keyword) {
		EntityManager em = JpaUtils.getEntityManager();
		try {

			String jpql = "SELECT new Report2(o.user.id, o.user.fullname, o.user.email, o.likeDate)"
					+ " FROM Favorite o WHERE o.video.title LIKE :keyword";
			TypedQuery<Report2> query = em.createQuery(jpql, Report2.class);
			query.setParameter("keyword", "%" + keyword + "%");
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		return null;
	}

	public List<Report3> findShared(String keyword) {
		EntityManager em = JpaUtils.getEntityManager();
		try {

			String jpql = "SELECT new Report3(o.user.fullname, o.user.email, o.email, o.sharedDate) " + " FROM Shared o"
					+ " WHERE o.video.title LIKE :keyword";
			TypedQuery<Report3> query = em.createQuery(jpql, Report3.class);
			query.setParameter("keyword", "%" + keyword + "%");
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		return null;
	}

	public List<TopLike> toplike() {
		EntityManager em = JpaUtils.getEntityManager();
		String jpql = "SELECT new TopLike(f.video, COUNT(f.video)) FROM Favorite f"
				+ " GROUP BY f.video "
				+ " ORDER BY COUNT(f.video) DESC ";
		TypedQuery<TopLike> query = em.createQuery(jpql, TopLike.class);
		query.setFirstResult(0);
		query.setMaxResults(10);
		return query.getResultList();

	}

	public List<TopShare> topshare() {
		EntityManager em = JpaUtils.getEntityManager();
		try {

			String jpql = "SELECT new TopShare(s.video, COUNT(s.video)) FROM Shared s " + " GROUP BY s.video"
					+ " ORDER BY COUNT(s.video) DESC ";
			TypedQuery<TopShare> query = em.createQuery(jpql, TopShare.class);
			query.setFirstResult(0);
			query.setMaxResults(10);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
