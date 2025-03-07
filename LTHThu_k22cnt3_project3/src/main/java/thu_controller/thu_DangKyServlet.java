package thu_controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/dang-ky")
public class thu_DangKyServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unused")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String tenNguoiDung = request.getParameter("thu_ho_ten");
        String email = request.getParameter("email");
        String matKhau = request.getParameter("mat_khau");
        
        // Gọi DAO để lưu vào database (chưa cài đặt DAO)
        boolean dangKyThanhCong = true; // Giả lập đăng ký thành công
        
        if (dangKyThanhCong) {
            HttpSession session = request.getSession();
            session.setAttribute("thu_User", tenNguoiDung);
            response.sendRedirect("thu_thuoc-list.jsp");
        } else {
            request.setAttribute("loi", "Đăng ký không thành công!");
            request.getRequestDispatcher("thu_dang-ky.jsp").forward(request, response);
        }
    }
}
