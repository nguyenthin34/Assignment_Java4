package com.poly.servlet;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.beanutils.BeanUtils;

import com.poly.dao.UserDAO;
import com.poly.dao.VideoDAO;
import com.poly.entity.User;
import com.poly.entity.Video;
import com.poly.helper.CookieUtils;
import com.poly.helper.XScope;
@MultipartConfig
@WebServlet({"/AdminServlet", "/AdminServlet/signinad", "/AdminServlet/listvideo"
	, "/AdminServlet/editvideo", "/AdminServlet/insertvideo", "/AdminServlet/updatevideo"})
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		VideoDAO dao = new VideoDAO();
		if(uri.contains("listvideo") ){
			listVideo(request, response);
		}else if(uri.contains("editvideo")) {
			String videoid = request.getParameter("VideoID");
			if(videoid != null) {
				Video video = dao.findByID(videoid);
				request.setAttribute("video", video);
			}
			request.setAttribute("views", "editvideo.jsp");
			request.getRequestDispatcher("/admin/home.jsp").forward(request, response);
		}else{
			request.setAttribute("views", "signin.jsp");
			request.getRequestDispatcher("/admin/home.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		if(uri.contains("signinad")) {
			signin(request, response);
		}else if(uri.contains("insertvideo")) {
			insertvideo(request, response);
		}else if(uri.contains("updatevideo")) {
			updatevideo(request, response);
		}
	}
	protected void signin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserDAO dao = new UserDAO();
		String username = request.getParameter("userid");
		String pass = request.getParameter("passwords");
		String remember = request.getParameter("remember");
		User user = dao.checklogin(username, pass);
		try {
			if(user != null) {
				if(user.getPasswords().equalsIgnoreCase(pass)) {
					XScope.setApplication(request, "username", username);
					if(user.getAdmins()) {
						if(remember != null) {
							CookieUtils.add("UserAdminID", username, 30*24, response);
						}
						request.setAttribute("message", "Thành Công");
						ServletContext application = request.getServletContext();
						application.setAttribute("user", user);
					}else {
						request.setAttribute("message", "you can't login here");
					}
				}else{
					request.setAttribute("message", "Incorrect password");
					request.setAttribute("views", "signin.jsp");
					request.getRequestDispatcher("/admin/home.jsp").forward(request, response);
					return;
				}
			}else{
				request.setAttribute("message", "The account is not correct");
				request.setAttribute("views", "signin.jsp");
				request.getRequestDispatcher("/admin/home.jsp").forward(request, response);
				return;
			}
			request.setAttribute("views", "homepage.jsp");
			request.getRequestDispatcher("/admin/home.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	protected void listVideo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		VideoDAO dao = new VideoDAO();
		List<Video> listvideo = dao.findpage();
		request.setAttribute("listvideo", listvideo);
		request.setAttribute("views", "listvideo.jsp");
		request.getRequestDispatcher("/admin/home.jsp").forward(request, response);
	}
	protected void insertvideo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		VideoDAO dao = new VideoDAO();
		Video video = new Video();
		String forder = request.getServletContext().getRealPath("/images");
//		Path uploadpath = Paths.get(forder);
		File dir = new File(forder);
		if(dir.exists()) {
			dir.mkdirs();
		}
		// lưu các file upload vào thư mục files
		Part photo = request.getPart("photo_file"); // file hình
		String imagefilename = Path.of(photo.getSubmittedFileName()).getFileName().toString();
		File photofile = new File(dir, photo.getSubmittedFileName());
		photo.write(photofile.getAbsolutePath());
		try {
			video.setPoster(imagefilename);
			BeanUtils.populate(video, request.getParameterMap());
			dao.insert(video);
			request.setAttribute("message", "success");
			request.setAttribute("views", "editvideo.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("/admin/home.jsp").forward(request, response);
	}
	
	protected void updatevideo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		VideoDAO dao = new VideoDAO();
		Video cvideo = dao.findByID(request.getParameter("id"));
		String imagefilename = "";
		if(cvideo != null) {
			Video video = new Video();
			Part photo = request.getPart("photo_file"); // file hình
			imagefilename = Path.of(photo.getSubmittedFileName()).getFileName().toString();
			if(!imagefilename.equals("")) {
				String forder = request.getServletContext().getRealPath("/images");
//				Path uploadpath = Paths.get(forder);
				File dir = new File(forder);
				if(dir.exists()) {
					dir.mkdirs();
				}
				// lưu các file upload vào thư mục files
				
				File photofile = new File(dir, photo.getSubmittedFileName());
				photo.write(photofile.getAbsolutePath());
				video.setPoster(imagefilename);
			}else {
				video.setPoster(cvideo.getPoster());
			}
			try {
				BeanUtils.populate(video, request.getParameterMap());
				dao.update(video);
				request.setAttribute("message", "success");
				request.setAttribute("views", "editvideo.jsp");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			request.setAttribute("message", "Video not exists");
			request.setAttribute("views", "editvideo.jsp");
		}
		request.getRequestDispatcher("/admin/home.jsp").forward(request, response);
	}
	
}
