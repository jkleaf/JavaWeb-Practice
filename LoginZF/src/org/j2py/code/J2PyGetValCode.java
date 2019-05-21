package org.j2py.code;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import javax.print.DocFlavor.STRING;

import org.tools.ImageHandler;
import org.web.getInfo.UserInterface;

import com.sun.org.apache.bcel.internal.generic.NEW;

public class J2PyGetValCode {

	private static String curPath = "D:\\apache-tomcat-9.0.13\\webapps\\ROOT\\LoginZF\\src\\org\\j2py\\code";

	private static String filePath = "D:\\apache-tomcat-9.0.13\\webapps\\ROOT\\LoginZF\\source\\img\\";
//	static {
//		curPath=J2PyGetValCode.class.getResource("");
//	}

	@SuppressWarnings("unused")
	private String command;

	private static String verifyCode;

	public static void executePyCommand(String fileName) throws IOException, InterruptedException {
		String exe = "python3 train.py ";
//		String filePath=" ../model/";
		String command = exe + filePath + fileName;
//		System.out.println(command);
//		command=new String[] {exe,filePath};
		Process process = Runtime.getRuntime().exec(command, null, new File(curPath));
		BufferedInputStream bis = new BufferedInputStream(process.getInputStream());
		BufferedReader br = new BufferedReader(new InputStreamReader(bis));
		verifyCode = br.readLine();
		process.waitFor();
		br.close();
		bis.close();
	}

	public static String getVerifyCode(String verifyCodeUrl) throws IOException {
		ImageHandler imageHandler = new ImageHandler();
//		File imgFile = imageHandler.getImgFile();
		File imgFile=new File(filePath+"verifyImg.gif");
		String imgFileName = imgFile.getName();
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(imgFile);
			out.write(imageHandler.getVerifyImg(verifyCodeUrl));
			executePyCommand(imgFileName);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
		return verifyCode;
	}

	public static void main(String[] args) throws Exception {
		UserInterface ui = new UserInterface();
		System.out.println(J2PyGetValCode.getVerifyCode(ui.getVerifyCodeUrl(ui.makeAccessUrlHead())));
	}
}
