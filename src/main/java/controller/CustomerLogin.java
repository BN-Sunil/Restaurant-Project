package controller;

import java.io.IOException;

import Dto.Customer;
import dao.MyDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class CustomerLogin extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String email=req.getParameter("email");
		String pass=req.getParameter("pass");
		
		//verify if email exit
		
		MyDao dao = new MyDao();

		Customer customer=dao.fetchByEmail(email);
		if(customer==null) {
			resp.getWriter().print("<h1 style='color:red'>invalid email</h1>");
			req.getRequestDispatcher("Login.html").include(req, resp);
		}
		else {
			if(pass.equals(customer.getPassword())) {
				resp.getWriter().print("<h1 style='color:green'>Login sucess</h1>");
				//this is logic to send to next apge
				req.getRequestDispatcher("CustemerHome.html").include(req, resp);
			}
			else {
				resp.getWriter().print("<h1 style='color:red'>invalid password</h1>");
				req.getRequestDispatcher("Login.html").include(req, resp);
			}
		}
		
	}
	

	

}
