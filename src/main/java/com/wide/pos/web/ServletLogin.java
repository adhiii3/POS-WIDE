package com.wide.pos.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ServletLogin
 */
@WebServlet("/login.action")
public class ServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String pathLoginPage = "login.jsp";
	private final String pathPosDo = "pos.do";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher(pathLoginPage).forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nama = request.getParameter("nama");
		String password = request.getParameter("password");
		
		if("ira".equalsIgnoreCase(nama) && "123".equals(password)) {
			HttpSession session = request.getSession();
			session.setAttribute("user_context",nama);
			response.sendRedirect("pos.do");
		}else {
			request.getRequestDispatcher(pathLoginPage).forward(request,response);
		}

	}

}
