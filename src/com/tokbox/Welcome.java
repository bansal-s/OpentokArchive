package com.tokbox;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.opentok.MediaMode;
import com.opentok.OpenTok;
import com.opentok.Role;
import com.opentok.Session;
import com.opentok.SessionProperties;
import com.opentok.TokenOptions;
import com.opentok.exception.OpenTokException;

/**
 * Servlet implementation class Archive
 */
@WebServlet("/Welcome")
public class Welcome extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int apiKey = 0 ; // YOUR API KEY
		String apiSecret = "YOUR API SECRET"; // YOUR API SECRET
		OpenTok opentok = new OpenTok(apiKey, apiSecret);
	
		Session routedSession = null;
		try {

			routedSession = opentok.createSession(new SessionProperties.Builder().mediaMode(MediaMode.ROUTED).build());
		} catch (Exception e) {
			//e.printStackTrace();
			System.out.println("in catch method");
			System.out.println(e);
		}
		
		String sessionId = routedSession.getSessionId();
		System.out.println("SessionId is : "+sessionId);

		String token = null;
		try {
			token = routedSession.generateToken(new TokenOptions.Builder().role(Role.MODERATOR).expireTime((System.currentTimeMillis() / 1000L) + (7 * 24 * 60 * 60)).data("name=sumit").build());
		} catch (OpenTokException e) {
			System.out.println("cant create token");
			e.printStackTrace();
		}
		System.out.println("Token is : "+token);

		request.setAttribute("sessionID", sessionId);
		request.setAttribute("apikey", apiKey);
		request.setAttribute("token", token);
		request.setAttribute("apiSecret", apiSecret);
		
		
		request.getRequestDispatcher("/Archiving.jsp").forward(request, response);
		
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}

