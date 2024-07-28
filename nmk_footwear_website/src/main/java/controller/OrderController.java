package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import dao.AddressDAO;
import dao.DeliveryDAO;
import dao.OrderDAO;
import dao.OrderDetailDAO;
import dao.ProductVariantDAO;
import dao.PromotionDAO;
import jakarta.persistence.EntityManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Address;
import model.CartItem;
import model.Delivery;
import model.DeliveryStatus;
import model.Order;
import model.OrderDetail;
import model.OrderDetailId;
import model.OrderStatus;
import model.PaymentMethod;
import model.ProductVariant;
import model.Promotion;
import model.User;
import utility.JPAUtil;

/**
 * Servlet implementation class OrderController
 */
@WebServlet("/order")
public class OrderController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public OrderController() {
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

		processOrder(request, response);

		response.sendRedirect(request.getContextPath() + "/orderResult.jsp");
	}

	private void processOrder(HttpServletRequest request, HttpServletResponse response) {
		EntityManager manager = JPAUtil.getEntityManagerFactory().createEntityManager();
		User user = (User) request.getSession().getAttribute("user");

		// Retrieve form parameters
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		String street = request.getParameter("street");
		String city = request.getParameter("city");
		String state = request.getParameter("state");
		String zip = request.getParameter("zip");
		PaymentMethod paymentMethod = PaymentMethod.valueOf(request.getParameter("paymentMethod"));

		double finalAmount = (double) request.getSession().getAttribute("finalAmount");
		Promotion promotion = (Promotion) request.getSession().getAttribute("promotion");

		// Initialize and persist the address
		Address address = new Address();
		address.setCity(city);
		address.setState(state);
		address.setStreet(street);
		address.setZipcode(zip);

		new AddressDAO(manager).save(address);

		// Initialize and persist the delivery
		Delivery delivery = new Delivery();
		delivery.setAddress(address);
		delivery.setDeliveryDate(null);
		delivery.setDeliveryStatus(DeliveryStatus.PENDING);
		delivery.setRecipientName(name);
		delivery.setRecipientPhone(phone);

		new DeliveryDAO(manager).save(delivery);

		// Initialize and persist the order
		Order order = new Order();
		order.setOrderDate(LocalDate.now());
		order.setTotalAmount(finalAmount);
		order.setOrderStatus(OrderStatus.PENDING);
		order.setPaymentMethod(paymentMethod);
		order.setUser(user);
		order.setPromotion(promotion);
		order.setDelivery(delivery);

		OrderDAO orderDAO = new OrderDAO(manager);
		orderDAO.save(order);

		// Initialize the order details
		Set<OrderDetail> orderDetails = new HashSet<OrderDetail>();

		for (CartItem item : user.getCart().getCartItems()) {
			OrderDetail orderDetail = new OrderDetail();

			OrderDetailId id = new OrderDetailId();
			id.setOrderId(order.getOrderId());
			id.setProductVariantId(item.getProductVariant().getProductVariantId());

			orderDetail.setId(id);
			orderDetail.setProductVariant(item.getProductVariant());
			orderDetail.setOrder(order);
			orderDetail.setQuantity(item.getQuantity());
			orderDetail.setPrice(item.getProductVariant().getPrice());
			orderDetail.setSubtotal(item.getSubtotal());

			new OrderDetailDAO(manager).save(orderDetail);

			orderDetails.add(orderDetail);
		}

		order.setOrderDetails(orderDetails);
		orderDAO.update(order);

		// Clear the cart and adjust other data
		user.getCart().getCartItems().clear();

		// Decrease the stock of the products
		for (OrderDetail orderDetail : orderDetails) {
			ProductVariantDAO productVariantDAO = new ProductVariantDAO(manager);

			ProductVariant productVariant = productVariantDAO
					.findById(orderDetail.getProductVariant().getProductVariantId());
			productVariant.setStockQuantity(productVariant.getStockQuantity() - orderDetail.getQuantity());
			productVariantDAO.update(productVariant);
		}

		// Decrease the promotion's quantity
		if (promotion != null) {
			PromotionDAO promotionDAO = new PromotionDAO(manager);
			promotion = promotionDAO.findById(promotion.getPromotionId());
			promotion.setQuantity(promotion.getQuantity() - 1);
			promotionDAO.update(promotion);
		}

		request.getSession().setAttribute("order", order);
	}

}
