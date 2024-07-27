package controller;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import dao.CartDAO;
import dao.UserDAO;
import jakarta.persistence.EntityManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Cart;
import model.CartItem;
import model.CartItemId;
import model.CartItemSession;
import model.CartSession;
import model.User;
import utility.JPAUtil;

/**
 * Servlet implementation class AccountController
 */
@WebServlet("/account")
public class AccountController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AccountController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("jsp/account.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		EntityManager manager = JPAUtil.getEntityManagerFactory().createEntityManager();
		UserDAO userDAO = new UserDAO(manager);

		// process login form
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		if (checkLogin(username, password)) {
			// System.out.println("User logged in");
			User user = userDAO.getUserByUsername(username);
			request.getSession().setAttribute("user", user);

			// Migrate the data from the session cart to the user's cart (or initialize a
			// new cart if the user does not have one).
			CartSession cartSession = (CartSession) request.getSession().getAttribute("cart");

			migrateSessionCartToUserCart(cartSession, user);
			
			System.out.println("cart id : " + user.getCart().getCartId());
			System.out.println("user id attached to cart : " + user.getCart().getUser().getUserId());
			System.out.println("User cart size : " + user.getCart().getCartItems().size());

			request.getSession().setAttribute("cart", user.getCart());

			response.sendRedirect("home");
			return;
		} else {
			// request.setAttribute("error", "* Invalid username or password");
			request.getSession().setAttribute("error", "* Invalid username or password");
			response.sendRedirect("account");
			return;
		}

	}

	private boolean checkLogin(String username, String password) {
		EntityManager manager = JPAUtil.getEntityManagerFactory().createEntityManager();
		UserDAO userDAO = new UserDAO(manager);

		User user = userDAO.getUserByUsername(username);

		if (user != null && user.getPassword().equals(password)) {
			return true;
		}

		// the username is either invalid or the password is incorrect
		return false;
	}

	private void migrateSessionCartToUserCart(CartSession sessionCart, User user) {
		EntityManager manager = JPAUtil.getEntityManagerFactory().createEntityManager();

		if (user.getCart() != null) {
			return;
		}

		Cart userCart = user.getCart();
		
		userCart = new Cart();

		// Persist the user cart to get the ID generated first.
		manager.getTransaction().begin();
		manager.persist(userCart);
		manager.getTransaction().commit();

		userCart.setUser(user);
		user.setCart(userCart);

		Set<CartItem> userCartItems = new HashSet<CartItem>();

		if (sessionCart != null) {
			for (CartItemSession sessionItem : sessionCart.getCartItems()) {
				CartItem userItem = new CartItem();

				// Create CartItemId
				CartItemId id = new CartItemId();

				System.out.println(userCart.getCartId());

				id.setCartId(userCart.getCartId());
				id.setProductVariantId(sessionItem.getProductVariant().getProductVariantId());

				userItem.setId(id);
				userItem.setProductVariant(sessionItem.getProductVariant());
				userItem.setQuantity(sessionItem.getQuantity());
				userItem.setPrice(sessionItem.getPrice());
				userItem.calculateSubtotal();

				userCartItems.add(userItem);
			}
		}

		userCart.setCartItems(userCartItems);
		userCart.calculateTotalPrice();
		
		manager.getTransaction().begin();
		manager.merge(user);
		manager.merge(userCart);
		manager.getTransaction().commit();
	}

}
