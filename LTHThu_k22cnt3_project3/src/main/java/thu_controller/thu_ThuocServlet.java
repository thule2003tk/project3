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

    private void deleteThuoc(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	private void listThuoc(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        List<thu_Thuoc> list = thu_ThuocDAO.getAllThuoc();
        request.setAttribute("thu_thuoc-list", list);
        request.getRequestDispatcher("thu_thuoc-list.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            thu_Thuoc thuoc = thu_ThuocDAO.getThuocById(id);
            if (thuoc != null) {
                request.setAttribute("thuoc", thuoc);
                request.getRequestDispatcher("thu_formthuoc.jsp").forward(request, response);
            } else {
                response.sendRedirect("thuoc");
            }
        } catch (NumberFormatException e) {
            response.sendRedirect("thu_thuoc");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("insert".equals(action)) {
            insertThuoc(request, response);
        } else if ("update".equals(action)) {
            updateThuoc(request, response);
        }
    }

    private void insertThuoc(HttpServletRequest request, HttpServletResponse response) 
            throws IOException {
        thu_Thuoc thuoc = new thu_Thuoc(
            0,
            request.getParameter("tenThuoc"),
            request.getParameter("loaiThuoc"),
            Double.parseDouble(request.getParameter("giaBan")),
            Integer.parseInt(request.getParameter("soLuong")),
            request.getParameter("nhaCungCap")
        );
        thu_ThuocDAO.addThuoc(thuoc);
        response.sendRedirect("thu_thuoc");
    }

    private void updateThuoc(HttpServletRequest request, HttpServletResponse response) 
            throws IOException {
        thu_Thuoc thuoc = new thu_Thuoc(
            Integer.parseInt(request.getParameter("id")),
            request.getParameter("tenThuoc"),
            request.getParameter("loaiThuoc"),
            Double.parseDouble(request.getParameter("giaBan")),
            Integer.parseInt(request.getParameter("soLuong")),
            request.getParameter("nhaCungCap")
        );
        thu_ThuocDAO.updateThuoc(thuoc);
        response.sendRedirect("thuoc");
    }
}
