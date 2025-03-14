<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Giỏ Hàng</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>

<div class="container mt-5">
    <h2>🛒 Giỏ Hàng Của Bạn</h2>
    <table class="table table-bordered">
        <thead>
            <tr>
                <th>Sản phẩm</th>
                <th>Giá</th>
                <th>Số lượng</th>
                <th>Tổng</th>
                <th>Hành động</th>
            </tr>
        </thead>
        <tbody id="cart-body">
            <!-- Dữ liệu sẽ được load từ localStorage -->
        </tbody>
    </table>

    <h4 class="text-end">Tổng tiền: <span id="totalPrice">0</span> VNĐ</h4>
    
    <div class="text-end">
        <button class="btn btn-danger" onclick="clearCart()">Xóa giỏ hàng</button>
        <button class="btn btn-primary" onclick="placeOrder()">Đặt hàng</button>
    </div>
</div>

<script>
    function loadCart() {
        let cart = JSON.parse(localStorage.getItem("cart")) || [];
        let cartBody = document.getElementById("cart-body");
        let totalPrice = 0;

        cartBody.innerHTML = "";
        cart.forEach((item, index) => {
            let itemTotal = item.price * item.quantity;
            totalPrice += itemTotal;

            cartBody.innerHTML += `
                <tr>
                    <td>${item.title}</td>
                    <td>${Number(item.price).toLocaleString()} VNĐ</td>
                    <td><input type="number" value="${item.quantity}" min="1" 
                               onchange="updateQuantity(${index}, this.value)"></td>
                    <td>${itemTotal.toLocaleString()} VNĐ</td>
                    <td><button class="btn btn-danger btn-sm" onclick="removeItem(${index})">Xóa</button></td>
                </tr>`;
        });

        document.getElementById("totalPrice").innerText = totalPrice.toLocaleString();
    }

    function updateQuantity(index, newQuantity) {
        let cart = JSON.parse(localStorage.getItem("cart"));
        cart[index].quantity = newQuantity;
        localStorage.setItem("cart", JSON.stringify(cart));
        loadCart();
    }

    function removeItem(index) {
        let cart = JSON.parse(localStorage.getItem("cart"));
        cart.splice(index, 1);
        localStorage.setItem("cart", JSON.stringify(cart));
        loadCart();
    }

    function clearCart() {
        localStorage.removeItem("cart");
        loadCart();
    }

    function placeOrder() {
        let cart = JSON.parse(localStorage.getItem("cart"));
        if (cart.length === 0) {
            alert("Giỏ hàng trống!");
            return;
        }

        $.ajax({
            url: "ThuMuaHangServlet",
            type: "POST",
            data: { cartData: JSON.stringify(cart) },
            success: function(response) {
                alert("Đặt hàng thành công!");
                localStorage.removeItem("cart");
                window.location.href = "thu_cart.jsp";
            },
            error: function() {
                alert("Đã xảy ra lỗi khi đặt hàng!");
            }
        });
    }

    loadCart();
</script>

</body>
</html>
