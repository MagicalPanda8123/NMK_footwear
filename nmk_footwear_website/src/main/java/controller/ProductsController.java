package controller;

import java.io.IOException;
import java.util.List;

import dao.ProductDAO;
import jakarta.persistence.EntityManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.CartSession;
import model.Product;
import utility.JPAUtil;


@WebServlet("/products")
public class ProductsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductsController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EntityManager manager = JPAUtil.getEntityManagerFactory().createEntityManager();
		
		// implementing pagination logic here
		int pageNumber = 1;
		int recordsPerPage = 8;
		
		if (request.getParameter("page") != null) {
			pageNumber = Integer.parseInt(request.getParameter("page"));
		}
		
		// Set the offset parameter (the number of records to skip) based on the page number
		int offset = (pageNumber - 1) * recordsPerPage;
		
		ProductDAO productDAO = new ProductDAO(manager);
		List<Product> products = productDAO.getProducts(offset, recordsPerPage);
		
		// Get total page count
		int totalPageCount = (int) Math.ceil(productDAO.getProductCount() * 1.0 / recordsPerPage);
		
		request.setAttribute("products", products);
		request.setAttribute("totalPageCount", totalPageCount);
		request.setAttribute("currentPage", pageNumber);
		
		request.getRequestDispatcher("jsp/products.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
