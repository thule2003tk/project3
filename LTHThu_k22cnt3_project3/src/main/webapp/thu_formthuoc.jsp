<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="thu_model.thu_Thuoc" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <title>Thêm/Sửa Thuốc</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <style>
        body {
            background-color: #f8f9fa;
        }
        .form-container {
            background: #ffffff;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.1);
            max-width: 500px;
            margin: auto;
            margin-top: 50px;
        }
        h2 {
            text-align: center;
            font-weight: bold;
            color: #007bff;
            margin-bottom: 20px;
        }
        .form-control {
            border-radius: 10px;
        }
        .btn {
            border-radius: 10px;
        }
    </style>
</head>
<body>
    <div class="form-container">
        <h2><%=(request.getAttribute("thuoc") == null) ? "Thêm Thuốc" : "Cập Nhật Thuốc"%></h2>
        <form action="thuoc?action=<%=(request.getAttribute("thuoc") == null) ? "insert" : "update"%>" method="post">
            <%
            thu_Thuoc thuoc = (thu_Thuoc) request.getAttribute("thuoc");
                            if (thuoc != null) {
            %>
                <input type="hidden" name="id" value="<%= thuoc.getId() %>">
            <% } %>

            <div class="mb-3">
                <label><i class="fas fa-capsules"></i> Tên Thuốc:</label>
                <input type="text" name="tenThuoc" class="form-control" value="<%= (thuoc != null) ? thuoc.getTenThuoc() : "" %>" required>
            </div>
            <div class="mb-3">
                <label><i class="fas fa-list"></i> Loại Thuốc:</label>
                <input type="text" name="loaiThuoc" class="form-control" value="<%= (thuoc != null) ? thuoc.getLoaiThuoc() : "" %>" required>
            </div>
            <div class="mb-3">
                <label><i class="fas fa-dollar-sign"></i> Giá Bán:</label>
                <input type="number" name="giaBan" class="form-control" value="<%= (thuoc != null) ? thuoc.getGiaBan() : "" %>" required>
            </div>
            <div class="mb-3">
                <label><i class="fas fa-box"></i> Số Lượng:</label>
                <input type="number" name="soLuong" class="form-control" value="<%= (thuoc != null) ? thuoc.getSoLuong() : "" %>" required>
            </div>
            <div class="mb-3">
                <label><i class="fas fa-truck"></i> Nhà Cung Cấp:</label>
                <input type="text" name="nhaCungCap" class="form-control" value="<%= (thuoc != null) ? thuoc.getNhaCungCap() : "" %>" required>
            </div>

            <button type="submit" class="btn btn-primary w-100"><i class="fas fa-save"></i> Lưu</button>
            <a href="thuoc" class="btn btn-secondary w-100 mt-2"><i class="fas fa-arrow-left"></i> Trở lại</a>
        </form>
    </div>
</body>
</html>
