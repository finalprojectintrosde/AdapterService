package com.introsde.weather;

import java.io.IOException;
import java.io.PrintWriter;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class Weather extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, NamingException, UnirestException {

		String lat = request.getParameter("lat");
		String lng = request.getParameter("lng");

		HttpResponse<JsonNode> row = Unirest
				.get("https://simple-weather.p.mashape.com/weatherdata?lat=" + lat + "&lng=" + lng)
				.header("X-Mashape-Key", "qCtaraKKrxmshluO9hTVs4TVmhRGp14DWzKjsnvnNbtm4dsmuf")
				.header("Accept", "application/json").asJson();

		JSONObject resp = row.getBody().getObject();
		JSONObject query = resp.getJSONObject("query");
		JSONObject results = query.getJSONObject("results");
		JSONObject channel = results.getJSONObject("channel");
		JSONObject item = channel.getJSONObject("item");
		JSONObject condition = item.getJSONObject("condition");

		PrintWriter out = response.getWriter();
		out.println(condition);
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
}