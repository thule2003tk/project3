<%@ page import="java.sql.*" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Danh sách thuốc</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>

<div class="container mt-4">
    <h2 class="text-center text-primary">Danh Sách Thuốc</h2>
    
    <table class="table table-bordered table-hover">
        <thead class="table-success">
            <tr>
                <th>ID</th>
                <th>Tên Thuốc</th>
                <th>Mô Tả</th>
                <th>Giá</th>
                <th>Hình Ảnh</th>
                <th>Hành Động</th>
            </tr>
        </thead>
        <tbody>
            <%
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hieuthuoc_thu", "root", "");
                    Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery("SELECT * FROM thuoc_thu");

                    if (!rs.isBeforeFirst()) { // Kiểm tra nếu bảng không có dữ liệu
            %>
                    <tr>
                        <td colspan="6" class="text-center text-danger">Không có dữ liệu!</td>
                    </tr>
            <%
                    } else {
                        while (rs.next()) {
            %>
            <tr>
                <td><%= rs.getInt("thu_id") %></td>
                <td><%= rs.getString("thu_ten_thuoc") %></td>
                <td><%= rs.getString("thu_cong_dung") %></td>
                <td><%= rs.getDouble("thu_gia_ban") %> VNĐ</td>
                <td>
                    <img src="<%= rs.getString("thu_hinh_anh") %>" width="100" class="img-thumbnail">
                </td>
                <td>
                    <a href="thu_formthuoc.jsp?id=<%= rs.getInt("thu_id") %>" class="btn btn-warning btn-sm">Sửa</a>
                    <a href="thu_formthuoc.jsp?id=<%= rs.getInt("thu_id") %>" class="btn btn-danger btn-sm" onclick="return confirm('Bạn có chắc chắn muốn xóa?');">Xóa</a>
                </td>
            </tr>
            <%
                        }
                    }
                    rs.close();
                    stmt.close();
                    conn.close();
                } catch (Exception e) {
            %>
            <tr>
                <td colspan="6" class="text-danger text-center">Lỗi kết nối CSDL: <%= e.getMessage() %></td>
            </tr>
            <%
                }
            %>
        </tbody>
    </table>

    <a href="thu_formthuoc.jsp" class="btn btn-primary">Thêm Thuốc Mới</a>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
