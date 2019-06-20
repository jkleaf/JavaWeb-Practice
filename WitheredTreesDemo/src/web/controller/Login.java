package web.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.jws.soap.SOAPBinding.Use;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.User;
import dao.UserDao;
import net.sf.json.JSONObject;
import service.UserService;
import tool.JsonReader;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
//		/** 设置响应头允许ajax跨域访问 **/
//		response.setHeader("Access-Control-Allow-Origin", "*");
//		/* 星号表示所有的异域请求都可以接受， */
//		response.setHeader("Access-Control-Allow-Methods", "GET,POST");
		PrintWriter out =response.getWriter();
		JSONObject json=JsonReader.receivePost2Json(request);
		JSONObject jsonObject=new JSONObject();
		User user=null;
		System.out.println(json);

		User loginUser=(User) JSONObject.toBean(json, User.class);//
		
//		if(loginUser!=null) {
//		if(loginUser!=null)
//			user=UserDao.getUser(loginUser.getAccount());
		
		if(loginUser!=null&&UserDao.isLogin(loginUser)){
			user=UserDao.getUser(loginUser.getAccount());
			jsonObject.put("user", JSONObject.fromObject(user));//object->json
//			jsonObject.put("message", JSONObject.fromObject("用户登录成功!"));
		}else{
//			jsonObject.put("message", JSONObject.fromObject("用户登录失败!用户名或密码错误!"));
			jsonObject.put("user", JSONObject.fromObject("{}"));
		}
		System.out.println("jsonObject:"+jsonObject.toString());
//		}
		out.write(jsonObject.toString());
		out.flush();
		
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
