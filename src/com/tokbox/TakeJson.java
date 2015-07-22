package com.tokbox;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * Servlet implementation class TakeJson.
 * This class handling the JSON which is sent on Callback URL.
 */
@WebServlet("/TakeJson")
public class TakeJson extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse response)
			throws ServletException, IOException {

		StringBuffer jb = new StringBuffer();
		String line = null;
		try {
			BufferedReader reader = req.getReader();
			while ((line = reader.readLine()) != null)
				jb.append(line);
		} catch (Exception e) { 
		}

		try {
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(jb.toString());
			JSONObject jsonObject = (JSONObject) obj;
			
			String archiveID=(String)jsonObject.get("id");
			String archiveStatus=(String)jsonObject.get("status");
			
			System.out.println("Archive ID is:"+archiveID);
			System.out.println(" Status:"+archiveStatus);
		
		
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
