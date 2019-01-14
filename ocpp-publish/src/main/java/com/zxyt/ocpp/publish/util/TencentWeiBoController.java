package com.zxyt.ocpp.publish.util;

import java.io.*;
import java.net.ConnectException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.xml.ws.Response;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.client.RestTemplate;

/**
 * 
 * @ClassName: TencentWeboController 
 * @Description: 腾讯微博控制层
 * @author JiangXincan 
 * @date 2016年1月6日 上午10:40:52 
 * @version 1.0
 */
@Controller
public class TencentWeiBoController {



	private static String client_id;

	private static String client_secret;

	private static String redirect_uri;

	private static String response_type = "code";

	private static String code_url;

	private static String state = "ocpp";


	private static String grant_type ="code";

	private static String token_url = "https://graph.qq.com/oauth2.0/authorize";


	private static String open_url;

	private static String weiboChannelId;

	private static String weiboAreaId;

	private static RestTemplate rest = new RestTemplate();

	/**
	 * 获取腾讯code
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping(value="/getcode")
	@ResponseBody
	public String getCode(@RequestParam Map<String, Object> map){
		String response_type = this.response_type;
		String client_id	= map.get("appId").toString();
		String client_secret = map.get("appKey").toString();
		String redirect_uri = this.redirect_uri;
		String state = this.state;
		
		String codeUrl = this.code_url+"?response_type="+response_type+
				"&client_id="+client_id+
				"&client_secret="+client_secret+
				"&redirect_uri="+URLEncoder.encode(redirect_uri)+
				"&state="+state;
		
		return codeUrl;
	}

	/**
	 * 发送信息
	 * @param map
	 * @return
	 */
	public static Integer authorization(Map<String, Object> map){
		// 获取token路径
		String tokenUrl = getTokenURL(map);
		// 获取token
		JSONObject token = httpRequestSSL(tokenUrl,"GET",null,1);
		if(token==null){
			return 1; // 获取token失败
		}
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("accessToken", token.getString("access_token"));
		param.put("refreshToken", token.getString("refresh_token"));
		param.put("expiresIn", token.getString("expires_in"));
		param.put("code", map.get("code"));
		param.put("keyId", map.get("keyId"));
		// 获取openId路径
		String openIdUrl = getOpenIdURL(param);
		// 获取openId
		JSONObject open = httpRequestSSL(openIdUrl,"GET",null,2);
		if(open==null){
			return 2;// 获取openId
		}
		param.put("content", "QQ{^}"+map.get("appId")+"{^}"+open.getString("openid")+"{^}"+token.getString("access_token")+"{^}"+map.get("redirectUri"));

		param.put("openId", open.getString("openid"));
		
		if(!StringUtils.isEmpty(token.getString("expires_in"))) {
			int second = Integer.valueOf(token.getString("expires_in"));
		}

		return 3;
		
        
	}

	/**
	 * 获取token路径
	 * @param map
	 * @return
	 */
	@SuppressWarnings("deprecation")
	private static  String getTokenURL(Map<String, Object> map){

		String code = map.get("code").toString();

		String tokenUrl = token_url+"?";
				tokenUrl += "response_type="+grant_type+"&";
				tokenUrl += "client_id="+client_id+"&";
				tokenUrl += "client_secret="+client_secret+"&";
				tokenUrl += "code="+code+"&";
				tokenUrl += "redirect_uri="+URLEncoder.encode(redirect_uri)+"&state="+state;
		return tokenUrl;
	}
	
	/**
	 * 获取openId路径
	 * @param map
	 * @return
	 */
	private static String getOpenIdURL(Map<String, Object> map){
		return open_url+"?access_token="+map.get("accessToken");
	}
	
	/**
	 * 获取数据
	 * @param tokenUrl
	 * @param requestMethod
	 * @param outputStr
	 * @param num
	 * @return
	 */
	private static JSONObject httpRequestSSL(String tokenUrl, String requestMethod, String outputStr,int num) {
		JSONObject jsonObject = null;
		StringBuffer buffer = new StringBuffer();
		try {
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
			TrustManager[] tm = { new MyX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();

			URL url = new URL(tokenUrl);
			HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
			httpUrlConn.setSSLSocketFactory(ssf);

			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
			// 设置请求方式（GET/POST）
			httpUrlConn.setRequestMethod(requestMethod);

			if ("GET".equalsIgnoreCase(requestMethod))
				httpUrlConn.connect();

			// 当有数据需要提交时
			if (null != outputStr) {
				OutputStream outputStream = httpUrlConn.getOutputStream();
				// 注意编码格式，防止中文乱码
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}

			// 将返回的输入流转换成字符串
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			// 释放资源
			inputStream.close();
			inputStream = null;
			httpUrlConn.disconnect();
			if(num==1){
				//获取accressToken，refreshToken
				jsonObject = getTokenData(buffer.toString());
			}else{
				//获取openId
				jsonObject = getOpenIdData(buffer.toString());
			}
			
		} catch (ConnectException ce) {
			ce.getLocalizedMessage();
		} catch (Exception e) {
			e.getLocalizedMessage();
		}
		return jsonObject;
	}
	/**
	 * 获取accressToken，refreshToken
	 * @param str
	 * @return
	 */
	private static JSONObject getTokenData(String str){
		if(str.contains("callback")){
			return null;
		}else{
			 str = "{'"+str.replaceAll("&", "', '").replaceAll("=", "':'")+"'}";
			return JSONObject.parseObject(str);
		}
	}

	/**
	 * 获取openId
	 * @param str
	 * @return
	 */
	private static JSONObject getOpenIdData(String str){
		if(!str.contains("openid")){
			return null;
		}else{
			str = str.substring(str.indexOf("{"), str.indexOf("}")+1);
			return JSONObject.parseObject(str);
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// 腾讯发微博
	private static JSONObject httpRequestSSL(String tokenUrl, String requestMethod, String outputStr) {
		JSONObject jsonObject = null;
		StringBuffer buffer = new StringBuffer();
		try {
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
			TrustManager[] tm = { new MyX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();

			URL url = new URL(tokenUrl);
			HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
			httpUrlConn.setSSLSocketFactory(ssf);

			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
			// 设置请求方式（GET/POST）
			httpUrlConn.setRequestMethod(requestMethod);

			if ("GET".equalsIgnoreCase(requestMethod))
				httpUrlConn.connect();

			// 当有数据需要提交时
			if (null != outputStr) {
				OutputStream outputStream = httpUrlConn.getOutputStream();
				// 注意编码格式，防止中文乱码
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}

			// 将返回的输入流转换成字符串
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			// 释放资源
			inputStream.close();
			inputStream = null;
			httpUrlConn.disconnect();

			jsonObject = getContentCode(buffer.toString());
			
		} catch (ConnectException ce) {
			ce.getLocalizedMessage();
		} catch (Exception e) {
			e.getLocalizedMessage();
		}
		return jsonObject;
	}
	private static JSONObject getContentCode(String str){
		
			return JSONObject.parseObject(str);
	}


	private static  String getTokenURL(JSONObject json){


			String token_url = json.getString("token_url");
			String grant_type = json.getString("grant_type");
			String client_id = json.getString("client_id");
			String client_secret = json.getString("client_secret");
			String code = json.getString("code");
			String redirect_uri = json.getString("redirect_uri");
			String state = json.getString("state");

			String tokenUrl = token_url+"?";
			tokenUrl += "grant_type="+grant_type+"&";
			tokenUrl += "client_id="+client_id+"&";
			tokenUrl += "client_secret="+client_secret+"&";
			tokenUrl += "code="+code+"&";
			tokenUrl += "redirect_uri="+redirect_uri +"&state=" + state;
			return tokenUrl;

	}


	public static void main(String[] args) {


//		第一步：需要提供域名、网站备案号

//		第二步：在qq互联申请账号

//		第三步：进入qq互联将基本信息完善，并提交审核

//		第四步：基本信息审核通过之后，根据第一步提供的信息和qq互联相应流程创建网站应用，并提交网站应用审核（在审核期间中可以得到appId,appKey）

//		第五步：在第一步域名地址所在的实体网站中（通常指的是进入系统的第一个界面，或者登录界面）添加qq登录组件（outh2.0校验必须步骤）

//		第六步：根据https://graph.qq.com/oauth2.0/authorize?response_type=code&client_id=101535330&redirect_uri=http://www.bjzxyt.cn/index.html&state=zxyt获取Authorization Code（55EA280E3E9AD257E124EE78DFC60D48）

//		第七步：根据 https://graph.qq.com/oauth2.0/token?grant_type=authorization_code&client_id=3083008226&client_secret=f68149e7da13e95ddd3172c6442dc1aa&code=C145A84B2EE072075E48082499A06DC0&
// 					redirect_uri=http://www.bjzxyt.cn/index.html
//				Authorization Code获取Authorization Token



		JSONObject json = new JSONObject();
		json.put("token_url","https://graph.qq.com/oauth2.0/token");
		json.put("grant_type","authorization_code");
		json.put("client_id", "1107957551");
		json.put("client_secret", "BNFS3WJkrBWjr2pw");
		json.put("code", "DAEC0C0377E66FE38A930C094BD38FF0");// 10分钟失效
		json.put("redirect_uri","http://www.bjzxyt.cn/index.html");
		json.put("state","zxyt");


		// 获取token
//		String tokenUrl = getTokenURL(json);
//		System.out.println(tokenUrl);
//		JSONObject token = rest.postForObject(tokenUrl,"GET", JSONObject.class);
//		System.out.println(token);



		//获取用户openId

//		String openUrl = "https://graph.
//
//
//
// qq.com/oauth2.0/me?access_token=D7CB386170CE7FABD087327CE26C53FE";
//
//		JSONObject openId = rest.getForObject(openUrl, JSONObject.class);
//
//		System.out.println(openId);


		String url = "https://graph.qq.com/t/add_t?";
		String param =
				"access_token=D7CB386170CE7FABD087327CE26C53FE"
				+ "&oauth_consumer_key=101535330"
				+ "&openid=6DF071D370325F75AE54B4ADD686CC4D"
				+ "&format=json"
				+ "&content=测试腾讯微博发送";
		JSONObject result = httpRequestSSL(url,"POST",param);

//		JSONObject result = rest.postForObject(url + param, "POST",JSONObject.class);

		Integer ret = result.getInteger("ret");
		System.out.println(ret);
		if(ret==0){
			System.out.println("微博发送成功");
		}else{
			System.out.println("微博发送失败");
		}
		System.out.println(json);
	}
}
