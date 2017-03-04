package com.code.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

public class NetDownloadStatus extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out = res.getWriter();
		JSONObject obj = new JSONObject();
		String key = req.getParameter("dkey");
		Integer idStr = (Integer) req.getSession().getAttribute(
				"netdownload" + key);
		File file = (File) req.getSession().getAttribute("bookfile"+key);
		obj.put("code", 200);
		obj.put("size", file.length());
		obj.put("bookId", idStr);
		out.print(obj);
	}
}
