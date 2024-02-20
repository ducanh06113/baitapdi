package ducanhbui.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import NguyenHaiDang.beans.Product;
import NguyenHaiDang.conn.ConnectionUnit;
import NguyenHaiDang.utils.ProductUtils;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/productList")
public class ProductListSerlet {
	private static final long serialVersionUID = 1L;
	public ProductListSerlet() {
		super();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response )throws ServletException , IOException{
		Connection conn = null;
		String errorString = null;
		List<Product> list = null;
		try {
			conn = ConnectionUnit.getMySQLConnection();
			try {
				list = ProductUtils.queryProduct(conn);
			}catch (SQLException e) {
				e.printStackTrace();
				errorString = e.getMessage();
			}
			request.setAttribute("errorString", errorString);
			request.setAttribute("productList", list);
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/views/productList.jsp");
			dispatcher.forward(request, response);
		}catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
			errorString = e1.getMessage();
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/views/productList.jsp");
			request.setAttribute("errorString", errorString);
			dispatcher.forward(request, response);
			
		}
		
	}
	 protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        doGet(request, response);
	    }
	
}
