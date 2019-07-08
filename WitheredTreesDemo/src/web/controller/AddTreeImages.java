package web.controller;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.TreeImage;
import dao.TreeImageDao;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import tool.ImageHandler;
import tool.JsonReader;

/**
 * Servlet implementation class AddTreeImages
 */
@WebServlet("/AddTreeImages")
public class AddTreeImages extends HttpServlet {
	private static final long serialVersionUID = 1L;

//	private String rootPath = "D:/apache-tomcat-9.0.13/webapps/ROOT/WitheredTreesDemo/upload/";
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddTreeImages() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");

		JSONObject json = JsonReader.receivePost2Json(request);
		System.out.println(json);
//		JSONObject jsonObject = new JSONObject();//
		JSONArray jsonArray = json.getJSONArray("treeImages");

		for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject jsonObjectTmp = jsonArray.getJSONObject(i);
			String id = jsonObjectTmp.getString("id");
			String name = jsonObjectTmp.getString("name");
//			String imgFilePath = rootPath + name;
			// longitude
//			Double longitude = Double.valueOf(ImageHandler.GetLng(imgFilePath));
			// latitude
//			Double latitude = Double.valueOf(ImageHandler.GetLat(imgFilePath));
			Double longitude=jsonObjectTmp.getDouble("longitude");
			Double latitude=jsonObjectTmp.getDouble("latitude");
//			System.out.println("read:"+name);
			String u_account = jsonObjectTmp.getString("u_account");
			String record_date = jsonObjectTmp.getString("record_date");
			TreeImage treeImage = new TreeImage(id, name, longitude, latitude, u_account, record_date);
			TreeImageDao.addTreeImage(treeImage);
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
