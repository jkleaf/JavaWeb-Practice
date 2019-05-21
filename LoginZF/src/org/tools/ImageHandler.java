package org.tools;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
//import java.text.SimpleDateFormat;
//import java.util.Date;

import org.web.getInfo.UserInterface;


public class ImageHandler {

	public byte[] getVerifyImg(String url) {
//      Bitmap bitmap = null;
		byte[] bytes = null;
		try {
			HttpHelper request = new HttpHelper(url, "gbk");
//          bitmap  = request.GetHttpGragh();
			bytes = request.GetHttpGragh();
//          Log.d("debug", bitmap.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}

		return bytes;
	}
	
	public String getImgFileName() {
//		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
		String fileUrl="source/img/";
//		String fileNamePrefix=sdf.format(new Date());
		String fileNamePrefix="verifyImg";
		String fileNameSuffix=".gif";
		String fileName=fileUrl+fileNamePrefix+fileNameSuffix;
		return fileName;
	}
	
	public File getImgFile() {
		return new File(getImgFileName());
	}
	
	public static void main(String[] args) throws IOException {
		UserInterface userInterface=new UserInterface();
		ImageHandler imageHandler=new ImageHandler();
		FileOutputStream out=new FileOutputStream(imageHandler.getImgFile());
		out.write(imageHandler.getVerifyImg(userInterface.getVerifyCodeUrl(userInterface.makeAccessUrlHead())));
		out.close();
	}
}
