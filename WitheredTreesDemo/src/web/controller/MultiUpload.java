package web.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.sun.org.apache.bcel.internal.generic.NEW;

import bean.TreeImage;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import tool.JsonReader;

/**
 * Servlet implementation class MutilUpload
 */
@WebServlet("/MultiUpload")
public class MultiUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private String rootPath;
//	private String rootPath="D:\\apache-tomcat-9.0.13\\webapps\\ROOT\\WitheredTreesDemo\\upload\\";
//	private String rootPath="/upload/";
//	private String rootPath="/var/lib/tomcat/webapps/WitheredTreesDemo/upload/";
	private Properties prop;
	
	private List<TreeImage> treeImages;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MultiUpload() {
        super();
        prop=new Properties();
        try {
			prop.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("web/controller/config.properties"));
			rootPath=prop.getProperty("rootPath");
        } catch (IOException e) {
			e.printStackTrace();
		}
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		ServletFileUpload sfupload=new ServletFileUpload(new DiskFileItemFactory());
		
		sfupload.setFileSizeMax(10*1024*1024);
		sfupload.setSizeMax(50*1024*1024);
//		sfupload.setSizeMax(1024*1024*1024);
//		String rootPath=request.getServletContext().getRealPath("/upload/");
//		System.out.println("rootPath: "+rootPath);
		System.out.println(request);
		System.out.println(request.getRemoteAddr());
		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		PrintWriter out=response.getWriter();
		try {
			Map<String, List<FileItem>> map = sfupload.parseParameterMap(request);
			for (Map.Entry<String, List<FileItem>> entry : map.entrySet()) {
				List<FileItem> list = entry.getValue();
				for(FileItem item : list) {     
	                if(item.isFormField()) { 
//	                	processFormField(item);//处理表单
	                } else { 
	                	processUploadFile(rootPath, item, out);//处理上传文件
	                }  
	            }  
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
//	private void processFormField(FileItem item) throws Exception {  
//        String name = item.getFieldName();  
//        if (name.equals("username") || name.equals("psw")) {
//        	String value = item.getString(); 
//            System.out.println("name = " + name);           
//            System.out.println("value = " + value); 
//        }          
//    }

	private void processUploadFile(String filePath, FileItem item, PrintWriter out) throws Exception {  
        System.out.println("be invoked");
		String filename = item.getName();         
        System.out.println("filename: " + filename);  
        int index = filename.lastIndexOf(File.separator);//\\  
        filename = filename.substring(index + 1, filename.length());  
        long fileSize = item.getSize();  
        if("".equals(filename) && fileSize == 0){
        	out.write("文件"+filename+"为空!");
        	return;  
        }   
        File uploadFile = new File(filePath + File.separator + filename);
        //id u_account 
//        uploadFile.setWritable(true, false);//linux java设置可写权限
        item.write(uploadFile);
        out.write("上传文件"+filename+"成功!");
        out.flush();
    }
	
}
