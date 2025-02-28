package controller;

import java.io.IOException;
import java.util.List;

import dao.ThuocDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Thuoc;

@WebServlet("/Thuoc")
public class ThuocServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ThuocDAO thuocDAO;

    @Override
    public void init() throws ServletException {
        thuocDAO = new ThuocDAO(null); // Xóa tham số Connection vì nó đã được xử lý trong ThuocDAO
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Thuoc> danhSachThuoc = thuocDAO.getAllThuoc();
        
        if (danhSachThuoc == null || danhSachThuoc.isEmpty()) {
            request.setAttribute("message", "Không có thuốc nào trong danh sách.");
        } else {
            request.setAttribute("danhSachThuoc", danhSachThuoc);
        }
        
        request.getRequestDispatcher("danh_sach_thuoc.jsp").forward(request, response);
    }
}
