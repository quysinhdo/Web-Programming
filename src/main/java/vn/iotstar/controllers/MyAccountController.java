package vn.iotstar.controllers;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.iotstar.services.IUserService;
import vn.iotstar.services.impl.UserServiceImpl;

@WebServlet(urlPatterns = {"/myaccount"})
public class MyAccountController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	IUserService service = new UserServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		
		List<String> user = service.search("sinhdq");
		
		req.setAttribute("fullname", user.get(2));
		req.setAttribute("phone", user.get(3));
		req.setAttribute("images", user.get(1));
		req.getRequestDispatcher("/views/myaccount.jsp").forward(req, resp);
	}
}
