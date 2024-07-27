package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;

/**
 * Servlet implementation class CheckOutController
 */
@WebServlet("/checkout")
public class CheckoutController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckoutController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Check if user is logged in
		User user = (User) request.getSession().getAttribute("user");
		
		if (user == null) {
			// if the user is not logged in, redirect to the login page.
			response.sendRedirect("account");
			return;
		}
		else {
			// if the user is logged in, redirect to the checkout page.
			
//			// Initialize the Order object and set it in the session.
//			Order order = new Order();
//			order.setOrderDate(java.time.LocalDate.now());
//			order.setOrderStatus(OrderStatus.PENDING);
//			order.setPaymentMethod(PaymentMethod.CASH_ON_DELIVERY);
//			order.setUser(user);
			
			
			request.getRequestDispatcher("jsp/checkout.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
