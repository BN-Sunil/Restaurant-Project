package controller;

import java.io.IOException;
import java.util.List;

import Dto.Item;
import dao.MyDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/viewmenu")
public class AdimnViewitem extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//logic to fetch data from database
		MyDao dao=new MyDao();
	   List<Item> items=dao.fetchAllFooditem();
		
		
		
		//logic to display data on the front end
		resp.getWriter().print("<html><body><h1>Menu</h1>");
		resp.getWriter().print("<table border='1'>");
		resp.getWriter().print(
				"<tr><th>Name</th><th>Type</th><th>Price</th><th>Quantity</th><th>Edit</th><th>Delete</th></tr>");
		for (Item item : items) {
			resp.getWriter().print(
					"<tr><th>"+item.getItem_name()+"</th><th>"+item.getFood_type()+"</th><th>"+item.getItem_price()+"</th><th>"+item.getQuantity()+"</th><th><button>Edit</button></th><th><button>Delete</button></th></tr>");
		}
		resp.getWriter().print("</table></body></html>");
	}
}
