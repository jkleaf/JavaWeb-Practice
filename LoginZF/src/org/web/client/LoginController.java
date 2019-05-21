package org.web.client;

import java.awt.Desktop;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.jasper.tagplugins.jstl.core.Out;
import org.bean.User;
import org.dao.UserDao;
import org.dao.impl.UserDaoImpl;

import sun.security.jgss.LoginConfigImpl;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginController() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		HttpSession session=request.getSession();
		PrintWriter out=response.getWriter();
		String account = request.getParameter("name");
		String password = request.getParameter("pwd");
		if (account==""||password==""||account==null||password==null) {
			response.sendRedirect("login.jsp");
			return;
		}
		User user=new User(account,password);
		UserDao userDao=new UserDaoImpl();
		int returnCode=userDao.isLogin(user);
//		String accessUrl=userDao.
		if (returnCode==1) {
			session.setAttribute("account", account);
			session.setAttribute("pwd", password);
			session.setMaxInactiveInterval(10*60);
			Cookie cookie=new Cookie("account", account);
			response.addCookie(cookie);
			userDao.insert2UserTable(user);
//			request.getRequestDispatcher("QueryAllUsers").forward(request, response);
			String accessUrl=((UserDaoImpl)userDao).getIndexUrl(user.getAccount());
			System.out.println(accessUrl);
//			response.sendRedirect(accessUrl);
			response.getWriter().println("<script>window.location.href=\""+accessUrl+"\";</script>");
//			request.getRequestDispatcher(accessUrl).forward(request, response);
//			request.setAttribute("returnCode", returnCode);
//			request.getRequestDispatcher("loginSuccess.jsp").forward(request, response);
		} else {
//			request.setAttribute("returnCode", returnCode);
//			response.sendRedirect("login.jsp");
//			request.getRequestDispatcher("loginError.jsp").forward(request, response);
			switch (returnCode) {
				case -1:
					out.println("<script>alert('服务器异常');</script>");
					break;
				case 2:
					out.println("<script>alert(\"验证码错误!\");</script>");
					break;
				case 3:
					out.println("<script>alert(\"密码错误!\");</script>");
					break;
				case 4:
					out.println("<script>alert(\"用户名不存在或未按照要求参加教学活动!\");</script>");
					break;
			}
			response.setHeader("refresh", "0.1;url=login.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
