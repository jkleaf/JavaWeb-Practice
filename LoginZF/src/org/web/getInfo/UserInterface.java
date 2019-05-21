package org.web.getInfo;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.j2py.code.J2PyGetValCode;
import org.tools.HttpHelper;
import org.tools.HttpResponse;

public class UserInterface {

	private final String inHost = "http://jwgl.fafu.edu.cn";

	protected String viewState;

	protected String viewStateGenerator;

	private static final String LoginPage = "default2.aspx";
	
	private String accessUrlHead;

//	private static final String ModifyPage = "mmxg.aspx";

	public String makeToken() {
		String randomString = "abcdefghijklmnopqrstuvwxyz12345";
		String token = "fucku";//
		Random random = new Random(System.currentTimeMillis());
		for (int i = 0; i < 19; i++) {
			token += randomString.charAt(random.nextInt(randomString.length()));
		}
		return token;
	}

	public String makeAccessUrlHead() {
		String token = makeToken();
//        return String.format(Locale.getDefault(), "%s/(%s)/", token);
		return String.format(Locale.getDefault(), "%s/(%s)/", inHost, token);
//		String accessUrlHead=String.format(Locale.getDefault(), "%s/(%s)/", inHost, token);
//		System.out.println("makeAccessUrlHead "+accessUrlHead);
//		return accessUrlHead;
	}
	
	public Map<String, String> GetRefererHeader(String accessUrlHead,String number) {
		Map<String, String> header = new HashMap<>();
//		header.put("Referer", makeAccessUrlHead() + "xs_main.aspx?xh=" + number);
		header.put("Referer", accessUrlHead + "xs_main.aspx?xh=" + number);
		return header;
	}

	public String getIndexUrl(String number) {//
		return makeAccessUrlHead() + "xs_main.aspx?xh=" + number;
	}

	public String getVerifyCodeUrl(String accessUrlHead) {
//		String accessUrl=makeAccessUrlHead();
//		System.out.println("getVerifyCodeUrl "+accessUrl);
//		return makeAccessUrlHead() + "CheckCode.aspx";
		return accessUrlHead+"CheckCode.aspx";
	}
	
	protected boolean setViewParams(String html) {
		Pattern patternA = Pattern.compile("__VIEWSTATE\" value=\"(.*)\"");
		Pattern patternB = Pattern.compile("__VIEWSTATEGENERATOR\" value=\"(.*)\"");
		Matcher matcherA = patternA.matcher(html);
		Matcher matcherB = patternB.matcher(html);
		if (matcherA.find() && matcherB.find()) {
			viewState = matcherA.group(1);
			viewStateGenerator = matcherB.group(1);
			viewState = viewState.replace(" ", "");
			viewState = viewState.replace("\n", "");
//            Log.d("debug", viewState);
//            Log.d("debug", viewStateGenerator);
			return true;
		} else {
			return false;
		}
	}

	protected boolean syncViewParams(String url) throws IOException {
		HttpHelper request = new HttpHelper(url);
		HttpResponse response = request.Get();
		if (response.getStatus() != 200) {
			return false;
		} else {
			String html = response.getResponse();
			return setViewParams(html);
		}
	}
	
	public String getAccessUrlHead() {
		return this.accessUrlHead;
	}
	
	public int Login(String account, String password) throws IOException {// return Response
		accessUrlHead=makeAccessUrlHead();
		String accessUrl = accessUrlHead + LoginPage;//
        if (!syncViewParams(accessUrl)) {
//            return new Response(false, 0, R.string.error_view_params_not_found);
        	return -1;
        }
//        System.out.println(accessUrl);
		/* Get verify code */
//        ZFVerify zfVerify = userController.getZfVerify();
//        String verifyCode = zfVerify.todo(zfVerify.getVerifyImg(getVerifyCodeUrl()));
//		String verifyCode = new J2PyGetValCode().getVerifyCode(getVerifyCodeUrl());
        String verifyCode=J2PyGetValCode.getVerifyCode(getVerifyCodeUrl(accessUrlHead));//

        HttpHelper request = new HttpHelper(accessUrl, "gbk");
		Map<String, String> postData = new HashMap<>();
		postData.put("__VIEWSTATE", URLEncoder.encode(viewState, "gbk"));
		postData.put("__VIEWSTATEGENERATOR", viewStateGenerator);
		postData.put("Textbox1", "");
		postData.put("lbLanguage", "");
		postData.put("tbtnsXw", URLEncoder.encode("yhdl|xwxsdl", "gbk"));
		postData.put("txtUserName", account);
		postData.put("Button1", "");
		postData.put("txtSecretCode", verifyCode);
		postData.put("TextBox2", password);
		postData.put("hidPdrs", "");
		postData.put("hidsc", "");
		postData.put("RadioButtonList1", "%D1%A7%C9%FA");

		HttpResponse response = request.Post(postData, false);
		if (response.getStatus() != 200) {
//            return new Response(false, 0, R.string.error_system);
			return -1;
		}
		if (response.getStatus() == -1) {
//            return new Response(false, 0, R.string.error_network);
			return -1;
		}

		String html = response.getResponse();
		Pattern patternA = Pattern.compile("alert\\('(.*?)'\\)");
		Matcher matcherA = patternA.matcher(html);
		if (matcherA.find()) {
			if (matcherA.group(1).contains("验证码")) {
				System.out.println("验证码错误!");
//				return Login(account, password);
				return 2;
			} else if(matcherA.group(1).contains("密码")) {
				System.out.println("密码错误!");
//				return Login(account, password);
				return 3;
			} else if(matcherA.group(1).contains("不存在")) {
				System.out.println("用户名不存在或未按照要求参加教学活动!");
//				return Login(account, password);
				return 4;
			}else {
//                return new Response(false, -1, matcherA.group(1));
				return -1;
			}
		}
//
//        String name = "佚名";
//        if (html.contains("输入新密码")) {
//            name = "新生";
//            return new Response(true, 1, name);
//        }
//
//        Pattern      patternB = Pattern.compile("xhxm\">(.*)同学");
//        Matcher      matcherB = patternB.matcher(html);
//        if (matcherB.find()) {
//            name = matcherB.group(1);
//        }

//        return new Response(true, 0, name);
		System.out.println("登陆成功!");
		
//		try {
//			Desktop.getDesktop().browse(new URI(accessUrlHead + "xs_main.aspx?xh=" + account));
//		} catch (URISyntaxException e) {
//			e.printStackTrace();
//		}
//		return true;
		return 1;
	}
	
//	public Profile getUserProfile(String account, String name) {
//        try {
//            String accessUrl = makeAccessUrlHead() + profilePage;
//            accessUrl += "?xh=" + account;
//            accessUrl += "&xm=" + URLEncoder.encode(name, "gbk");
//            accessUrl += "&gnmkdm=N121506";
//
//            Map<String, String> header = new HashMap<>();
//            header.put("Host", "jwgl.fafu.edu.cn");
//            header.put("Referer", getIndexUrl(account));
//            header.put("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36");
//
//            HttpHelper      request     = new HttpHelper(accessUrl, "gbk");
//            HttpResponse    response    = request.Get(header);
//            if (response.getStatus() != 200) {
//                return null;
//            }
//            if (!loginedCheck(response.getResponse())) {
//                return getUserProfile(account, name);
//            }
//
//            String  html    = response.getResponse().replace("\n", "").replace("\r", "");
//            Pattern pattern = Pattern.compile("xh\">(.*?)<.*?xm\">(.*?)<.*?xb\">(.*?)<.*?rxny\">(.*?)<.*?xy\">(.*?)<.*?bj\">(.*?)<");
//            Matcher matcher = pattern.matcher(html);
//            if (matcher.find()) {
//                Profile profile = new Profile();
//                profile.setAccount(matcher.group(1));
//                profile.setName(matcher.group(2));
//                profile.setSex(matcher.group(3));
//                profile.setYear(matcher.group(4).split("-")[0]);
//                profile.setInstitute(matcher.group(5));
//                profile.setClazz(matcher.group(6));
//                return profile;
//            } else {
//                return null;
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }

	public static void main(String[] args) {
		String account,pwd;
		Scanner sc=new Scanner(System.in);
		System.out.print("用户名:");
		account=sc.nextLine();
		System.out.print("密码:");
		pwd=sc.nextLine();
		UserInterface uInterface = new UserInterface();
//		System.out.println(uInterface.makeToken());
//		System.out.println(uInterface.getIndexUrl("3176016069"));
		try {
			System.out.println(uInterface.Login(account, pwd));
		} catch (IOException e) {
			e.printStackTrace();
		}
		sc.close();
	}

}
