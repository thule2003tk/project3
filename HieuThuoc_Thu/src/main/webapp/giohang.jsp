<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="model.GioHang, model.Thuoc" %>
<%@ page import="dao.GioHangDAO" %>
<%@ page import="config.DatabaseConnection" %>
<%@ page import="java.sql.Connection" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Giỏ hàng</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-4">
        <h2 class="text-center">Giỏ hàng của bạn</h2>

        <%
            Connection conn = DatabaseConnection.getConnection();
            GioHangDAO gioHangDAO = new GioHangDAO(conn);
            Integer khachHangId = (Integer) session.getAttribute("khachHangId");
            List<GioHang> gioHangList = (khachHangId != null) ? gioHangDAO.layGioHang(khachHangId) : null;
            conn.close();

            if (gioHangList == null || gioHangList.isEmpty()) {
        %>
            <p class="alert alert-warning text-center">Giỏ hàng trống! Hãy chọn sản phẩm.</p>
        <%
            } else {
        %>

        <table class="table table-bordered text-center">
            <thead class="table-dark">
                <tr>
                    <th>#</th>
                    <th>Tên thuốc</th>
                    <th>Giá</th>
                    <th>Số lượng</th>
                    <th>Thành tiền</th>
                    <th>Hành động</th>
                </tr>
            </thead>
            <tbody>
                <%
                    int index = 1;
                    double tongTien = 0;
                    for (GioHang item : gioHangList) {
                        Thuoc thuoc = item.getThuoc();
                        double thanhTien = thuoc.getGiaBan() * item.getSoLuong();
                        tongTien += thanhTien;
                %>
                <tr>
                    <td><%= index++ %></td>
                    <td><%= thuoc.getTenThuoc() %></td>
                    <td><%= thuoc.getGiaBan() %> VNĐ</td>
                    <td><%= item.getSoLuong() %></td>
                    <td><%= thanhTien %> VNĐ</td>
                    <td>
                        <a href="XoaGioHangServlet?id=<%= item.getId() %>" class="btn btn-danger btn-sm">Xóa</a>
                    </td>
                </tr>
                <% } %>
            </tbody>
        </table>

        <h4 class="text-end">Tổng tiền: <%= tongTien %> VNĐ</h4>

        <div class="text-center mt-3">
            <a href="index.jsp" class="btn btn-primary">Tiếp tục mua hàng</a>
            <a href="ThanhToanServlet" class="btn btn-success">Thanh toán</a>
        </div>

        <% } %>
    </div>
</body>
</html>
