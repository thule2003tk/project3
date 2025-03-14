package thu_controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import thu_dao.thu_ThuocDAO;
import thu_model.thu_Thuoc;

@WebServlet("/thuoc")
public class thu_ThuocServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private thu_ThuocDAO thu_ThuocDAO;

    @Override
    public void init() {
        try {
            thu_ThuocDAO = new thu_ThuocDAO();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException("Lỗi khởi tạo DAO: " + e.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) action = "list";
        
        try {
            switch (action) {
                case "new":
                    request.getRequestDispatcher("thu_formthuoc.jsp").forward(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                case "delete":
                    deleteThuoc(request, response);
                    break;
                default:
                    listThuoc(request, response);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        thu_Thuoc thuoc = thu_ThuocDAO.getThuocById(id);
        request.setAttribute("thuoc", thuoc);
        request.getRequestDispatcher("thu_formthuoc.jsp").forward(request, response);
    }

    private void listThuoc(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        List<thu_Thuoc> list = thu_ThuocDAO.getAllThuoc();
        request.setAttribute("thu_thuoc_list", list);
        request.getRequestDispatcher("thu_thuoc-list.jsp").forward(request, response);
    }

    private void deleteThuoc(HttpServletRequest request, HttpServletResponse response) 
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        thu_ThuocDAO.deleteThuoc(id);
        response.sendRedirect("thuoc?action=list");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String idStr = request.getParameter("id");
        String tenThuoc = request.getParameter("tenThuoc");
        String loaiThuoc = request.getParameter("loaiThuoc");
        double giaBan = Double.parseDouble(request.getParameter("giaBan"));
        int soLuong = Integer.parseInt(request.getParameter("soLuong"));
        String nhaCungCap = request.getParameter("nhaCungCap");

        thu_Thuoc thuoc = new thu_Thuoc(
            idStr == null ? 0 : Integer.parseInt(idStr),
            tenThuoc, loaiThuoc, giaBan, soLuong, nhaCungCap
        );

        if (idStr == null || idStr.isEmpty()) {
            thu_ThuocDAO.addThuoc(thuoc);
        } else {
            thu_ThuocDAO.updateThuoc(thuoc);
        }
        response.sendRedirect("thuoc?action=list");
    }
}
