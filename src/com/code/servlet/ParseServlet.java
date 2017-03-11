package com.code.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.code.parse.Main;

/**
 * Servlet implementation class ParseServlet
 */
@WebServlet("/parse")
public class ParseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ParseServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		final int start = Integer.parseInt(req.getParameter("start"));
		final int end = Integer.parseInt(req.getParameter("end"));

		final int count = Integer.parseInt(req.getParameter("count"));

		Thread t = new Thread() {
			@Override
			public void run() {
				Main.parse(start, end, count);
				super.run();
			}
		};
		t.start();
		resp.getWriter().println("now is start parse...");
	}

}
