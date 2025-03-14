<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Hiệu Thuốc Online</title>

    <!-- Bootstrap & FontAwesome -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">

    <style>
        /* Ảnh nền */
        body {
            background-image: url('images/index1.jpg');
            background-size: cover;
            background-position: center;
            background-attachment: fixed;
            min-height: 100vh;
            display: flex;
            flex-direction: column;
            background-color: #2c3e50; /* Màu nền trầm hơn */
        }

        /* Navbar */
        .navbar {
            background-color: #1b2838; /* Màu tối hơn */
        }
        .navbar .nav-link {
            color: white;
            font-weight: bold;
        }
        .navbar .nav-link:hover {
            color: #ffcc00;
        }
        .search-box {
            width: 300px;
        }

        /* Sản phẩm */
        .product-card {
            border: 1px solid #444;
            border-radius: 10px;
            overflow: hidden;
            transition: 0.3s;
            background: #34495e; /* Nền trầm hơn */
            color: white;
        }
        .product-card:hover {
            box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.4);
        }
        .product-card img {
            width: 100%;
            height: 200px;
            object-fit: cover;
        }
        .product-card .card-body {
            text-align: center;
        }
        .product-card .btn {
            margin: 5px;
        }

        /* Footer */
        .footer {
            background-color: #1b2838;
            color: white;
            text-align: center;
            padding: 10px 0;
            margin-top: auto;
        }
    </style>
</head>
<body>

    <!-- Navbar -->
    <nav class="navbar navbar-expand-lg navbar-dark">
        <div class="container">
            <a class="navbar-brand" href="#">Hiệu Thuốc Online</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
                <span class="navbar-toggler-icon"></span>
            </button>
            
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav me-auto">
                    <li class="nav-item"><a class="nav-link" href="thu_trangchu.jsp">Trang chủ</a></li>
                    <li class="nav-item"><a class="nav-link" href="thu_cart.jsp">Giỏ hàng</a></li>
                    <li class="nav-item"><a class="nav-link" href="thu_thuoc-list.jsp">Thuốc</a></li>
                    <li class="nav-item"><a class="nav-link" href="tuvan.jsp">Tư vấn sức khỏe</a></li>
                    <li class="nav-item"><a class="nav-link" href="lienhe.jsp">Liên hệ</a></li>
                </ul>
                <form class="d-flex">
                    <input class="form-control me-2 search-box" type="search" placeholder="Tìm thuốc..." aria-label="Search">
                    <button class="btn btn-warning" type="submit"><i class="fa fa-search"></i> Tìm</button>
                </form>
            </div>
        </div>
    </nav>

    <!-- Danh sách thuốc -->
    <div class="container mt-4">
        <h3 class="text-center mb-4 text-white">Danh sách thuốc</h3>
        <div class="row">
            <!-- Sản phẩm mẫu -->
            <%
                String[][] sanPhams = {
                    {"Acetylcysteine 200mg", "Hỗ trợ điều trị bệnh phổi", "500,000 VNĐ", "images/Acetylcysteine200mg.jpg"},
                    {"Amoxicillin", "Kháng sinh phổ rộng", "300,000 VNĐ", "images/Amoxicillin.jpg"},
                    {"Vitamin C 1000mg", "Tăng cường sức đề kháng", "400,000 VNĐ", "images/VitaminC1000mg.jpg"},
                    {"Paracetamol", "Giảm đau, hạ sốt", "200,000 VNĐ", "images/Paracetamol.jpg"},
                    {"Ibuprofen", "Chống viêm, giảm đau", "250,000 VNĐ", "images/ibuprofen.jpg"},
                    {"Omega-3", "Tốt cho tim mạch", "450,000 VNĐ", "images/Omeprazole20mg.jpg"}
                };
                for (int i = 0; i < sanPhams.length; i++) {
            %>
            <div class="col-md-4">
                <div class="card product-card">
                    <img src="<%= sanPhams[i][3] %>" alt="<%= sanPhams[i][0] %>">
                    <div class="card-body">
                        <h5 class="card-title"><%= sanPhams[i][0] %></h5>
                        <p class="card-text"><%= sanPhams[i][1] %></p>
                        <p><strong>Giá: <%= sanPhams[i][2] %></strong></p>
                        <button class="btn btn-success"><i class="fa fa-shopping-cart"></i> Mua ngay</button>
                        <button class="btn btn-primary" onclick="showProductModal('<%= sanPhams[i][0] %>', '<%= sanPhams[i][1] %>', '<%= sanPhams[i][2] %>', '<%= sanPhams[i][3] %>')">
                            <i class="fa fa-plus"></i> Thêm vào giỏ
                        </button>
                    </div>
                </div>
            </div>
            <% } %>
        </div>
    </div>

    <!-- Modal hiển thị thông tin sản phẩm -->
    <div class="modal fade" id="productModal" tabindex="-1" aria-labelledby="productModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="productModalLabel">Thông tin sản phẩm</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                </div>
                <div class="modal-body text-center">
                    <img id="modalProductImage" src="" class="img-fluid mb-3">
                    <h5 id="modalProductName"></h5>
                    <p id="modalProductDesc"></p>
                    <p><strong id="modalProductPrice"></strong></p>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-success"><i class="fa fa-shopping-cart"></i> Mua ngay</button>
                    <button class="btn btn-primary"><i class="fa fa-plus"></i> Xác nhận thêm vào giỏ</button>
                </div>
            </div>
        </div>
    </div>

    <script>
        function showProductModal(name, desc, price, image) {
            document.getElementById("modalProductName").innerText = name;
            document.getElementById("modalProductDesc").innerText = desc;
            document.getElementById("modalProductPrice").innerText = price;
            document.getElementById("modalProductImage").src = image;
            new bootstrap.Modal(document.getElementById("productModal")).show();
        }
    </script>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
