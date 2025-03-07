<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Đăng ký</title>
    <link rel="stylesheet" type="text/css" href="css/Style.css">
</head>
<body>

<div class="navbar">
    <a href="thu_thuoc-list.jsp">Trang chủ</a>
    <a href="thu_login.jsp">Đăng nhập</a>
    <a href="thu_dang-ky.jsp">Đăng ký</a>
</div>

<h2>Đăng ký tài khoản</h2>
<div class="form-container">
    <form action="dang-ky" method="post">
        <input type="text" name="ten_nguoi_dung" placeholder="Họ tên" required>
        <input type="email" name="email" placeholder="Email" required>
        <input type="password" name="mat_khau" placeholder="Mật khẩu" required>
        <button type="submit">Đăng ký</button>
    </form>
    <a href="thu_formthuoc.jsp">Đã có tài khoản? Đăng nhập</a>
</div>

<div class="footer">
    © 2025 Quản lý hiệu thuốc | LeThu
</div>

</body>
</html>
