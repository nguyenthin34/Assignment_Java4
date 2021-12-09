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
import com.poly.dao.ReportDAO;
import com.poly.dao.SharedDAO;
import com.poly.dao.UserDAO;
import com.poly.dao.VideoDAO;
import com.poly.entity.*;
import com.poly.helper.CookieUtils;
import com.poly.helper.XScope;


@WebServlet({"/","/UserServlet","/UserServlet/list", "/UserServlet/register", "/signin","/details", 
	"/UserServlet/likevd", "/UserServlet/move/*", "/UserServlet/myfavorite", "/UserServlet/unlike", "/UserServlet/sendemail"
	, "/UserServlet/share", "/UserServlet/forgot", "/UserServlet/change", "/UserServlet/profile"
	, "/UserServlet/updateprofile", "/logoff", "/findtitle", "/UserServlet/home"})
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		System.out.println(request.getContextPath());
		selectTop5(request, response);
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
			String username = XScope.getSession( request, "username").toString();
			User u  = daou.findByID(username);
			request.setAttribute("profile", u);
			request.setAttribute("views", "infomation.jsp");
			request.getRequestDispatcher("/views/home.jsp").forward(request, response);
		}else if(uri.contains("change")) {
			request.setAttribute("views", "changepass.jsp");
			request.getRequestDispatcher("/views/home.jsp").forward(request, response);
		}else if(uri.contains("logoff")) {
			XScope.setSession(request, "username", null);
			XScope.setSession(request, "user", null);
			response.sendRedirect(request.getContextPath() + "/");
		}else if(uri.contains("signin")) {
			request.setAttribute("views", "signin.jsp");
			checkUser(request, response);
		}else if(uri.contains("details")) {
			increaseviews(request, response);
			VideoDetails(request, response);
			request.getRequestDispatcher("/views/home.jsp").forward(request, response);
		}else if(uri.contains("myfavorite")) {
			MyFavorite(request, response);
		}else if(uri.contains("move")) {
			findAllVideoNext(request, response);
		}else if(uri.contains("home")) {
			findVideoNotLike(request, response);
			request.getRequestDispatcher("/views/home.jsp").forward(request, response);
		}else if(uri.contains("unlike")) {
				try {
					unLikeVideo(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
		}else if(uri.contains("likevd")) {
			likeVideo(request, response);
		}else if(uri.contains("list")) {
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
		}else if(uri.contains("findtitle")){
			findByVideoTitle(request, response);
		}else {
			findAllVideo(request, response);
			request.getRequestDispatcher("/views/home.jsp").forward(request, response);
		}
		
}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String uri = request.getRequestURI();
		selectTop5(request, response);
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
		User user = dao.findByID(username);
		HttpSession session = request.getSession();
		try {
			if(user != null) {
				if(user.getPasswords().equals(pass)) {
					XScope.setSession(request, "username", username);
					if(remember != null) {
						CookieUtils.add("UserID", username, 30*24, response);
					}
					session.setAttribute("user", user);
				}else{
					request.setAttribute("message", "Password is not exists!!!");
					request.setAttribute("views", "signin.jsp");
					request.getRequestDispatcher("/views/home.jsp").forward(request, response);
					return;
				}
			}else{
				request.setAttribute("message", "Account is not exists!!!");
				request.setAttribute("views", "signin.jsp");
				request.getRequestDispatcher("/views/home.jsp").forward(request, response);
				return;
			}
			
//			request.getRequestDispatcher("/views/home.jsp").forward(request, response);
			request.setAttribute("message", "Login Success!!!");
			response.sendRedirect( request.getContextPath() + "/UserServlet/home");
		} catch (Exception e) {
			request.setAttribute("message", "Account is not exists!!!");
			request.setAttribute("views", "signin.jsp");
			request.getRequestDispatcher("/views/home.jsp").forward(request, response);
			e.printStackTrace();
		}
	}
	protected void findAllVideo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			VideoDAO dao = new VideoDAO();
			List<Video> list = dao.findpage();
			if(list == null) {
				request.setAttribute("message", "No videos yet!");
			}else {
				request.setAttribute("video", list);
			}
			request.setAttribute("views", "article.jsp");
	}
	protected void findVideoNotLike(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			try {
				VideoDAO dao = new VideoDAO();
				String userid = XScope.getSession(request, "username").toString();
				List<Video> list = dao.findnotexists(0, userid);
				if(list == null) {
					request.setAttribute("message", "No videos yet!");
				}else {
					request.setAttribute("video", list);
				}
				request.setAttribute("views", "article.jsp");
			} catch (Exception e) {
				// TODO: handle exception
			}
	}
	
	protected void likeVideo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String page = request.getParameter("page");
		Date date = new Date();
		UserDAO daou = new UserDAO();
		VideoDAO daov = new VideoDAO();
		String userid = XScope.getSession(request, "username").toString();
		String videoid = request.getParameter("VideoID");
		User user = daou.findByID(userid);
		Video video = daov.findByID(videoid);
		FavoriteDAO daof = new FavoriteDAO();
		Favorite fv = daof.findLikeVideo(videoid, userid);
		if(fv != null) {
			request.setAttribute("message", "You already liked this song!");
		}else {
			Favorite favorite = new Favorite(date, user, video);
			daof.insert(favorite);
			request.setAttribute("message", "Thank you for liking the song!");
		}
		if(page.equals("article")) {
			response.sendRedirect(request.getContextPath() + "/UserServlet/home");
		}else {
			response.sendRedirect(request.getContextPath() + "/details?VideoID="+videoid + "&page=details");
		}
//		findVideoNotLike(request, response);
//		request.setAttribute("views", "article.jsp");
//		request.getRequestDispatcher("/views/home.jsp").forward(request, response);
	}
	protected void unLikeVideo(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String userid = XScope.getSession(request, "username").toString();
		String videoid = request.getParameter("VideoID");
		FavoriteDAO daof = new FavoriteDAO();
		Favorite dfv = daof.findLikeVideo(videoid, userid);
		String page = request.getParameter("page");
		if(dfv == null) {
			request.setAttribute("message", "You already Unliked this song!");
		}else {
			daof.delete(dfv.getId());
			findVideoNotLike(request, response);
			request.setAttribute("message", "You already liked this song!");
		}	
		if(page.equals("article")) {
			response.sendRedirect(request.getContextPath() + "/UserServlet/myfavorite");
		}else {
			response.sendRedirect(request.getContextPath() + "/details?VideoID="+videoid+ "&page=details");
		}
	}
	protected void VideoDetails(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			VideoDAO dao = new VideoDAO();
			FavoriteDAO daof = new FavoriteDAO();
			String videoId = request.getParameter("VideoID");
			List<Video> listvideo = dao.findAll();
			LinkedList<Video> list = new LinkedList<>();
			if(XScope.getSession(request, "username") != null) {
				String userId = XScope.getSession(request, "username").toString();
				UserDAO daou = new UserDAO();
				User user = daou.findByID(userId);
				if(user != null){
					Favorite fv = daof.findLikeVideo(videoId, userId);
					if(fv == null) {
						request.setAttribute("views", "details.jsp");
					}else {
						request.setAttribute("views", "details2.jsp");
					}
				}
			}else {
				request.setAttribute("views", "details.jsp");
			}
			Video video = dao.findByID(videoId);
			request.setAttribute("nameytb", video);
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
			request.setAttribute("list", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
//		request.getRequestDispatcher("/views/home.jsp").forward(request, response);
	}
	protected void findAllVideoNext(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
				request.setCharacterEncoding("utf-8");
				String uri = request.getRequestURI();
				VideoDAO dao = new VideoDAO();
				ArrayList<Video> nolst =  dao.findAll();
				int maxpage = nolst.size() / 6;
				int prd = nolst.size() % 6;
				List<Video> list;
				String islogin = request.getParameter("islogin");
				String id = request.getParameter("VideoID");
				if(uri.contains("next")) {
					if(!id.equals("") && islogin.equals("no")) {
						int index = 0;
						for(Video x : nolst) {
							if(x.getId().equalsIgnoreCase(id)) {
								index =  nolst.indexOf(x);
							};
						}
						if(index + 1  < nolst.size()) {
							list = dao.findpage(index + 1, 6);
							request.setAttribute("video", list);
						}else if(index + 1 == maxpage*6) {
							list = dao.findpage(index + 1, prd);
							request.setAttribute("video", list);
						}else {
							list =  dao.findpage(0, 6);
							request.setAttribute("video", list);
						}
						request.setAttribute("views", "article.jsp");
						request.getRequestDispatcher("/views/home.jsp").forward(request, response);
					}else if(!id.equals("") && islogin.equals("yes")) {
						String id2 = request.getParameter("VideoID");
						String userid = XScope.getSession( request, "username").toString();
						ArrayList<Video> yeslst = dao.findallnotexists(userid);
						int maxpage2 = yeslst.size() / 6;
						int prd2 = yeslst.size() % 6;
						int index = 0;
						for(Video x : yeslst) {
							if(x.getId().equals(id2)) {
								index =  yeslst.indexOf(x);
							};
						}
						if(index + 1  < yeslst.size()) {
							list = dao.findpageallnotexists(userid, index + 1, 6);
							request.setAttribute("video", list);
						}else if(index + 1 == maxpage2 * 6) {
							list = dao.findpageallnotexists(userid, index + 1, prd2);
							request.setAttribute("video", list);
						}else {
							list =  dao.findpageallnotexists(userid, 0, 6);
							request.setAttribute("video", list);
						}
						request.setAttribute("views", "article.jsp");
						request.getRequestDispatcher("/views/home.jsp").forward(request, response);
					}
				}else if(uri.contains("prev")) {
					if(!id.equals("") && islogin.equals("no")) {
						int index = 0;
						for(Video x : nolst) {
							if(x.getId().equalsIgnoreCase(id)) {
								index =  nolst.indexOf(x);
							};
						}
						if(index - 6  >= 0) {
							list = dao.findpage(index - 6, 6);
							request.setAttribute("video", list);
						}else if(index - 6 < 0) {
							list = dao.findpage(nolst.size() - prd , prd);
							request.setAttribute("video", list);
						}else {
							list =  dao.findpage(0, 6);
							request.setAttribute("video", list);	
						}
					request.setAttribute("views", "article.jsp");
					request.getRequestDispatcher("/views/home.jsp").forward(request, response);
					}else if(!id.equals("") && islogin.equals("yes")) {
				String id2 = request.getParameter("VideoID");
				String userid = XScope.getSession( request, "username").toString();
				ArrayList<Video> yeslst = dao.findallnotexists(userid);
				int prd2 = yeslst.size() % 6;
				int index = 0;
				for(Video x : yeslst) {
					if(x.getId().equals(id2)) {
						index =  yeslst.indexOf(x);
					};
				}
				if(index - 6  >= 0) {
					list = dao.findpageallnotexists(userid, index - 6, 6);
					request.setAttribute("video", list);
				}else if(index - 6 < 0) {
					list = dao.findpageallnotexists(userid, yeslst.size() - prd2 , prd2);
					request.setAttribute("video", list);
				}else {
					list =  dao.findpageallnotexists(userid, 0, 6);
					request.setAttribute("video", list);
				}
				request.setAttribute("views", "article.jsp");
				request.getRequestDispatcher("/views/home.jsp").forward(request, response);
			}
		}
	}
	protected void MyFavorite(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		VideoDAO dao = new VideoDAO();
		String userid = XScope.getSession(request, "username").toString();
		List<Video> list = dao.findmyfavorite(userid);
		if(list == null) {
			request.setAttribute("message", "No favorites videos yet!");
		}else {
			request.setAttribute("video", list);
			request.setAttribute("quantity", list.size());
		}
		request.setAttribute("views", "Myfavorite.jsp");
		request.getRequestDispatcher("/views/home.jsp").forward(request, response);
	}
	protected void sendemail(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			final String username = "nguyenthin34hd@gmail.com";
			final String password = "Thin280101";
			try {
				String videoid = request.getParameter("VideoID");
				VideoDAO daov = new VideoDAO();
				Video video = daov.findByID(videoid);
				UserDAO daou = new UserDAO();
				String userid = XScope.getSession( request, "username").toString();
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
					MimeMessage message = new MimeMessage(session);
					message.setFrom(new InternetAddress(username));
					message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailTo));
					message.setSubject(emailSubject, "utf-8");
					message.setText(emailContent, "utf-8", "html");
					Transport.send(message);
				Shared shared = new Shared();
				shared.setEmail(emailTo);
				shared.setSharedDate(date);
				shared.setUser(user);
				shared.setVideo(video);
				SharedDAO daos = new SharedDAO();
				daos.insert(shared);
				findVideoNotLike(request, response);
				request.setAttribute("views", "article.jsp");
				request.setAttribute("message", "Send Email Success!");
				request.getRequestDispatcher("/views/home.jsp").forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("message", "Error!");
			}
		
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
				request.setAttribute("message", "Invalid information!!!");
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
			}else {
				String newpass = request.getParameter("confirmPassword");
				cuser.setPasswords(newpass);
				dao.update(cuser);
				request.setAttribute("message", "Change Password Success");
			}
			request.setAttribute("views", "changepass.jsp");
			request.getRequestDispatcher("/views/home.jsp").forward(request, response);
	}
	@SuppressWarnings("unused")
	protected void Registeruser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		try {
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
			User user = new User();;
			BeanUtils.populate(user, request.getParameterMap());
			if(user.getId().equals("") || user.getEmail().equals("") || user.getFullname().equals("") || user.getPasswords().equals("")) {
				request.setAttribute("message", "Did you leave something blank?");
			}
			user.setAdmins(false);
			if(user != null) {
				UserDAO dao = new UserDAO();
				User ucheck = dao.findByID(user.getId());
				if(ucheck == null) {
					dao.insert(user);
					Mail(request, response, user);
					request.setAttribute("message", "Register Success!!!");
				}else {
					request.setAttribute("message", "Username already exists!!!");
				}	
			}else {
				request.setAttribute("message", "Register unsuccessful!!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "Register unsuccessful!!!");
		}
		request.setAttribute("views", "register.jsp");
		request.getRequestDispatcher("/views/home.jsp").forward(request, response);
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
				request.setAttribute("message", "Send Mail Success!");
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("message", "Send Mail Failed!");
			}
		
	}
	protected void UpdateUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String username = XScope.getSession( request, "username").toString();
		UserDAO dao = new UserDAO();
		User cuser = dao.findByID(username);
		User user = new User();
		try {
			if(cuser!=null) {
				BeanUtils.populate(user, request.getParameterMap());
				if(!user.getEmail().equals(cuser.getEmail())) {
					request.setAttribute("message", "Email Exists!!!");
				}else {
					user.setId(username);
					user.setAdmins(cuser.getAdmins());
					user.setPasswords(cuser.getPasswords());
					dao.update(user);
					request.setAttribute("message", "Udpate Success!!!");
					request.setAttribute("email", user.getEmail());
					request.setAttribute("fullname", user.getFullname());
				}
				
			}else {
				request.setAttribute("message", "Udpate Failed!!!");
			}
		} catch (Exception e) {
			request.setAttribute("message", "Udpate Failed!!!");
			e.printStackTrace();
		}
		request.setAttribute("views", "infomation.jsp");
		request.getRequestDispatcher("/views/home.jsp").forward(request, response);
	}
	protected void findByVideoTitle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String title = request.getParameter("title");
		VideoDAO dao = new VideoDAO();
		List<Video> listttvideo = new ArrayList<Video>();
			listttvideo = dao.findbyVideoTitle(title);
			if(listttvideo != null) {
				request.setAttribute("video", listttvideo);
			}else {
		request.setAttribute("message", "There is no video you are looking for!!!");
			}
		request.setAttribute("views", "article.jsp");
		request.getRequestDispatcher("/views/home.jsp").forward(request, response);
	}
	protected void selectTop5(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		try {
			VideoDAO dao = new VideoDAO();
			ReportDAO daor = new ReportDAO();
			List<Video> topview = dao.topview();
			List<TopLike> toplike = daor.toplike();
			List<TopShare> topshare = daor.topshare();
			if(topview != null) {
				request.setAttribute("topview", topview);
			}
			if(toplike != null) {
				request.setAttribute("toplike", toplike);
			}
			if(topshare != null) {
				request.setAttribute("topshare", topshare);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	protected void increaseviews(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
			try {
				String videoid = request.getParameter("VideoID");
				VideoDAO dao = new VideoDAO();
				Video cvideo = dao.findByID(videoid);
				if(cvideo != null) {
					cvideo.setViews(cvideo.getViews() + 1);
					dao.update(cvideo);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	
}
