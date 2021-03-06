package tool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

public class JsonReader {
	
	public static JSONObject receivePost2Json(HttpServletRequest request) throws IOException, UnsupportedEncodingException {
		BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(),"utf-8"));
		String line = null;
		StringBuilder sb = new StringBuilder();
		while ((line = br.readLine()) != null) {
			sb.append(line);
		}
		System.out.println(sb.toString());
		JSONObject json=JSONObject.fromObject(sb.toString().replaceAll("\\\\", ""));
		return json;
	}
	
	public static String receivePost2String(HttpServletRequest request) throws IOException, UnsupportedEncodingException {
		BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(),"utf-8"));
		String line = null;
		StringBuilder sb = new StringBuilder();
		while ((line = br.readLine()) != null) {
			sb.append(line);
		}
		System.out.println(sb.toString());
		return sb.toString();
	}
	
}
