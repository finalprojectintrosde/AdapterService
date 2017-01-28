package com.introsde.fitbit;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FitbitSignin extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private final String clientId = "2286ZS";
	private final String clientSecret = "a51fd08110de330cff00a70d4b5ebfb4";
	private final String callbackUri = "https://adapterservice.herokuapp.com/FitbitCallback";

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException {

		// redirect to google for authorization
		StringBuilder oauthUrl = new StringBuilder().append("https://www.fitbit.com/oauth2/authorize")
		.append("?client_id=").append(clientId) // the client id from the api console registration
		.append("&response_type=code")
		.append("&scope=activity%20heartrate%20location%20nutrition%20profile%20settings%20sleep%20social%20weight") // scope is the api permissions we are requesting
		.append("&redirect_uri=").append(callbackUri); // the servlet that google redirects to after authorization
		// this requires them to verify which account to use, if they are already signed in
		
		resp.sendRedirect(oauthUrl.toString());
	}	
}