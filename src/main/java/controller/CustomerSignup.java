package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;

import Dto.Customer;
import dao.MyDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@WebServlet("/signup")
@MultipartConfig
public class CustomerSignup extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// receive the data from front end
		String user = req.getParameter("user");
		String pass = req.getParameter("pass");
		String email = req.getParameter("email");
		long phno = Long.parseLong(req.getParameter("phno"));
		LocalDate dob = LocalDate.parse(req.getParameter("dob"));
		String gender = req.getParameter("gender");
		String pro = req.getParameter("country");
		int age = Period.between(dob, LocalDate.now()).getYears();

		Part pic = req.getPart("picture");
		byte[] picture = null;
		picture = new byte[pic.getInputStream().available()];
		pic.getInputStream().read(picture);

	

		MyDao dao = new MyDao();

		if (dao.fetchByEmail(email) == null && dao.fetchByMobile(phno) == null) {
			Customer customer = new Customer();
			customer.setUsername(user);
			customer.setEmail(email);
			customer.setPhonenumber(phno);
			customer.setPassword(pass);
			customer.setGender(gender);
			customer.setDob(dob);
			customer.setPicture(picture);
			customer.setAge(age);
			customer.setCountry(pro);

			dao.save(customer);
			resp.getWriter().print("<h1 style='color:green'>Account create succesfully</h1>");
			//this is logic to send to next page
			req.getRequestDispatcher("Login.html").include(req, resp);
			

		} else {
			resp.getWriter().print("<h1 style='color:red'>Email and phone no is alredy exit</h1>");
			req.getRequestDispatcher("Signup.html").include(req, resp);
		}

	}
}
