package thu_controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import thu_dao.thu_UserDAO;
import thu_model.thu_User;

@WebServlet("/thu_LoginServlet")
public class thu_LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        thu_User thu_User = thu_UserDAO.authenticate(username, password);
        if (thu_User != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", thu_User);
            response.sendRedirect("index.jsp"); // Điều hướng về trang chủ sau khi đăng nhập thành công
        } else {
            request.setAttribute("errorMessage", "Tên đăng nhập hoặc mật khẩu sai!");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
