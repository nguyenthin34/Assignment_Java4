package com.poly.helper;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class RRSharer {
	private static Map<Long, HttpServletRequest> reqs = new HashMap<>();
	private static Map<Long, HttpServletResponse> resp = new HashMap<>();
	public static void add(HttpServletRequest request, HttpServletResponse response) {
		reqs.put(Thread.currentThread().getId(), request);
		resp.put(Thread.currentThread().getId(), response);
	}
	public static void remove(HttpServletRequest request, HttpServletResponse response) {
		reqs.put(Thread.currentThread().getId(), request);
		resp.put(Thread.currentThread().getId(), response);
	}
	
	public static HttpServletRequest request() {
		return reqs.get(Thread.currentThread().getId());
	}
	public static HttpServletResponse response() {
		return resp.get(Thread.currentThread().getId());
		}
}
