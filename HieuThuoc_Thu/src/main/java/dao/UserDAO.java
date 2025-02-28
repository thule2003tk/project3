package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.User;
import config.DatabaseConnection;

public class UserDAO {
    
    public boolean register(User user) {
        String sql = "INSERT INTO khachhang_thu(ho_ten, email, so_dien_thoai, dia_chi, mat_khau) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, user.getHoTen());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getSoDienThoai());
            ps.setString(4, user.getDiaChi());
            ps.setString(5, user.getMatKhau());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public User login(String email, String matKhau) {
        String sql = "SELECT * FROM khachhang_thu WHERE email = ? AND mat_khau = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, email);
            ps.setString(2, matKhau);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new User(rs.getInt("id"), rs.getString("ho_ten"), rs.getString("email"),
                        rs.getString("so_dien_thoai"), rs.getString("dia_chi"), rs.getString("mat_khau"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
