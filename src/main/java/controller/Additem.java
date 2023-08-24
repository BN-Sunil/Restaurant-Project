package controller;

import java.io.IOException;

import Dto.Item;
import dao.MyDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/additem")
@MultipartConfig
public class Additem extends HttpServlet {
  @Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	  String name = req.getParameter("name");
	  double price= Double.parseDouble(req.getParameter("price"));
	  String type = req.getParameter("type");
	  int quantity=Integer.parseInt(req.getParameter("quantity"));
	  byte[] picture=new byte[req.getPart("pic").getInputStream().available()];
	  req.getPart("pic").getInputStream().read(picture);
	  
	  Item items=new Item();
	  items.setItem_name(name);
	  items.setItem_price(price);
	  items.setQuantity(quantity);
	  items.setFood_type(type);
	  items.setPicture(picture);
	  
	  MyDao dao=new MyDao();
	  dao.item(items);
	  resp.getWriter().print("<h1 style='color:green'>Added successfully </h1>");
	  
	  
	  
}
}
