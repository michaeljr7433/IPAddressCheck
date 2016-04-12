package com.hzzgood48;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IpAddressCheck extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String ip1 = req.getRemoteAddr();
		String ip2 = req.getLocalAddr();
		PrintWriter pw = resp.getWriter();
		pw.println("<html><body>");
		pw.println("<h1 style=text-align:center>Remote Address:" +ip1 + "</h1><br>");
		pw.println("<h1 style=text-align:center>Local Address:" +ip2 + "</h1><br>");
		pw.println("</body></html>");
		pw.close();
		System.out.println(ip1);
		System.out.println(ip2);
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.service(req, resp);
	}

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
	}
	

}
