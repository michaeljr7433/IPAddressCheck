package com.hzzgood48;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IpAddressCheck extends HttpServlet {

	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://localhost:3306/learnsql";
	private static final String DB_USER = "root";
	private static final String DB_PASS = "7438747";
	private String ip;
	private Connection conn = null;
	private Statement state = null;
	private ResultSet rs = null;

	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.service(req, resp);
		System.out.println("service end");
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ip = req.getRemoteAddr();
		long time = System.currentTimeMillis();
		Date date = new Date(time);
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
			state = conn.createStatement();
			rs = state.executeQuery("select * from ipaddress where ip ='" + ip + "'");
			System.out.println("select end");
			PrintWriter pw = resp.getWriter();
			if (rs.next()) {
				String date1 = rs.getString(3);
				pw.println("<html><body>");
				pw.println("<h1 style=text-align:center><font color=red>IP used:</h1><br>");
				pw.println("<h1 style=text-align:center><font color=red>" + ip + "</h1><br>");
				pw.println("<h1 style=text-align:center><font color=red>Used time:" + date1 + "</h1><br>");
				pw.println("</body></html>");
				pw.close();
			} else {
				pw.println("<html><body>");
				pw.println("<h1 style=text-align:center><font color=blue>IP not use:</h1><br>");
				pw.println("<h1 style=text-align:center><font color=blue>" + ip + "</h1><br>");
				pw.println("</body></html>");
				pw.close();
				System.out.println("insert into ipaddress (ip,date) values('" + ip + "','" + date + "')");
				int i = state.executeUpdate("insert into learnsql.ipaddress (ip,date) values('" + ip + "','" + date + "')");
				System.out.println("i=" + i);
				System.out.println("execute end");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				state.close();
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		System.out.println("init end");
	}

}
