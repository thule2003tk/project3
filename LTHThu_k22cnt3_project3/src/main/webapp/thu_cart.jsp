<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List, thu_model.thu_CartItem, java.math.BigDecimal" %>
<%
List<thu_CartItem> cart = (List<thu_CartItem>) session.getAttribute("cart");
BigDecimal totalAmount = BigDecimal.ZERO;
%>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Giỏ hàng</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <h2>Giỏ hàng của bạn</h2>
    <table border="1">
        <thead>
            <tr>
                <th>Tên thuốc</th>
                <th>Loại thuốc</th>
                <th>Thành phần</th>
                <th>Công dụng</th>
                <th>Hướng dẫn</th>
                <th>Hạn sử dụng</th>
                <th>Giá bán</th>
                <th>Số lượng</th>
                <th>Tổng tiền</th>
            </tr>
        </thead>
        <tbody>
            <% 
            if (cart != null && !cart.isEmpty()) {
                for (thu_CartItem item : cart) {
                    BigDecimal totalItem = item.getGiaBan().multiply(BigDecimal.valueOf(item.getSoLuong()));
                    totalAmount = totalAmount.add(totalItem);
            %>
            <tr>
                <td><%= item.getTenThuoc() %></td>
                <td><%= item.getLoaiThuoc() %></td>
                <td><%= item.getThanhPhan() %></td>
                <td><%= item.getCongDung() %></td>
                <td><%= item.getHuongDan() %></td>
                <td><%= item.getHanSuDung() %></td>
                <td><%= item.getGiaBan() %> VND</td>
                <td><%= item.getSoLuong() %></td>
                <td><%= totalItem %> VND</td>
            </tr>
            <% 
                }
            } else { 
            %>
            <tr>
                <td colspan="9">Giỏ hàng trống</td>
            </tr>
            <% } %>
        </tbody>
    </table>
    <h3>Tổng thanh toán: <%= totalAmount %> VND</h3>
</body>
</html>
