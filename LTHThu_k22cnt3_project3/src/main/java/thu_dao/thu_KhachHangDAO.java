package thu_dao;

import java.sql.*;

import thu_model.thu_KhachHang;

public class thu_KhachHangDAO {
    private Connection conn;

    public thu_KhachHangDAO(Connection conn) {
        this.conn = conn;
    }

    // Đăng ký khách hàng
    public boolean register(thu_KhachHang kh) {
        try {
            String query = "INSERT INTO khachhang_thu (ho_ten, email, so_dien_thoai, dia_chi, mat_khau) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, kh.getHoTen());
            ps.setString(2, kh.getEmail());
            ps.setString(3, kh.getSoDienThoai());
            ps.setString(4, kh.getDiaChi());
            ps.setString(5, kh.getMatKhau());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Kiểm tra đăng nhập
    public thu_KhachHang login(String email, String matKhau) {
        try {
            String query = "SELECT * FROM khachhang_thu WHERE email = ? AND mat_khau = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, email);
            ps.setString(2, matKhau);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new thu_KhachHang(rs.getInt("id"), rs.getString("ho_ten"),
                        rs.getString("email"), rs.getString("so_dien_thoai"),
                        rs.getString("dia_chi"), rs.getString("mat_khau"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
