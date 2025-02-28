package model;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/ThemGioHang")
public class ThemGioHang extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Lấy thông tin thuốc từ request
        int id = Integer.parseInt(request.getParameter("id"));
        String tenThuoc = request.getParameter("tenThuoc");
        String loaiThuoc = request.getParameter("loaiThuoc");
        double giaBan = Double.parseDouble(request.getParameter("giaBan"));
        int soLuong = Integer.parseInt(request.getParameter("soLuong"));
        String hinhAnh = request.getParameter("hinhAnh");

        // Tạo đối tượng thuốc
        Thuoc thuoc = new Thuoc(id, tenThuoc, loaiThuoc, hinhAnh, hinhAnh, hinhAnh, null, giaBan, soLuong, hinhAnh, soLuong);

        // Lấy giỏ hàng từ session
        HttpSession session = request.getSession();
        List<Thuoc> gioHang = (List<Thuoc>) session.getAttribute("gioHang");

        // Nếu giỏ hàng chưa có, tạo mới
        if (gioHang == null) {
            gioHang = new ArrayList<>();
            session.setAttribute("gioHang", gioHang);
        }

        // Kiểm tra xem thuốc đã có trong giỏ hàng chưa
        boolean daCoTrongGio = false;
        for (Thuoc t : gioHang) {
            if (t.getId() == id) {
                t.setSoLuong(t.getSoLuong() + soLuong); // Cộng dồn số lượng
                daCoTrongGio = true;
                break;
            }
        }

        // Nếu chưa có, thêm mới vào giỏ hàng
        if (!daCoTrongGio) {
            gioHang.add(thuoc);
        }

        // Chuyển hướng về trang giỏ hàng
        response.sendRedirect("giohang.jsp");
    }
}
