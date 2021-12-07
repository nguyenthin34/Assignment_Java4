package com.poly.helper;

import java.net.http.HttpRequest;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class XScope {
//	public static HttpServletRequest request() {
//		return RRSharer.request();
//	}
//	public static HttpSession session() {
//		return request().getSession();
//	}
//	public static ServletContext application() {
//		return request().getServletContext();
//	}
//	/**
//	* Táº¡o attribute trong request scope
//	* @param name tĂªn attribute
//	* @param value giĂ¡ trá»‹ cá»§a attribute
//	*/
//	public static void setRequest(String name, Object value) {
//		request().setAttribute(name, value);
//	}
//	/**
//	* Ä�á»�c attribute trong request scope
//	* @param name tĂªn attribute
//	* @return GiĂ¡ trá»‹ cá»§a attribute hoáº·c null náº¿u khĂ´ng tá»“n táº¡i
//	*/
//	@SuppressWarnings("unchecked")
//	public static <T> T getRequest(String name) {
//		return (T) request().getAttribute(name);
//	}
//	/**
//	* XĂ³a attribute trong request scope
//	* @param name tĂªn attribute cáº§n xĂ³a
//	*/
//	public static void removerequest(String name) {
//		request().removeAttribute(name);
//	}
	/**
	* Táº¡o attribute trong session scope
	* @param name tĂªn attribute
	* @param value giĂ¡ trá»‹ cá»§a attribute
	*/
	public static void setSession(HttpServletRequest request, String name, Object value) {
		HttpSession session = request.getSession();
		session.setAttribute(name, value);
	}
	/**
	* Ä�á»�c attribute trong session scope
	* @param name tĂªn attribute
	* @return GiĂ¡ trá»‹ cá»§a attribute hoáº·c null náº¿u khĂ´ng tá»“n táº¡i
	*/
	public static Object getSession(HttpServletRequest request, String name){
		HttpSession session = request.getSession();
		return session.getAttribute(name) == null ? null : session.getAttribute(name);
	}
	/**
	* XĂ³a attribute trong session scope
	* @param name tĂªn attribute cáº§n xĂ³a
	*/
	public static void removeSession(HttpServletRequest request,String name){
		HttpSession session = request.getSession();
		 session.removeAttribute(name);
	}
	/**
	* Táº¡o attribute trong application scope
	* @param name tĂªn attribute
	* @param value giĂ¡ trá»‹ cá»§a attribute
	*/
	public static void setApplication(HttpServletRequest request, String name, Object value) {
		ServletContext application = request.getServletContext(); 
		application.setAttribute(name, value);
	}
	/**
	* Ä�á»�c attribute trong application scope
	* @param name tĂªn attribute
	* @return GiĂ¡ trá»‹ cá»§a attribute hoáº·c null náº¿u khĂ´ng tá»“n táº¡i
	*/
	public static Object getApplication(String name, HttpServletRequest request) {
		ServletContext application = request.getServletContext();
		return application.getAttribute(name) == null ? null : application.getAttribute(name);
	}
	/**
	* XĂ³a attribute trong application scope
	* @param name tĂªn attribute cáº§n xĂ³a
	*/
	public static void removeApplication(String name, HttpServletRequest request) {
		ServletContext application = request.getServletContext();
		application.removeAttribute(name);
	}
	
	public static String getLoginUsername(HttpServletRequest request, String name) {
		Object username = getApplication( name, request);
		return username == null ? null : username.toString();
	}
}
