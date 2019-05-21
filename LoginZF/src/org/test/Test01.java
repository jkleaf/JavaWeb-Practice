package org.test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Test01
 */
@WebServlet("/Test01")
public class Test01 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	public void init() throws ServletException {
	}
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Test01() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
//		String name=new String(request.getParameter("name").getBytes("ISO-8859-1"), "UTF-8");
		String name=request.getParameter("name");
		String pwd=request.getParameter("pwd");
		PrintWriter out=response.getWriter();
		HttpSession session=request.getSession();
//		String title="data read from form";
////		out.println("<h1>"+message+"<h1>");
//		String docType = "<!DOCTYPE html> \n";
//        out.println(docType +
//            "<html>\n" +
//            "<head><title>" + title + "</title></head>\n" +
//            "<body bgcolor=\"#f0f0f0\">\n" +
//            "<h1 align=\"center\">" + title + "</h1>\n" +
//            "<ul>\n" +
//            "  <li><b>User</b>£º"
//            + name + "\n" +
//            "  <li><b>Password</b>£º"
//            + request.getParameter("pwd") + "\n" +
//            "</ul>\n" +
//            "</body></html>");
		if(name.equals("rokkkkkk")&&pwd.equals("xyz")) {
			session.setAttribute("name", name);
			session.setAttribute("pwd", pwd);
			session.setMaxInactiveInterval(10*60);
			Cookie cookie=new Cookie("name", name);
			response.addCookie(cookie);
			request.getRequestDispatcher("welcome.jsp").forward(request, response);
		}else {
			response.sendRedirect("login.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
