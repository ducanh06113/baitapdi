package ducanhbui.servlet;

import java.io.IOException;
import java.sql.Connection;

import NguyenHaiDang.conn.ConnectionUnit;
import NguyenHaiDang.utils.ProductUtils;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/productDelete")
public class ProductDeleteServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	public ProductDeleteServlet() {
		super();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException , IOException{
		
		String errorString = null ;
		String code =(String)request.getParameter("code");
		Connection conn = null;		
		
		try {
			conn = ConnectionUnit.getMySQLConnection();
			ProductUtils.deleteProduct(conn, code);
			
		}catch (Exception e) {
			e.printStackTrace();
			errorString= e.getMessage();
		}
		//khi co loi
		if(errorString != null ) {
			request.setAttribute("errString", errorString);
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/views/productDeleteError.jsp");
			dispatcher.forward(request, response);
		}else {
			response.sendRedirect(request.getContextPath()+"/productList");
		}
	}
	protected void doPost(HttpServletRequest request ,HttpServletResponse response)throws ServletException , IOException{
		doGet(request,response);
	}
}
