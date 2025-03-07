<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Trang chủ</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
    <h2>Chào mừng đến với trang quản lý</h2>
    <% 
        HttpSession sessionDangNhap = request.getSession(false);
        String nguoiDung = (sessionDangNhap != null) ? (String) sessionDangNhap.getAttribute("thu_User") : null;
        if (nguoiDung != null) {
    %>
        <p>Xin chào, <%= nguoiDung %>!</p>
        <a href="thu_formthuoc">Đăng xuất</a>
    <% } else { %>
        <p>Bạn chưa đăng nhập. <a href="thu_formthuoc.jsp">Đăng nhập</a></p>
    <% } %>
</body>
</html>
