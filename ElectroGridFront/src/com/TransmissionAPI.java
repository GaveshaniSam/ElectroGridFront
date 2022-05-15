package com;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TransmissionAPI
 */
@WebServlet("/TransmissionAPI")
public class TransmissionAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Transmission transmissionObj = new Transmission();	
	
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
   
    public TransmissionAPI() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String output = transmissionObj.insertTransmissions(
				request.getParameter("no"),
				request.getParameter("area"),
				request.getParameter("name"),
				request.getParameter("voltage"),
				request.getParameter("date"),
				request.getParameter("time"));
		
		response.getWriter().write(output);
		doGet(request, response);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Map paras = getParasMap(request);
		
		 String output = transmissionObj.updateTransmissions(paras.get("hidTransmissionIDSave").toString(),
				 	paras.get("no").toString(),
					paras.get("area").toString(),
					paras.get("name").toString(),
					paras.get("voltage").toString(),
					paras.get("date").toString(),
					paras.get("time").toString());
		 
		            response.getWriter().write(output);
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Map paras = getParasMap(request);
		  String output = transmissionObj.deleteTransmissions(paras.get("transID").toString());
		  
		  response.getWriter().write(output);
	}
	
	// Convert request parameters to a Map
		private static Map getParasMap(HttpServletRequest request)
		{
				Map<String, String> map = new HashMap<String, String>();
			    try {
			    	Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
			    	String queryString = scanner.hasNext() ?
			    			scanner.useDelimiter("\\A").next() : "";
			    	scanner.close();
			    	String[] params = queryString.split("&");
			    	for (String param : params)
			    	{ 
			    		String[] p = param.split("=");
			    		map.put(p[0], p[1]);
			    	}
				}
				catch (Exception e)
			 	{
			 	}
			return map;
		}

}
