package web.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class MutilUpload
 */
@WebServlet("/MultiUpload")
public class MultiUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private String rootPath="D:\\apache-tomcat-9.0.13\\webapps\\ROOT\\WitheredTreesDemo\\upload\\";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MultiUpload() {
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
		ServletFileUpload sfupload=new ServletFileUpload(new DiskFileItemFactory());
		sfupload.setFileSizeMax(10*1024*1024);
//		sfupload.setSizeMax(1024*1024*1024);
//		String rootPath=request.getServletContext().getRealPath("/upload/");
		System.out.println("rootPath: "+rootPath);
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
        int index = filename.lastIndexOf("\\");  
        filename = filename.substring(index + 1, filename.length());  
        long fileSize = item.getSize();  
        if("".equals(filename) && fileSize == 0){             
            return;  
        }   
        File uploadFile = new File(filePath + File.separator + filename);  
        item.write(uploadFile);
        out.write("上传文件"+filename+"成功!");
    }
	
}
