package controller;

import java.io.IOException;

import dao.ThuocDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Thuoc;

@WebServlet("/ChiTietThuoc")
public class ChiTietThuocServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ThuocDAO thuocDAO;

    @Override
    public void init() {
        thuocDAO = new ThuocDAO(null);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idParam = request.getParameter("id");
        if (idParam != null) {
            try {
                int id = Integer.parseInt(idParam);
                Thuoc thuoc = thuocDAO.getThuocById(id);
                request.setAttribute("thuoc", thuoc);
                request.getRequestDispatcher("chi_tiet_thuoc.jsp").forward(request, response);
            } catch (NumberFormatException e) {
                response.sendRedirect("DanhSachThuoc");
            }
        } else {
            response.sendRedirect("DanhSachThuoc");
        }
    }
}
