package com.poly.servlet;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.beanutils.BeanUtils;

import com.poly.dao.ReportDAO;
import com.poly.dao.UserDAO;
import com.poly.dao.VideoDAO;
import com.poly.entity.Report;
import com.poly.entity.Report2;
import com.poly.entity.Report3;
import com.poly.entity.User;
import com.poly.entity.Video;
import com.poly.helper.CookieUtils;
import com.poly.helper.XScope;
@MultipartConfig
@WebServlet({"/AdminServlet", "/AdminServlet/signinad", "/AdminServlet/listvideo"
	, "/AdminServlet/editvideo", "/AdminServlet/insertvideo", "/AdminServlet/updatevideo"
	, "/AdminServlet/resetvideo", "/AdminServlet/removevideo", "/AdminServlet/listuser"
	,"/AdminServlet/edituser","/AdminServlet/updateuser","/AdminServlet/removeuser","/AdminServlet/insertuser"
	,"/AdminServlet/favorites","/AdminServlet/findfv","/AdminServlet/favoritesuser"
	,"/AdminServlet/findfavouser","/AdminServlet/shared","/AdminServlet/findshared"
	,"/AdminServlet/home","/AdminServlet/signout"})
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		VideoDAO dao = new VideoDAO();
		UserDAO daou = new UserDAO();
		if(uri.contains("listvideo") ){
			listVideo(request, response);
		}else if(uri.contains("home")) {
			request.setAttribute("views", "homepage.jsp");
			request.getRequestDispatcher("/admin/home.jsp").forward(request, response);
		}else if(uri.contains("signout")) {
			XScope.setSession(request, "username", null);
			XScope.setSession(request, "user", null);
			response.sendRedirect(request.getContextPath() + "/AdminServlet");
		}else if(uri.contains("editvideo")) {
			String videoid = request.getParameter("VideoID");
			if(videoid != null) {
				Video video = dao.findByID(videoid);
				request.setAttribute("video", video);
			}
			request.setAttribute("views", "editvideo.jsp");
			request.getRequestDispatcher("/admin/home.jsp").forward(request, response);
		}else if(uri.contains("removevideo")) {
			removevideo(request, response);
		}else if(uri.contains("insertuser")) {
			request.setAttribute("views", "adduser.jsp");
			request.getRequestDispatcher("/admin/home.jsp").forward(request, response);
		}else if(uri.contains("insertvideo")) {
			request.setAttribute("views", "addvideo.jsp");
			request.getRequestDispatcher("/admin/home.jsp").forward(request, response);
		}else if(uri.contains("listuser")) {
			listUser(request, response);
		}else if(uri.contains("edituser")) {
			String userid = request.getParameter("UserID");
			if(userid != null) {
				User user = daou.findByID(userid);
				request.setAttribute("user", user);
			}
			request.setAttribute("views", "edituser.jsp");
			request.getRequestDispatcher("/admin/home.jsp").forward(request, response);
		}else if(uri.contains("removeuser")) {
			removeuser(request, response);
		}else if(uri.contains("findfavouser")) {
			findlistfavoriteuser(request, response);
		}else if(uri.contains("favoritesuser")) {
			listfavoriteUser(request, response);
		}else if(uri.contains("favorites")) {
			listfavorite(request, response);
		}else if(uri.contains("findfv")) {
			findlistfavorite(request, response);
		}else if(uri.contains("findshared")) {
			findlistshared(request, response);
		}else if(uri.contains("shared")) {
			listshare(request, response);
		}else{
			request.setAttribute("views", "signin.jsp");
			checkUser(request, response);
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
		}else if(uri.contains("resetvideo")) {
			Video video = new Video();
			request.setAttribute("videore", video);
			request.setAttribute("views", "editvideo.jsp");		
			request.getRequestDispatcher("/admin/home.jsp").forward(request, response);
		}else if(uri.contains("updateuser")) {
			updateuser(request, response);
		}else if(uri.contains("insertuser")) {
			insertuser(request, response);
		}
	}
	
	protected void checkUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = CookieUtils.get("UserAdminID", request);
		if(username.equals("")) {
			response.sendRedirect("/admin/AdminServlet");
			return;
		}
		XScope.setSession(request, "adminnameid", username);
		request.getRequestDispatcher("/admin/home.jsp").forward(request, response);
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
					XScope.setSession(request, "username", username);
					if(user.getAdmins()) {
						if(remember != null) {
							CookieUtils.add("UserAdminID", username, 30*24, response);
						}
						request.setAttribute("message", "Login Success");
						XScope.setSession(request, "user", user);
					}else {
						request.setAttribute("message", "you can't login here");
						request.setAttribute("views", "signin.jsp");
						request.getRequestDispatcher("/admin/home.jsp").forward(request, response);
						return;
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
			response.sendRedirect(request.getContextPath() + "/AdminServlet/home");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	protected void listVideo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		VideoDAO dao = new VideoDAO();
		List<Video> listvideo = dao.findAll();
		if(listvideo != null) {
			request.setAttribute("listvideo", listvideo);
		}else {
			request.setAttribute("listvideo", "List Not Empty");
		}
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
			Video checkvideo = dao.findByID(video.getId());
			if(checkvideo == null) {
				dao.insert(video);
				request.setAttribute("message", "Insert Success");
				request.setAttribute("views", "editvideo.jsp");
			}else {
				request.setAttribute("message", "Video already exists");
				request.setAttribute("views", "editvideo.jsp");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "Insert Failed");
			request.setAttribute("views", "editvideo.jsp");
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
				request.setAttribute("message", "Update Success");
				request.setAttribute("views", "editvideo.jsp");
			} catch (Exception e) {
				request.setAttribute("message", "Update Failed");
				request.setAttribute("views", "editvideo.jsp");
				e.printStackTrace();
			}
		}else {
			request.setAttribute("message", "Video not exists");
			request.setAttribute("views", "editvideo.jsp");
		}
		request.getRequestDispatcher("/admin/home.jsp").forward(request, response);
	}
	protected void removevideo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			VideoDAO dao = new VideoDAO();
			String videoid = request.getParameter("VideoID");
			if(videoid == null) {
				request.setAttribute("message", "Video ID Null!!!");
				request.setAttribute("views", "listvideo.jsp");
			}else {
				Video video = dao.findByID(videoid);
				if(video != null) {
						dao.delete(videoid);
						request.setAttribute("message", "Delete Success!!!");
						request.setAttribute("views", "listvideo.jsp");	
				}else{
					request.setAttribute("message", "Video Not Exists!!!");
					request.setAttribute("views", "listvideo.jsp");	
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "Can't delete video!!!");
			request.setAttribute("views", "listvideo.jsp");	
		}
		listVideo(request, response);
	}
	
	protected void listUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserDAO dao = new UserDAO();
		List<User> listuser = dao.findAll();
		
		if(listuser != null) {
			request.setAttribute("listuser", listuser);
		}else {
			request.setAttribute("message", "List is empty");
		}
		request.setAttribute("views", "listuser.jsp");
		request.getRequestDispatcher("/admin/home.jsp").forward(request, response);
	}
	protected void updateuser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		try {
			UserDAO dao = new UserDAO();
			User cuser = dao.findByID(request.getParameter("id"));
			if(cuser != null) {
				User nuser = new User();
				BeanUtils.populate(nuser, request.getParameterMap());
				dao.update(nuser);
				request.setAttribute("user", nuser);
				request.setAttribute("message", "Update User Success");
				request.setAttribute("views", "edituser.jsp");
			}else {
				request.setAttribute("message", "User not exists");
				request.setAttribute("views", "edituser.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "Update failed");
			request.setAttribute("views", "edituser.jsp");
		}
		request.getRequestDispatcher("/admin/home.jsp").forward(request, response);
	}
	protected void removeuser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			UserDAO dao = new UserDAO();
			String userid = request.getParameter("UserID");
			if(userid == null) {
				request.setAttribute("message", "User ID Null!!!");
			}else {
				User user = dao.findByID(userid);
				if(user != null) {
					dao.delete(userid);
					request.setAttribute("message", "Delete Success!!!");
				}else{
					request.setAttribute("message", "User Not Exists!!!");	
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "Can't delete User!!!");	
		}
		request.setAttribute("views", "listuser.jsp");	
		listUser(request, response);
	}
	protected void listfavorite(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ReportDAO dao = new ReportDAO();	
		List<Report> listreport = dao.favorites();
		if(listreport != null) {
			request.setAttribute("listreport", listreport);
		}else {
			request.setAttribute("message", "List Favorite Not Empty");
		}
		request.setAttribute("views", "RpFavorites.jsp");	
		request.getRequestDispatcher("/admin/home.jsp").forward(request, response);
	}
	protected void findlistfavorite(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		ReportDAO dao = new ReportDAO();
		String keyword = request.getParameter("keyword");
		if(keyword != null) {
			List<Report> listreport = dao.findfavorite(keyword);
				if(listreport != null) {
					request.setAttribute("title", keyword);
					request.setAttribute("listreport", listreport);
				}
		}
		request.setAttribute("views", "RpFavorites.jsp");	
		request.getRequestDispatcher("/admin/home.jsp").forward(request, response);
	}
	protected void listfavoriteUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ReportDAO dao = new ReportDAO();	
		List<Report2> listreport = dao.favoritesuser();
		if(listreport != null) {
			request.setAttribute("listreport2", listreport);
		}else {
			request.setAttribute("message", "List Favorite User Not Empty");
		}
		request.setAttribute("views", "RpFvUser.jsp");	
		request.getRequestDispatcher("/admin/home.jsp").forward(request, response);
	}
	protected void findlistfavoriteuser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		ReportDAO dao = new ReportDAO();
		String keyword = request.getParameter("keyword");
		if(keyword != null) {
			List<Report2> listreport = dao.findfavoriteUser(keyword);
				if(listreport != null) {
					request.setAttribute("title", keyword);
					request.setAttribute("listreport2", listreport);
				}
		}
		request.setAttribute("views", "RpFvUser.jsp");	
		request.getRequestDispatcher("/admin/home.jsp").forward(request, response);
	}
	protected void listshare(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ReportDAO dao = new ReportDAO();	
		List<Report3> listreport = dao.listShare();
		if(listreport != null) {
			request.setAttribute("listreport3", listreport);
		}else {
			request.setAttribute("message", "List Shared Not Empty");
		}
		request.setAttribute("views", "RpShared.jsp");	
		request.getRequestDispatcher("/admin/home.jsp").forward(request, response);
	}
	protected void findlistshared(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		ReportDAO dao = new ReportDAO();
		String keyword = request.getParameter("keyword");
		if(keyword != null) {
			List<Report3> listreport = dao.findShared(keyword);
				if(listreport != null) {
					request.setAttribute("title", keyword);
					request.setAttribute("listreport3", listreport);
				}
		}
		request.setAttribute("views", "RpShared.jsp");	
		request.getRequestDispatcher("/admin/home.jsp").forward(request, response);
	}
	
	protected void insertuser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		try {
			UserDAO dao = new UserDAO();
			User cuser = dao.findByID(request.getParameter("id"));
			if(cuser == null) {
				User nuser = new User();
				BeanUtils.populate(nuser, request.getParameterMap());
				dao.insert(nuser);
				request.setAttribute("user", nuser);
				request.setAttribute("message", "Insert User Success");
				request.setAttribute("views", "adduser.jsp");
			}else {
				request.setAttribute("message", "User exists");
				request.setAttribute("views", "adduser.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "Insert failed");
			request.setAttribute("views", "adduser.jsp");
		}
		request.getRequestDispatcher("/admin/home.jsp").forward(request, response);
	}
}
