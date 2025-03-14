package thu_controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import thu_model.thu_ChiTietDonHang;
import thu_model.thu_DBConnection;

@SuppressWarnings("unused")
@WebServlet("/ThuMuaHangServlet")
public class ThuMuaHangServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Phương thức doGet() để tránh lỗi 405 khi truy cập bằng trình duyệt
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        response.getWriter().println("<h3>ThuMuaHangServlet đang chạy! Vui lòng sử dụng phương thức POST để mua hàng.</h3>");
    }

    // Phương thức doPost() để xử lý yêu cầu mua hàng
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        Connection con = null;
        PreparedStatement stmtDonHang = null, stmtChiTiet = null;
        ResultSet rs = null;

        try {
            // Kết nối CSDL
            con = thu_DBConnection.getConnection();

            // Nhận dữ liệu từ request
            String tenSanPham = request.getParameter("tenSanPham");
            int soLuong = Integer.parseInt(request.getParameter("soLuong"));
            double donGia = Double.parseDouble(request.getParameter("donGia"));
            int maKhachHang = Integer.parseInt(request.getParameter("maKhachHang"));

            // Thêm đơn hàng vào bảng DonHang_Thu
            String sqlInsertDonHang = "INSERT INTO DonHang_Thu (maKhachHang, ngayDatHang) VALUES (?, NOW())";
            stmtDonHang = con.prepareStatement(sqlInsertDonHang, PreparedStatement.RETURN_GENERATED_KEYS);
            stmtDonHang.setInt(1, maKhachHang);
            stmtDonHang.executeUpdate();

            // Lấy ID đơn hàng mới tạo
            rs = stmtDonHang.getGeneratedKeys();
            int maDonHang = -1;
            if (rs.next()) {
                maDonHang = rs.getInt(1);
            }

            // Thêm chi tiết đơn hàng vào bảng ChiTietDonHang_Thu
            String sqlInsertChiTiet = "INSERT INTO ChiTietDonHang_Thu (maDonHang, tenSanPham, soLuong, donGia) VALUES (?, ?, ?, ?)";
            stmtChiTiet = con.prepareStatement(sqlInsertChiTiet);
            stmtChiTiet.setInt(1, maDonHang);
            stmtChiTiet.setString(2, tenSanPham);
            stmtChiTiet.setInt(3, soLuong);
            stmtChiTiet.setDouble(4, donGia);
            stmtChiTiet.executeUpdate();

            // Phản hồi JSON về client
            response.getWriter().write("{\"message\": \"Đơn hàng đã được tạo thành công!\", \"maDonHang\": " + maDonHang + "}");
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("{\"error\": \"Lỗi khi đặt hàng!\"}");
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmtDonHang != null) stmtDonHang.close();
                if (stmtChiTiet != null) stmtChiTiet.close();
                if (con != null) con.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
