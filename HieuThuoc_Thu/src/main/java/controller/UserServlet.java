package controller;
import java.io.IOException;

import dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;

@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDAO userDAO = new UserDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("register".equals(action)) {
            String hoTen = request.getParameter("hoTen");
            String email = request.getParameter("email");
            String soDienThoai = request.getParameter("soDienThoai");
            String diaChi = request.getParameter("diaChi");
            String matKhau = request.getParameter("matKhau");
            User user = new User(0, hoTen, email, soDienThoai, diaChi, matKhau);
            if (userDAO.register(user)) {
                response.sendRedirect("login.jsp");
            } else {
                response.sendRedirect("register.jsp?error=fail");
            }
        }

        if ("login".equals(action)) {
            String email = request.getParameter("email");
            String matKhau = request.getParameter("matKhau");
            User user = userDAO.login(email, matKhau);
            if (user != null) {
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                response.sendRedirect("index.jsp");
            } else {
                response.sendRedirect("login.jsp?error=invalid");
            }
        }

        if ("logout".equals(action)) {
            HttpSession session = request.getSession();
            session.invalidate();
            response.sendRedirect("login.jsp");
        }
    }
}
