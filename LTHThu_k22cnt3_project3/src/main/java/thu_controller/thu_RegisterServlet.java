package thu_controller;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import thu_dao.thu_KhachHangDAO;
import thu_model.thu_DBConnection;
import thu_model.thu_KhachHang;

@WebServlet("/register")
public class thu_RegisterServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String hoTen = request.getParameter("hoTen");
        String email = request.getParameter("email");
        String soDienThoai = request.getParameter("soDienThoai");
        String diaChi = request.getParameter("diaChi");
        String matKhau = request.getParameter("matKhau");

        thu_KhachHang kh = new thu_KhachHang(0, hoTen, email, soDienThoai, diaChi, matKhau);
        thu_KhachHangDAO dao = null;
		try {
			dao = new thu_KhachHangDAO(thu_DBConnection.getConnection());
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        if (dao.register(kh)) {
            response.sendRedirect("thu_login.jsp?success=true");
        } else {
            response.sendRedirect("register.jsp?error=true");
        }
    }
}
