package web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.TreeImage;
import dao.TreeImageDao;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import tool.JsonReader;

/**
 * Servlet implementation class QueryTrees
 */
@WebServlet("/QueryTrees")
public class QueryTrees extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private List<TreeImage> treeImages;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QueryTrees() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out=response.getWriter();
		JSONObject json=JsonReader.receivePost2Json(request);
		JSONObject jsonObject=new JSONObject();
		JSONArray jsonArray=new JSONArray();
		System.out.println(json);
		
		String startDate=json.getString("startDate");
		String endDate=json.getString("endDate");
		
		treeImages=TreeImageDao.getImages(startDate, endDate);
		if(treeImages!=null) {
			for (TreeImage treeImage : treeImages) {
				JSONObject jsonObjectTmp=new JSONObject();
				jsonObjectTmp.put("id", treeImage.getId());
				jsonObjectTmp.put("name", treeImage.getName());
				jsonObjectTmp.put("longitude", treeImage.getLongitude());
				jsonObjectTmp.put("latitude", treeImage.getLatitude());
				jsonObjectTmp.put("u_account", treeImage.getU_account());
				jsonObjectTmp.put("record_date", treeImage.getRecord_date());
				jsonArray.add(jsonObjectTmp);
			}
			jsonObject.put("treeImages", jsonArray.toString());
		}
		
		System.out.println("jsonObject:"+jsonObject.toString());
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
