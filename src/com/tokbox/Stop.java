package com.tokbox;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.opentok.OpenTok;

/**
 * Servlet implementation class Stop
 */

@WebServlet("/Stop")
public class Stop extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String archiveId = request.getParameter("archiveId");
		String apikey = request.getParameter("apikey");
		String apiSecret = request.getParameter("apiSecret");
		int apiKeyInt = Integer.parseInt(apikey);
		
		OpenTok opentok = new OpenTok(apiKeyInt, apiSecret);
		try {
			opentok.stopArchive(archiveId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		response.setContentType("text/plain");  
		response.setCharacterEncoding("UTF-8"); 
		response.getWriter().write("Done");
		
	}

}