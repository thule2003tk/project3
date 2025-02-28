package controller; 

import java.io.IOException;
import java.util.ArrayList;

import model.Thuoc;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/ThemVaoGioHang") // Định nghĩa đường dẫn Servlet
public class ThemVaoGioHang extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy tham số ID thuốc từ request
        String id = request.getParameter("id");
        
        if (id != null) {
            // Lấy session hiện tại
            HttpSession session = request.getSession();
            
            // Lấy danh sách giỏ hàng từ session (nếu có), nếu không thì tạo mới
            ArrayList<Thuoc> gioHang = (ArrayList<Thuoc>) session.getAttribute("gioHang");
            if (gioHang == null) {
                gioHang = new ArrayList<>();
            }

            // Giả lập sản phẩm (Bạn cần lấy dữ liệu từ database)
            Thuoc thuoc = new Thuoc(Integer.parseInt(id), "Tên thuốc " + id, id, id, id, id, null, 100000, 1, id, 0);
            gioHang.add(thuoc); // Thêm thuốc vào giỏ hàng

            // Cập nhật session
            session.setAttribute("gioHang", gioHang);

            // Chuyển hướng về trang giỏ hàng
            response.sendRedirect("giohang.jsp");
        } else {
            response.sendRedirect("error.jsp"); // Nếu không có ID, chuyển hướng đến trang lỗi
        }
    }
}
