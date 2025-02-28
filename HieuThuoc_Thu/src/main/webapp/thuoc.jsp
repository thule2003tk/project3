<%@ page import="java.util.List, model.Thuoc" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Danh sách Thuốc</title>
</head>
<body>
    <h2>Danh sách thuốc</h2>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Tên thuốc</th>
            <th>Loại</th>
            <th>Giá</th>
            <th>Số lượng</th>
        </tr>
        <% List<Thuoc> list = (List<Thuoc>) request.getAttribute("listThuoc");
           for (Thuoc t : list) { %>
        <tr>
            <td><%= t.getId() %></td>
            <td><%= t.getTenThuoc() %></td>
            <td><%= t.getLoaiThuoc() %></td>
            <td><%= t.getGiaBan() %></td>
            <td><%= t.getSoLuong() %></td>
        </tr>
        <% } %>
    </table>
</body>
</html>
