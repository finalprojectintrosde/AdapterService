package com.introsde.fitbit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

public class FitbitCallback extends HttpServlet {
	
	static String  code;
	private static final long serialVersionUID = 1L;
	private final String clientId = "2286ZS";
	private final String clientSecret = "a51fd08110de330cff00a70d4b5ebfb4";
	private final String callbackUri = "https://adapterservice.herokuapp.com/FitbitCallback";
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException {
		
		if (req.getParameter("error") != null) {
			resp.getWriter().println(req.getParameter("error"));
			return;
		}
		code = req.getParameter("code");
		//prova
		URL obj = new URL("https://api.fitbit.com/oauth2/token");
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setDoOutput(true);
		con.setRequestMethod("POST");
		con.setRequestProperty("Authorization", "Basic MjI4NlpTOmE1MWZkMDgxMTBkZTMzMGNmZjAwYTcwZDRiNWViZmI0");
		con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

		//set and send body of the request
		String str = "grant_type=authorization_code&clientId="+clientId+"&code="+code+"&redirect_uri="+callbackUri;
		byte[] outputInBytes = str.getBytes("UTF-8");
		OutputStream os = con.getOutputStream();
		os.write(outputInBytes);
		os.close();
		//get the response
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		
		JSONObject jobj = new JSONObject(response.toString());
		String accessToken = jobj.getString("access_token");
		String userId= jobj.getString("user_id");
		String refreshToken= jobj.getString("refresh_token");

		
		req.getSession().setAttribute("user_id", userId);
		req.getSession().setAttribute("access_token", accessToken);
		req.getSession().setAttribute("refresh_token", refreshToken);

		resp.sendRedirect("/FitbitUserInfo?from=callback");	
	}	

}