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

import org.json.JSONArray;
import org.json.JSONObject;

import com.mashape.unirest.http.exceptions.UnirestException;


public class FitbitWeightLogs extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, NamingException, UnirestException {
		
		String access_token = (String) request.getParameter("access_token");
		String user_id = (String) request.getParameter("user_id");
		String refresh_token = (String) request.getParameter("refresh_token");
		String base_date = (String) request.getParameter("base_date");
		String end_date = (String) request.getParameter("end_date");


		URL obj = new URL("https://api.fitbit.com/1/user/"+user_id+"/body/log/weight/date/"+base_date+"/"+end_date+".json");

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

		JSONObject result = new JSONObject(resp.toString());
		JSONArray weight = result.getJSONArray("weight");
		
		String jarr="[";
		if(weight.length()<2){
			out.println("null");
		}else{
			jarr+=weight.getJSONObject(0)+","+weight.getJSONObject(weight.length()-1)+"]";
			out.println(jarr);
		}	
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
