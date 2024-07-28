package controller;

import java.io.IOException;

import dao.CartDAO;
import dao.CartItemDAO;
import dao.ProductVariantDAO;
import dao.UserDAO;
import jakarta.persistence.EntityManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Cart;
import model.CartItem;
import model.CartItemId;
import model.CartItemSession;
import model.CartSession;
import model.ProductVariant;
import model.User;
import utility.JPAUtil;

/**
 * Servlet implementation class CartController
 */
@WebServlet("/cart")
public class CartController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CartController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		EntityManager manager = JPAUtil.getEntityManagerFactory().createEntityManager();

		// Get user from session.
		User user = (User) request.getSession().getAttribute("user");

		if (user != null) {
			UserDAO userDAO = new UserDAO(manager);
			user = userDAO.getUserByUsername(user.getUsername());
			// get the cart from the database according to the user id retrieved from the
			// session.
			CartDAO cartDAO = new CartDAO(manager);
			Cart cart = cartDAO.findByUserId(user.getUserId());

			// Set the cart in the session.
			request.getSession().setAttribute("cart", cart);

			// Forward to cart.jsp to display the cart.
			request.getRequestDispatcher("jsp/cart.jsp").forward(request, response);
		} else {
			// Handle guest users
			CartSession cart = (CartSession) request.getSession().getAttribute("cart");

			if (cart == null) {
				cart = new CartSession();
				request.getSession().setAttribute("cart", cart);
			}

			// Forward to cart.jsp to display the cart.
			request.getRequestDispatcher("jsp/cart.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		EntityManager manager = JPAUtil.getEntityManagerFactory().createEntityManager();
		HttpSession session = request.getSession();

		// Retrieve productVariantId from the request and get the ProductVariant object.
		long productVariantId = Long.parseLong(request.getParameter("productVariantId"));
		ProductVariantDAO productVariantDAO = new ProductVariantDAO(manager);
		ProductVariant productVariant = productVariantDAO.findById(productVariantId);

		// Handle cart operation (add, update, delete).
		String action = request.getParameter("action");

		switch (action) {
		case "add":
			addItem(session, manager, productVariant);
			// Redirect back to the cart page.
			response.sendRedirect("products");
			return;
		case "update":
			int quantity = Integer.parseInt(request.getParameter("quantity"));
			updateItem(session, manager, productVariant, quantity);
			response.sendRedirect("cart");
			return;
		case "delete":
			deleteItem(session, manager, productVariant);
			response.sendRedirect("cart");
			return;
		}
	}

	private void addItem(HttpSession session, EntityManager manager, ProductVariant productVariant) {
		// Get user from session
		User user = (User) session.getAttribute("user");

		if (user != null) {
			// There is a user logged in.
			CartDAO cartDAO = new CartDAO(manager);
			CartItemDAO cartItemDAO = new CartItemDAO(manager);

			Cart cart = cartDAO.findByUserId(user.getUserId());

			// Retrieve associated cart item from the database.
			CartItem cartItem = cartItemDAO
					.findById(new CartItemId(cart.getCartId(), productVariant.getProductVariantId()));

			if (cartItem == null) {
				// Cart item not found in the database. Create a new cart item.
				// Construct the composite key for CartItem. Then initialize and persist it.
				CartItemId cartItemId = new CartItemId(cart.getCartId(), productVariant.getProductVariantId());
				cartItem = new CartItem(cartItemId, cart, productVariant, 1, productVariant.getPrice(),
						productVariant.getPrice());
				cartItemDAO.save(cartItem);
				cartDAO.update(cart);
			} else {
				// Cart item found in the database. Increment the quantity by 1.
				cartItem.setQuantity(cartItem.getQuantity() + 1);
				cart.calculateTotalPrice();
			}
			cartDAO.update(cart);

		} else {
			CartSession cart = (CartSession) session.getAttribute("cart");
			if (cart == null) {
				// Create a new cart if there isn't one in the session.
				cart = new CartSession();
				session.setAttribute("cart", cart);
			}

			// Retrieve the associated SESSION cart item.
			CartItemSession cartItemSession = cart.findByProductVariantId(productVariant.getProductVariantId());

			if (cartItemSession == null) {
				// Cart item not found in the session. Create a new cart item.
				cartItemSession = new CartItemSession(productVariant, 1, productVariant.getPrice());
				cart.getCartItems().add(cartItemSession);
			} else {
				// Cart item found in the session. Increment the quantity by 1.
				cartItemSession.setQuantity(cartItemSession.getQuantity() + 1);
				cart.calculateTotalPrice();
			}
		}
	}

	private void updateItem(HttpSession session, EntityManager manager, ProductVariant productVariant, int quantity) {
		// Get user from session
		User user = (User) session.getAttribute("user");

		if (user != null) {
			// There is a user logged in.
			CartDAO cartDAO = new CartDAO(manager);
			CartItemDAO cartItemDAO = new CartItemDAO(manager);

			Cart cart = cartDAO.findByUserId(user.getUserId());

			System.out.println(cart.getCartId());
			System.out.println(productVariant.getProductVariantId());

			CartItem cartItem = cartItemDAO
					.findById(new CartItemId(cart.getCartId(), productVariant.getProductVariantId()));

			cartItem.setQuantity(quantity);
			cartItem.calculateSubtotal();
			cartItemDAO.update(cartItem);
		} else {
			// Handle guest users.
			CartSession cartSession = (CartSession) session.getAttribute("cart");
			CartItemSession cartItemSession = cartSession.findByProductVariantId(productVariant.getProductVariantId());
			cartItemSession.setQuantity(quantity);
			cartSession.calculateTotalPrice();
		}
	}

	private void deleteItem(HttpSession session, EntityManager manager, ProductVariant productVariant) {
		// Get user from session
		User user = (User) session.getAttribute("user");

		if (user != null) {
			// There is a user logged in.
			CartDAO cartDAO = new CartDAO(manager);
			CartItemDAO cartItemDAO = new CartItemDAO(manager);

			Cart cart = cartDAO.findByUserId(user.getUserId());

			CartItem cartItem = cartItemDAO
					.findById(new CartItemId(cart.getCartId(), productVariant.getProductVariantId()));

			cartItemDAO.delete(cartItem);
			cart.getCartItems().remove(cartItem);
			cart.calculateTotalPrice();
			cartDAO.update(cart);

			session.setAttribute("cart", cart);
		} else {
			// Handle guest users.
			CartSession cart = (CartSession) session.getAttribute("cart");
			CartItemSession cartItemSession = cart.findByProductVariantId(productVariant.getProductVariantId());
			cart.removeItem(cartItemSession);
		}
	}

}
