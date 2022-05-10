package com.noobs.codeshare.service;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.security.Timestamp;
import java.util.ArrayList;

import com.noobs.codeshare.DateProcessing;
import com.noobs.codeshare.dao.SourceCodeDAO;

@WebServlet("/SourceCodeService")
public class SourceCodeService extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		SourceCodeDAO source_code_dao = new SourceCodeDAO();

		int language = Integer.parseInt(request.getParameter("language"));
		int poster = Integer.parseInt(request.getParameter("poster"));
		String poster_name = request.getParameter("poster_name");
		String source = request.getParameter("source");
		System.out.println("poster name: " + poster_name);
		int expire = Integer.parseInt(request.getParameter("expire"));
		DateProcessing dp = new DateProcessing();
		java.sql.Timestamp curTimestamp = dp.getCurTimestamp();
		String expTimestamp = null;
		int visibility = 1;
		String[] share_with = null;

		if (expire == 1) {
			expTimestamp = dp.getExpTimestamp(1).toString();
		} else if (expire == 2) {
			expTimestamp = dp.getExpTimestamp(24).toString();
		} else if (expire == 3) {
			expTimestamp = dp.getExpTimestamp(7 * 24).toString();
		} else if (expire == 4) {
			expTimestamp = dp.getExpTimestamp(30 * 24).toString();
		}

		if (session.getAttribute("id") != null) {
			share_with = request.getParameterValues("share_with");
			visibility = Integer.parseInt(request.getParameter("visibility"));
		}
		//source_code_dao.addSourceCode(language, visibility, source, poster, poster_name, curTimestamp.toString(),
				//expTimestamp, 0, share_with);
	}
}