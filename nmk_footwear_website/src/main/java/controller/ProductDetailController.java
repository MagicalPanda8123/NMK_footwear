package controller;

import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dao.ProductDAO;
import jakarta.persistence.EntityManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Product;
import model.ProductVariant;
import utility.JPAUtil;
import utility.ProductVariantSerializer;

@WebServlet("/product-detail")
public class ProductDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProductDetailController() {
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

		// Retrieve the product ID from the request.
		long productId = Long.parseLong(request.getParameter("productId"));

		// Retrieve the product from the database.
		ProductDAO productDAO = new ProductDAO(manager);

		Product product = productDAO.findById(productId);

		// Convert the product variants to JSON
		Gson gson = new GsonBuilder()
			    .registerTypeAdapter(ProductVariant.class, new ProductVariantSerializer())
			    .create();

		String variantsJson = gson.toJson(product.getVariants());

		// Set the product and variants JSON as request attributes
		request.setAttribute("product", product);
		request.setAttribute("variantsJson", variantsJson);

		request.getRequestDispatcher("jsp/productDetail.jsp").forward(request, response);
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
