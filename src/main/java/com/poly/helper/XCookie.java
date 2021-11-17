package com.poly.helper;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class XCookie {
	/**
	* Táº¡o vĂ  gá»­i cookie vá»� client
	* @param name tĂªn cookie
	* @param value giĂ¡ trá»‹ cookie
	* @param hours thá»�i háº¡n cookie (giá»�)
	* @return Cookie Ä‘Ă£ gá»­i vá»� client
	*/
	public static Cookie add(String name, String value, int hours, HttpServletResponse response) {
		Cookie cookie = new Cookie(name, value);
		cookie.setMaxAge(hours * 60 * 60);
		cookie.setPath("/");
		response.addCookie(cookie);
		return cookie;
	}
	/**
	* Ä�á»�c giĂ¡ trá»‹ cookie
	* @param name tĂªn cookie cáº§n Ä‘á»�c
	* @param defval giĂ¡ trá»‹ máº·c Ä‘á»‹nh
	* @return GiĂ¡ trá»‹ cookie hoáº·c defval náº¿u cookie khĂ´ng tá»“n táº¡i
	*/

	public static String get(String name, HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		if(cookies != null) {
			for(Cookie cookie : cookies) {
				if(cookie.getName().equalsIgnoreCase(name)) {
					return cookie.getValue();
					}
			}
		}
		return "";
	}
}
