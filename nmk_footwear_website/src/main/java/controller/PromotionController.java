package controller;

import java.io.IOException;

import dao.PromotionDAO;
import jakarta.persistence.EntityManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Promotion;
import utility.JPAUtil;

/**
 * Servlet implementation class PromotionController
 */
@WebServlet("/promotion-processing")
public class PromotionController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PromotionController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		EntityManager manager = JPAUtil.getEntityManagerFactory().createEntityManager();

		// Get the promotion code and total order amount provided by the client.
		String promotionCode = request.getParameter("promotionCode");
		double totalAmount = Double.parseDouble(request.getParameter("totalAmount"));

		// Check if the promotion code is valid.
		PromotionDAO promotionDAO = new PromotionDAO(manager);

		Promotion promotion = promotionDAO.findByCode(promotionCode); // first find the promotion by the code string.

		if (promotionDAO.isValidPromotion(promotion, totalAmount)) {
			// if the promotion is valid, set the promotion in the session.
			request.getSession().setAttribute("promotion", promotion);

			// calculate the discounted amount.
			double discountedAmount = totalAmount * promotion.getDiscountPercentage() / 100;
			double finalAmount = totalAmount - discountedAmount;

			request.getSession().setAttribute("discountedAmount", discountedAmount);
			request.getSession().setAttribute("finalAmount", finalAmount);
		} else {
			request.setAttribute("promotionError", "Invalid or expired promotion code");
			request.getSession().setAttribute("discountedAmount", 0.0);
			request.getSession().setAttribute("finalAmount", totalAmount);
		}

		request.getRequestDispatcher("jsp/checkout.jsp").forward(request, response);
	}

}
