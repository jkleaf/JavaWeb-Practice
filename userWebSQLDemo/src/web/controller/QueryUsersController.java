package web.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;

/**
 * Servlet implementation class QueryUsersController
 */
@WebServlet("/QueryUsersController")
public class QueryUsersController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public QueryUsersController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		String sitem = request.getParameter("sitem");
		String keyword = request.getParameter("keywordtxt");
		if ("all".equals(sitem)) {
			request.setAttribute("usersList", UserDao.queryAllUsers(keyword));
		} else if ("id".equals(sitem)) {
			request.setAttribute("usersList", UserDao.queryUsersById(keyword));
		} else if ("name".equals(sitem)) {
			request.setAttribute("usersList", UserDao.queryUsersByName(keyword));
		} else if ("pwd".equals(sitem)) {
			request.setAttribute("usersList", UserDao.queryUsersByPwd(keyword));
		} else if ("email".equals(sitem)) {
			request.setAttribute("usersList", UserDao.queryUsersByEmail(keyword));
		} else if ("phonenum".equals(sitem)) {
			request.setAttribute("usersList", UserDao.queryUsersByPhonenum(keyword));
		}
		request.getRequestDispatcher("userManager.jsp").forward(request, response);
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
