package com.introsde.bmi;

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

public class Bmi  extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, NamingException, UnirestException {    
        		
		String weight=request.getParameter("weight");
		String height=request.getParameter("height");
		String sex=request.getParameter("sex");
		String age=request.getParameter("age");


        // These code snippets use an open-source library. http://unirest.io/java
    	HttpResponse<JsonNode> resp = Unirest.post("https://bmi.p.mashape.com/")
    	.header("X-Mashape-Key", "qCtaraKKrxmshluO9hTVs4TVmhRGp14DWzKjsnvnNbtm4dsmuf")
    	.header("Content-Type", "application/json")
    	.header("Accept", "application/json")
    	.body("{\"weight\":{\"value\":\""+weight+"\",\"unit\":\"kg\"},\"height\":{\"value\":\""+height+"\",\"unit\":\"cm\"},\"sex\":\""+sex+"\",\"age\":\""+age+"\"}")
    	.asJson();
    	
    	JSONObject result=resp.getBody().getObject();
    	JSONObject bmi=result.getJSONObject("bmi");
    	
    	PrintWriter out = response.getWriter();
    	out.println(bmi);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (UnirestException e) {
			e.printStackTrace();
		}
	}
}