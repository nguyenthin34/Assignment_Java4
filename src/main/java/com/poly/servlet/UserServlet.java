package com.poly.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import com.poly.dao.FavoriteDAO;
import com.poly.dao.SharedDAO;
import com.poly.dao.UserDAO;
import com.poly.dao.VideoDAO;
import com.poly.entity.*;
import com.poly.helper.CookieUtils;
import com.poly.helper.XScope;


@WebServlet({"/UserServlet","/UserServlet/list", "/UserServlet/register", "/UserServlet/signin","/UserServlet/details", 
	"/UserServlet/likevd", "/UserServlet/move/*", "/UserServlet/myfavorite", "/UserServlet/unlike", "/UserServlet/sendemail"
	, "/UserServlet/share", "/UserServlet/forgot", "/UserServlet/change", "/UserServlet/profile"
	, "/UserServlet/updateprofile", "/UserServlet/logoff"})
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	int page;
	@Override
	public void init() throws ServletException {
		page = 0;
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		UserDAO daou = new UserDAO();
		VideoDAO dao = new VideoDAO();
		XScope.setSession(request, "count", 1);
		if(uri.contains("register")) {
			request.setAttribute("views", "register.jsp");
			request.getRequestDispatcher("/views/home.jsp").forward(request, response);
		}else if(uri.contains("forgot")) {
			request.setAttribute("views", "forgotpassword.jsp");
			request.getRequestDispatcher("/views/home.jsp").forward(request, response);
		}else if(uri.contains("profile")) {
			String username = XScope.getApplication("username", request).toString();
			User u  = daou.findByID(username);
			request.setAttribute("email", u.getEmail());
			request.setAttribute("fullname", u.getFullname());
			request.setAttribute("views", "infomation.jsp");
			request.getRequestDispatcher("/views/home.jsp").forward(request, response);
		}else if(uri.contains("change")) {
			request.setAttribute("views", "changepass.jsp");
			request.getRequestDispatcher("/views/home.jsp").forward(request, response);
		}else if(uri.contains("logoff")) {
			XScope.setApplication(request, "username", null);
			ServletContext application = request.getServletContext();
			application.setAttribute("user", null);
			findAllVideo(request, response);
			request.getRequestDispatcher("/views/home.jsp").forward(request, response);
		}else if(uri.contains("signin")) {
			request.setAttribute("views", "signin.jsp");
			checkUser(request, response);
		}else if(uri.contains("details")) {
			XScope.setApplication(request, "linklike", uri);
			request.setAttribute("views", "details.jsp");
			VideoDetails(request, response);
			request.getRequestDispatcher("/views/home.jsp").forward(request, response);
		}else if(uri.contains("myfavorite")) {
			XScope.setApplication(request, "linklike", uri);
			MyFavorite(request, response);
		}else if(uri.contains("move")) {
			findAllVideoNext(request, response);
			request.getRequestDispatcher("/views/home.jsp").forward(request, response);
		}else if(uri.contains("unlike")) {
				try {
					unLikeVideo(request, response);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}else if(uri.contains("likevd")) {
			likeVideo(request, response);
		}else if(uri.contains("list")) {
			XScope.setApplication(request, "linklike", uri);
			findVideoNotLike(request, response);
			request.setAttribute("views", "article.jsp");
			request.getRequestDispatcher("/views/home.jsp").forward(request, response);
		}else if(uri.contains("share")) {
			String videoid = request.getParameter("VideoID");
			if(videoid == null) {
				request.setAttribute("views", "article.jsp");
				request.getRequestDispatcher("/views/home.jsp").forward(request, response);
				return;
			}
		    request.setAttribute("videoid", videoid);
			request.setAttribute("views", "sendemail.jsp");
			request.getRequestDispatcher("/views/home.jsp").forward(request, response);
		}else {
			findAllVideo(request, response);
			request.getRequestDispatcher("/views/home.jsp").forward(request, response);
		}
}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		if(uri.contains("register")) {
			Registeruser(request, response);
		}else if(uri.contains("signin")) {
			signin(request, response);
		}else if(uri.contains("share")) {
			sendemail(request, response);
		}else if(uri.contains("forgot")) {
			EmailForgotPass(request, response);
		}else if(uri.contains("change")) {
			ChangePassword(request, response);
		}else if(uri.contains("updateprofile")) {
			UpdateUser(request, response);
		}
	}
	
	protected void checkUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = CookieUtils.get("UserID", request);
		if(username.equals("")) {
			request.getRequestDispatcher("/views/home.jsp").forward(request, response);
			return;
		}
		XScope.setSession(request, "userid", username);
		request.getRequestDispatcher("/views/home.jsp").forward(request, response);
	}
	protected void signin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserDAO dao = new UserDAO();
		String username = request.getParameter("userid");
		String pass = request.getParameter("passwords");
		String remember = request.getParameter("remember");
		User user = dao.checklogin(username, pass);
		try {
			if(user != null) {
				if(user.getPasswords().equalsIgnoreCase(pass)) {
					XScope.setApplication(request, "username", username);
					if(remember != null) {
						CookieUtils.add("UserID", username, 30*24, response);
					}
					request.setAttribute("message", "Thành Công");
					ServletContext application = request.getServletContext();
					application.setAttribute("user", user);
				}else{
					request.setAttribute("message", "Sai Mật Khẩu");
					request.setAttribute("views", "signin.jsp");
					request.getRequestDispatcher("/views/home.jsp").forward(request, response);
					return;
				}
			}else{
				request.setAttribute("message", "Sai Tài Khoản");
				request.setAttribute("views", "signin.jsp");
				request.getRequestDispatcher("/views/home.jsp").forward(request, response);
				return;
			}
			request.setAttribute("views", "article.jsp");
			findVideoNotLike(request, response);
			request.getRequestDispatcher("/views/home.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	protected void findAllUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {

			UserDAO dao = new UserDAO();
			List<User> list = dao.findAll();
			request.setAttribute("users", list);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", e.getMessage());
		}
		
	}
	protected void findAllVideo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			VideoDAO dao = new VideoDAO();
			List<Video> list = dao.findpage();
			request.setAttribute("video", list);
			request.setAttribute("views", "article.jsp");

	}
	protected void findVideoNotLike(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			VideoDAO dao = new VideoDAO();
			String userid = XScope.getApplication("username", request).toString();
			List<Video> list = dao.findnotexists(0, userid);
			request.setAttribute("video", list);
	}
	
	protected void likeVideo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		UserDAO daou = new UserDAO();
		VideoDAO daov = new VideoDAO();
		String userid = XScope.getApplication("username", request).toString();
		String videoid = request.getParameter("VideoID");
		User user = daou.findByID(userid);
		Video video = daov.findByID(videoid);
		Favorite favorite = new Favorite(date, user, video);
		FavoriteDAO daof = new FavoriteDAO();
		daof.insert(favorite);
		Object uri = XScope.getApplication("linklike", request);
		if(uri != null) {
			String ur = uri.toString();
			if(ur.contains("details")) {
				VideoDetails(request, response);
				request.setAttribute("views", "details.jsp");
			}else if(ur.contains("list")){
				findVideoNotLike(request, response);
				request.setAttribute("views", "article.jsp");
			}
		}else {
			findVideoNotLike(request, response);
			request.setAttribute("views", "article.jsp");
		}
		
		request.getRequestDispatcher("/views/home.jsp").forward(request, response);
	}
	protected void unLikeVideo(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String userid = XScope.getApplication("username", request).toString();
		String videoid = request.getParameter("VideoID");
		FavoriteDAO daof = new FavoriteDAO();
		Favorite dfv = daof.findLikeVideo(videoid, userid);
		daof.delete(dfv.getId());
		Object uri = XScope.getApplication("linklike", request);
		if(uri != null) {
			String ur = uri.toString();
		if(ur.contains("details")) {
			VideoDetails(request, response);
			request.setAttribute("views", "details.jsp");
			
		}else if(ur.contains("myfavorite")){
			MyFavorite(request, response);
		}
		}else {
			request.setAttribute("views", "article.jsp");
		}
		request.getRequestDispatcher("/views/home.jsp").forward(request, response);
		
	}
	protected void VideoDetails(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		VideoDAO dao = new VideoDAO();
		FavoriteDAO daof = new FavoriteDAO();
		String videoId = request.getParameter("VideoID");
		Video video = dao.findByID(videoId);
		List<Video> listvideo = dao.findAll();
		LinkedList<Video> list = new LinkedList<>();
		HttpSession session = request.getSession();
		String userId = XScope.getLoginUsername(request, "username");
		session.setAttribute("nameytb", video);
		String testNameCookie = CookieUtils.get(videoId, request);
		if(testNameCookie != null) {
			CookieUtils.add(videoId, videoId, 0, response);
			CookieUtils.add(videoId, videoId, 30*24, response);
		}{
			CookieUtils.add(videoId, videoId, 30*24, response);
		}
		for(Video vd : listvideo) {
			String vdck = CookieUtils.get(vd.getId(), request);
			if(vdck != "") {
				list.add(vd);
			}
		}
		if(userId != null){
			Favorite fv = daof.findLikeVideoCookie(videoId, userId);
			if(fv != null) {
				request.setAttribute("chk", true);
			}
		}
		HttpSession session2 = request.getSession();
		session2.setAttribute("list", list);
//		request.getRequestDispatcher("/views/home.jsp").forward(request, response);
	}
	protected void findAllVideoNext(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String uri = request.getRequestURI();
				VideoDAO dao = new VideoDAO();
				List<Video> lst = dao.findAll();			
				
				int maxpage = (int) (dao.countvideo()/6);
				List<Video> list = null;
					if(uri.contains("next")) {
						if(page <= maxpage) {
							list = dao.findpage(page);
							page++;
							
							request.setAttribute("index", page);
						}
						
					}else if(uri.contains("prev")) {
						if(page >= 0) {
							list = dao.findpage(page);
							page--;
							request.setAttribute("index", page);
						}else {
							page = maxpage;
							list = dao.findpage(page);
							request.setAttribute("index", page - 1);
						}
					}
				request.setAttribute("video", list);
				request.setAttribute("views", "article.jsp");
	}
//	protected void findAllVideoPrev(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		VideoDAO dao = new VideoDAO();
//		Long maxvideo = dao.countvideo();
//		int du = (int) (maxvideo % 6);
//		int vitri = (int) (maxvideo - du);
//		try {	
//			int prev = Integer.valueOf(request.getParameter("page"));
//			
//			if(prev < 0) {
//				request.setAttribute("index", vitri);
//				List<Video> list = dao.findpage(vitri);
//				request.setAttribute("video", list);		
//			}else {
//				List<Video> list = dao.findpage(prev);
//				request.setAttribute("video", list);
//				request.setAttribute("index", prev - 6 );
//			}
//					
//		} catch (Exception e) {
//			e.printStackTrace();
//			request.setAttribute("error", e.getMessage());
//		}
//	}
	
	protected void MyFavorite(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		VideoDAO dao = new VideoDAO();
		String userid = XScope.getApplication("username", request).toString();
		List<Video> list = dao.findmyfavorite(userid);
		request.setAttribute("video", list);
		request.setAttribute("quantity", list.size());
		request.setAttribute("views", "article.jsp");
		request.setAttribute("article", true);
		request.getRequestDispatcher("/views/home.jsp").forward(request, response);
	}
	
	protected void sendemail(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			final String username = "nguyenthin34hd@gmail.com";
			final String password = "thin280101";
			String videoid = request.getParameter("VideoID");
			VideoDAO daov = new VideoDAO();
			Video video = daov.findByID(videoid);
			UserDAO daou = new UserDAO();
			String userid = XScope.getApplication("username", request).toString();
			User user = daou.findByID(userid);
			Date date = new Date();
			Properties prop = new Properties();
			prop.put("mail.smtp.host", "smtp.gmail.com");
			prop.put("mail.smtp.port", "587");
			prop.put("mail.smtp.auth", "true");
			prop.put("mail.smtp.starttls.enable", "true");
			Session session = Session.getInstance(prop, new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
			});
			String emailTo = request.getParameter("to");
			String emailSubject = "Video Remix: Share Video";
			String emailContent = "http://localhost:8080/Assigment/UserServlet/details?VideoID=" + videoid;
			try {
				MimeMessage message = new MimeMessage(session);
				message.setFrom(new InternetAddress(username));
				message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailTo));
				message.setSubject(emailSubject, "utf-8");
				message.setText(emailContent, "utf-8", "html");
				Transport.send(message);
				System.out.println("Done");
			} catch (Exception e) {
				e.printStackTrace();
			}
			Shared shared = new Shared();
			shared.setEmail(emailTo);
			shared.setSharedDate(date);
			shared.setUser(user);
			shared.setVideo(video);
			SharedDAO daos = new SharedDAO();
			daos.insert(shared);
			findVideoNotLike(request, response);
			request.setAttribute("views", "article.jsp");
			request.getRequestDispatcher("/views/home.jsp").forward(request, response);
		}
	protected void EmailForgotPass(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			final String username = "nguyenthin34hd@gmail.com";
			final String password = "thin280101";
			String userf = request.getParameter("username");
			String emailf = request.getParameter("email");
			UserDAO daou = new UserDAO();
			User user = daou.findByEmail(userf, emailf);
			if(user != null) {
				Properties prop = new Properties();
				prop.put("mail.smtp.host", "smtp.gmail.com");
				prop.put("mail.smtp.port", "587");
				prop.put("mail.smtp.auth", "true");
				prop.put("mail.smtp.starttls.enable", "true");
				Session session = Session.getInstance(prop, new Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				});
				String emailSubject = "Video Remix: Send Your Password";
				String emailContent = "Dear " + user.getFullname() + ", "
						+ "Your PassWord: " + user.getPasswords() + ".";
				try {
					MimeMessage message = new MimeMessage(session);
					message.setFrom(new InternetAddress(username));
					message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailf));
					message.setSubject(emailSubject, "utf-8");
					message.setText(emailContent, "utf-8", "html");
					Transport.send(message);
					System.out.println("Done");
				} catch (Exception e) {
					e.printStackTrace();
				}
				request.setAttribute("message", "Successful, please check your email");
				request.setAttribute("views", "forgotpassword.jsp");
				request.getRequestDispatcher("/views/home.jsp").forward(request, response);
			}else {
				request.setAttribute("message", "Invalid information");
				request.setAttribute("views", "forgotpassword.jsp");
				request.getRequestDispatcher("/views/home.jsp").forward(request, response);
			}
		}
	protected void ChangePassword(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			String username = request.getParameter("usern");
			String pass = request.getParameter("currentpassword");
			UserDAO dao = new UserDAO();
			User cuser = dao.findByPass(username, pass);
			if(cuser == null) {
				request.setAttribute("message", "Incorrect Account or Password");
				request.setAttribute("views", "changepass.jsp");
				request.getRequestDispatcher("/views/home.jsp").forward(request, response);
			}else {
				String newpass = request.getParameter("confirmPassword");
				cuser.setPasswords(newpass);
				dao.update(cuser);
				request.setAttribute("message", "Change Password Success");
				request.setAttribute("views", "changepass.jsp");
				request.getRequestDispatcher("/views/home.jsp").forward(request, response);
			}
	}
	@SuppressWarnings("unused")
	protected void Registeruser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		try {
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
			User user = new User();
			BeanUtils.populate(user, request.getParameterMap());
			user.setAdmins(false);
			if(user != null) {
				UserDAO dao = new UserDAO();
				User ucheck = dao.findByID(user.getId());
				if(ucheck == null) {
					dao.insert(user);
					Mail(request, response, user);
					request.setAttribute("message", "Register Success!!!");
					request.setAttribute("views", "register.jsp");
					request.getRequestDispatcher("/views/home.jsp").forward(request, response);
				}else {
					request.setAttribute("message", "username already exists!!!");
					request.setAttribute("views", "register.jsp");
					request.getRequestDispatcher("/views/home.jsp").forward(request, response);
				}	
			}else {
				request.setAttribute("message", "Register unsuccessful!!!");
				request.setAttribute("views", "register.jsp");
				request.getRequestDispatcher("/views/home.jsp").forward(request, response);
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	protected void Mail(HttpServletRequest request, HttpServletResponse response, User user)
			throws ServletException, IOException{
		final String username = "nguyenthin34hd@gmail.com";
		final String password = "thin280101";
			Properties prop = new Properties();
			prop.put("mail.smtp.host", "smtp.gmail.com");
			prop.put("mail.smtp.port", "587");
			prop.put("mail.smtp.auth", "true");
			prop.put("mail.smtp.starttls.enable", "true");
			Session session = Session.getInstance(prop, new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
			});
			String emailSubject = "Video Remix: Welcome to the world of remix music!";
			String emailContent = "Dear " + user.getFullname() + ", "
					+ "Thank you for signing up to become a member of music remix.\r\n"
					+ "Wish you have a good experience with our products.\r\n"
					+ "--Admin--";
			try {
				MimeMessage message = new MimeMessage(session);
				message.setFrom(new InternetAddress(username));
				message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(user.getEmail()));
				message.setSubject(emailSubject, "utf-8");
				message.setText(emailContent, "utf-8", "html");
				Transport.send(message);
				System.out.println("Done");
			} catch (Exception e) {
				e.printStackTrace();
			}
		
	}
	protected void UpdateUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String username = XScope.getApplication("username", request).toString();
		UserDAO dao = new UserDAO();
		User cuser = dao.findByID(username);
		User user = new User();
		try {
			if(cuser!=null) {
				BeanUtils.populate(user, request.getParameterMap());
				user.setId(username);
				user.setAdmins(cuser.getAdmins());
				user.setPasswords(cuser.getPasswords());
				dao.update(user);
				request.setAttribute("message", "Udpate Success!!!");
				request.setAttribute("email", user.getEmail());
				request.setAttribute("fullname", user.getFullname());
				request.setAttribute("views", "infomation.jsp");
				request.getRequestDispatcher("/views/home.jsp").forward(request, response);
			}else {
				request.setAttribute("message", "Udpate Failed!!!");
				request.setAttribute("views", "infomation.jsp");
				request.getRequestDispatcher("/views/home.jsp").forward(request, response);
			}
		} catch (Exception e) {
			request.setAttribute("message", "Udpate Failed!!!");
			request.setAttribute("views", "infomation.jsp");
			request.getRequestDispatcher("/views/home.jsp").forward(request, response);
			e.printStackTrace();
		}
	}
}
