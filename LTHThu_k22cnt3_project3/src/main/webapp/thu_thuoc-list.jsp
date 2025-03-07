<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List" %>
<%@page import="thu_model.thu_Thuoc" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Danh sách thuốc</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
    <div class="container">
        <h2 class="mt-4">Danh sách thuốc</h2>
        <a href="thuoc?action=new" class="btn btn-primary mb-3">Thêm thuốc mới</a>
        <table class="table table-bordered">
            <thead class="table-dark">
                <tr>
                    <th>ID</th>
                    <th>Tên thuốc</th>
                    <th>Loại thuốc</th>
                    <th>Giá bán</th>
                    <th>Số lượng</th>
                    <th>Nhà cung cấp</th>
                    <th>Hành động</th>
                </tr>
            </thead>
            <tbody>
                <%
                List<thu_Thuoc> list = (List<thu_Thuoc>) request.getAttribute("thu_thuoc-list");
                                    if (list != null && !list.isEmpty()) {
                                        for (thu_Thuoc t : list) {
                %>
                <tr>
                    <td class="text-center"><%= t.getId() %></td>
                    <td><%= t.getTenThuoc() %></td>
                    <td><%= t.getLoaiThuoc() %></td>
                    <td><%= t.getGiaBan() %> VNĐ</td>
                    <td><%= t.getSoLuong() %></td>
                    <td><%= t.getNhaCungCap() %></td>
                    <td>
                        <a href="thuoc?action=edit&id=<%= t.getId() %>" class="btn btn-warning btn-sm">Sửa</a>
                        <a href="thuoc?action=delete&id=<%= t.getId() %>" 
                           class="btn btn-danger btn-sm"
                           onclick="return confirm('Bạn có chắc chắn muốn xóa không?');">Xóa</a>
                    </td>
                </tr>
                <%  
                        }
                    } else {
                %>
                <tr>
                    <td colspan="7" class="text-center">Không có thuốc nào trong danh sách.</td>
                </tr>
                <%  
                    }
                %>
            </tbody>
        </table>
    </div>
</body>
</html>
