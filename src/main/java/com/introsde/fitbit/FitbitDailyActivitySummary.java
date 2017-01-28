package com.introsde.fitbit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mashape.unirest.http.exceptions.UnirestException;


public class FitbitDailyActivitySummary extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, NamingException, UnirestException {
		String access_token = (String) request.getParameter("access_token");
		String user_id = (String) request.getParameter("user_id");
		String refresh_token = (String) request.getParameter("refresh_token");
		String date = (String) request.getParameter("date");

		
		URL obj = new URL("https://api.fitbit.com/1/user/"+user_id+"/activities/date/"+date+".json");

		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		con.setRequestMethod("GET");
		con.setRequestProperty("Authorization", "Bearer " + access_token);

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer resp = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			resp.append(inputLine);
		}
		in.close();
		
	PrintWriter out = response.getWriter();
	out.println(resp);
	
}

protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
	try {
		processRequest(request, response);
	} catch (NamingException e) {
		e.printStackTrace();
	} catch (UnirestException e) {
		e.printStackTrace();
	}
}

protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
	doGet(request, response);
}

}
