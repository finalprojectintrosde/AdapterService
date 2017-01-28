package com.introsde.fitbit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FitbitUserInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String access_token = (String) request.getSession().getAttribute("access_token");
		String user_id = (String) request.getSession().getAttribute("user_id");
		String refresh_token = (String) request.getSession().getAttribute("refresh_token");

		String from = request.getParameter("from");
		if (from == null) {
			response.sendRedirect("/FitbitSignin");
		} else {
			PrintWriter out = response.getWriter();
			out.println(access_token+" "+user_id+" "+refresh_token);
			//out.println(getUserInformation(access_token, user_id));

		}
	}

	public String getUserInformation(String access_token, String user_id) throws IOException {

		Date date = new Date();
		String sdate = new SimpleDateFormat("yyyy-MM-dd").format(date);

		URL obj = new URL("https://api.fitbit.com/1/activities.json");

		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		con.setRequestMethod("GET");
		con.setRequestProperty("Authorization", "Bearer " + access_token);

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		return response.toString();
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}