package ducanhbui.servlet;

import java.io.IOException;
import java.sql.Connection;

import NguyenHaiDang.beans.Product;
import NguyenHaiDang.conn.ConnectionUnit;
import NguyenHaiDang.utils.ProductUtils;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet
public class ProductCreateServlet extends HomeServlet{
	private static final long serialVersionUID = 1l;
	public ProductCreateServlet() {
		super();
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/views/productCreate.jsp");
		dispatcher.forward(request, response);
		
	}
	//Khi người dùng nhấn nút ghi lại phương thức dogot sẽ được gọi
	@Override
	protected void doPost(HttpServletRequest request , HttpServletResponse response)throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8");
		String erroString = null;
		//Lấy dữ liệu from
		String code =(String) request.getParameter("code");
		String name =(String) request.getParameter("name");
		String priceStr=(String) request.getParameter("price");
		float price = 0;
		try {
			price= Float.parseFloat(priceStr);
			
					
		}catch (Exception e) {
			// TODO: handle exception
			erroString = e.getMessage();
		}
		Product  product = new Product(code, name , price);
		//Kiểm tra code ít nhất 1 ký tự[a-a , A-Z, 0-9]
		String  regex="\\w+";
		if(code == null || !code.matches(regex)) {
			erroString = "Product Code invalid!";
		}
		if(erroString !=null) {
			request.setAttribute("erroString", erroString);
			request.setAttribute("product", product);
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/views/productCreate.jsp");
			dispatcher.forward(request, response);
			return;
			
			
		}
		Connection conn= null;
		try {
			conn = ConnectionUnit.getMySQLConnection();
			ProductUtils.insertProduct(conn, product);
			response.sendRedirect(request.getContextPath()+"/productList");
			
		}catch (Exception e) {
			e.printStackTrace();
			erroString= e.getMessage();
			request.setAttribute("errorString", erroString);
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/views/productCreate.jsp");
			dispatcher.forward(request, response);
			
			// TODO: handle exception
		}
	}
	
}
