package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dao.GioHangDAO;
import dao.ThuocDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.GioHang;
import model.Thuoc;
import config.DatabaseConnection; 

@WebServlet("/GioHangServlet")
public class GioHangServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer khachHangId = (Integer) session.getAttribute("khachHangId");

        if (khachHangId == null) {
            response.sendRedirect("login.jsp?error=notLoggedIn");
            return;
        }

        try {
            int thuocId = Integer.parseInt(request.getParameter("id"));

            try (Connection conn = DatabaseConnection.getConnection()) {
                GioHangDAO gioHangDAO = new GioHangDAO(conn);
                ThuocDAO thuocDAO = new ThuocDAO(conn);

                // Lấy thuốc từ database
                Thuoc thuoc = thuocDAO.getThuocById(thuocId);
                if (thuoc == null) {
                    response.sendRedirect("giohang.jsp?error=ThuocNotFound");
                    return;
                }

                // Kiểm tra xem thuốc đã có trong giỏ chưa
                List<GioHang> gioHangList = gioHangDAO.layGioHang(khachHangId);
                boolean daTonTai = false;

                for (GioHang gh : gioHangList) {
                    if (gh.getThuoc().getId() == thuocId) {
                        gioHangDAO.capNhatSoLuong(gh.getId(), gh.getSoLuong() + 1);
                        daTonTai = true;
                        break;
                    }
                }

                // Nếu chưa có trong giỏ, thêm vào giỏ hàng
                if (!daTonTai) {
                    gioHangDAO.themVaoGioHang(khachHangId, thuocId, 1);
                }

                response.sendRedirect("giohang.jsp");
            }
        } catch (NumberFormatException e) {
            response.sendRedirect("giohang.jsp?error=InvalidInput");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("giohang.jsp?error=DatabaseError");
        }
    }
}
