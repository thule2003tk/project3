package thu_controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import thu_dao.thu_ThuocDAO;
import thu_model.thu_CartItem;
import thu_model.thu_Thuoc;

@WebServlet("/thu_CartServlet")
public class thu_CartServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private thu_ThuocDAO productDAO;

    @Override
    public void init() throws ServletException {
        try {
            productDAO = new thu_ThuocDAO();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new ServletException("Lỗi kết nối CSDL: " + e.getMessage(), e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        
        // Kiểm tra và lấy danh sách giỏ hàng từ session
        @SuppressWarnings("unchecked")
		List<thu_CartItem> cart = (List<thu_CartItem>) session.getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<>();
            session.setAttribute("cart", cart);
        }

        try {
            if ("add".equals(action)) {
                handleAddToCart(request, cart);
            } else if ("remove".equals(action)) {
                handleRemoveFromCart(request, cart);
            } else if ("update".equals(action)) {
                handleUpdateCart(request, cart);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendRedirect("cart.jsp?error=invalid_number");
            return;
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("cart.jsp?error=server_error");
            return;
        }

        // Cập nhật session với giỏ hàng mới
        session.setAttribute("cart", cart);
        response.sendRedirect("cart.jsp");
    }

    // Xử lý thêm sản phẩm vào giỏ hàng
    private void handleAddToCart(HttpServletRequest request, List<thu_CartItem> cart) throws SQLException {
        String idParam = request.getParameter("id");
        if (idParam == null || !idParam.matches("\\d+")) {
            throw new NumberFormatException("ID sản phẩm không hợp lệ");
        }
        int productId = Integer.parseInt(idParam);
        thu_Thuoc product = productDAO.getThuocById(productId);

        if (product != null) {
            boolean exists = false;
            for (thu_CartItem item : cart) {
                if (item.getId() == productId) {
                    item.setSoLuong(item.getSoLuong() + 1);
                    exists = true;
                    break;
                }
            }
            if (!exists) {
                cart.add(new thu_CartItem(productId, product.getTenThuoc(), product.getLoaiThuoc(),
                        product.getThanhPhan(), product.getCongDung(), product.getHuongDan(),
                        product.getHanSuDung(), product.getGiaBan(), 1,
                        product.getHinhAnh(), product.getNccId()));
            }
        }
    }

    // Xử lý xóa sản phẩm khỏi giỏ hàng
    private void handleRemoveFromCart(HttpServletRequest request, List<thu_CartItem> cart) {
        String idParam = request.getParameter("id");
        if (idParam == null || !idParam.matches("\\d+")) {
            throw new NumberFormatException("ID sản phẩm không hợp lệ");
        }
        int productId = Integer.parseInt(idParam);
        cart.removeIf(item -> item.getId() == productId);
    }

    // Xử lý cập nhật số lượng sản phẩm trong giỏ hàng
    private void handleUpdateCart(HttpServletRequest request, List<thu_CartItem> cart) {
        String idParam = request.getParameter("id");
        String quantityParam = request.getParameter("quantity");

        if (idParam == null || !idParam.matches("\\d+")) {
            throw new NumberFormatException("ID sản phẩm không hợp lệ");
        }
        if (quantityParam == null || !quantityParam.matches("\\d+")) {
            throw new NumberFormatException("Số lượng không hợp lệ");
        }

        int productId = Integer.parseInt(idParam);
        int newQuantity = Integer.parseInt(quantityParam);

        if (newQuantity <= 0) {
            cart.removeIf(item -> item.getId() == productId);
        } else {
            for (thu_CartItem item : cart) {
                if (item.getId() == productId) {
                    item.setSoLuong(newQuantity);
                    break;
                }
            }
        }
    }
}
