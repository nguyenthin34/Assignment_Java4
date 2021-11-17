package com.poly.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poly.dao.VideoDAO;
import com.poly.entity.Video;

@WebServlet({ "/ScrollKeyServlet", "/ScrollKeyServlet/next"})
public class ScrollKeyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int count = 1;
		String uri = request.getRequestURI();
		VideoDAO dao = new VideoDAO();
		if(uri.contains("next")) {
			count += 6;
			List<Video> list = dao.findpage(count);
			request.setAttribute("video", list);
		}
		request.getRequestDispatcher("/UserServlet").forward(request, response);
	}
}
