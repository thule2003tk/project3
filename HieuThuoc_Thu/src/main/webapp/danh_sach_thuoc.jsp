<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List, java.text.SimpleDateFormat" %>
<%@ page import="model.Thuoc" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Danh Sách Thuốc</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-4">
        <h2 class="text-center">Danh Sách Thuốc</h2>
        
        <% 
            List<Thuoc> danhSachThuoc = (List<Thuoc>) request.getAttribute("danhSachThuoc"); 
            if (danhSachThuoc == null || danhSachThuoc.isEmpty()) { 
        %>
            <div class="alert alert-warning text-center">Không có thuốc nào trong danh sách.</div>
        <% } else { %>
        
        <table class="table table-bordered table-hover">
            <thead class="table-dark">
                <tr>
                    <th>ID</th>
                    <th>Tên Thuốc</th>
                    <th>Loại Thuốc</th>
                    <th>Thành Phần</th>
                    <th>Công Dụng</th>
                    <th>Giá Bán</th>
                    <th>Hạn Sử Dụng</th>
                    <th>Hình Ảnh</th>
                    <th>Thao Tác</th>
                </tr>
            </thead>
            <tbody>
                <%
                    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                    for (Thuoc thuoc : danhSachThuoc) {
                        String hanSuDungStr = (thuoc.getHanSuDung() != null) ? sdf.format(thuoc.getHanSuDung()) : "Không có";
                        String hinhAnh = (thuoc.getHinhAnh() != null && !thuoc.getHinhAnh().isEmpty()) ? thuoc.getHinhAnh() : "default.jpg";
                %>
                <tr>
                    <td><%= thuoc.getId() %></td>
                    <td><%= thuoc.getTenThuoc() %></td>
                    <td><%= thuoc.getLoaiThuoc() %></td>
                    <td><%= thuoc.getThanhPhan() %></td>
                    <td><%= thuoc.getCongDung() %></td>
                    <td><%= thuoc.getGiaBan() %> VND</td>
                    <td><%= hanSuDungStr %></td>
                    <td class="text-center">
                        <img src="images/<%= hinhAnh %>" width="50" class="rounded">
                    </td>
                    <td>
                        <a href="ChiTietThuoc?id=<%= thuoc.getId() %>" class="btn btn-info btn-sm">Xem</a>
                        <a href="ThemGioHang?id=<%= thuoc.getId() %>" class="btn btn-success btn-sm">Thêm vào giỏ</a>
                    </td>
                </tr>
                <% } %>
            </tbody>
        </table>
        
        <% } %>
    </div>
</body>
</html>
