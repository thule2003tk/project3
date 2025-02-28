<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="model.Thuoc" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Chi Tiết Thuốc</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-4">
        <h2 class="text-center">Chi Tiết Thuốc</h2>
        <%
            Thuoc thuoc = (Thuoc) request.getAttribute("thuoc");
            if (thuoc != null) {
        %>
        <div class="card">
            <div class="row g-0">
                <div class="col-md-4">
                    <img src="images/<%= thuoc.getHinhAnh() %>" class="img-fluid rounded-start" alt="<%= thuoc.getTenThuoc() %>">
                </div>
                <div class="col-md-8">
                    <div class="card-body">
                        <h5 class="card-title"><%= thuoc.getTenThuoc() %></h5>
                        <p><strong>Loại thuốc:</strong> <%= thuoc.getLoaiThuoc() %></p>
                        <p><strong>Thành phần:</strong> <%= thuoc.getThanhPhan() %></p>
                        <p><strong>Công dụng:</strong> <%= thuoc.getCongDung() %></p>
                        <p><strong>Hướng dẫn:</strong> <%= thuoc.getHuongDan() %></p>
                        <p><strong>Hạn sử dụng:</strong> <%= thuoc.getHanSuDung() %></p>
                        <p><strong>Giá bán:</strong> <%= thuoc.getGiaBan() %> VND</p>
                        <a href="ThemGioHang?id=<%= thuoc.getId() %>" class="btn btn-success">Thêm vào giỏ hàng</a>
                        <a href="DanhSachThuoc" class="btn btn-secondary">Quay lại</a>
                    </div>
                </div>
            </div>
        </div>
        <%
            } else {
        %>
        <p class="text-danger">Không tìm thấy thông tin thuốc.</p>
        <a href="DanhSachThuoc" class="btn btn-secondary">Quay lại</a>
        <%
            }
        %>
    </div>
</body>
</html>
