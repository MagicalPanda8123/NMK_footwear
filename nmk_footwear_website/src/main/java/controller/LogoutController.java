package controller;

import java.io.IOException;

import dao.CartDAO;
import dao.CartItemDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.CartItem;
import model.User;
import utility.JPAUtil;

/**
 * Servlet implemeoller
 */
@WebServlet("/logout")
public class LogoutController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LogoutController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Persist the user's cart
		User user = (User) request.getSession().getAttribute("user");
		CartDAO cartDAO = new CartDAO(JPAUtil.getEntityManagerFactory().createEntityManager());
		cartDAO.update(user.getCart());

		CartItemDAO cartItemDAO = new CartItemDAO(JPAUtil.getEntityManagerFactory().createEntityManager());
		for (CartItem item : user.getCart().getCartItems()) {
			cartItemDAO.update(item);
		}

		// Invalidate the session to log the user out
		request.getSession().invalidate();

		// Redirect to the home page or login page
		response.sendRedirect("home");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
