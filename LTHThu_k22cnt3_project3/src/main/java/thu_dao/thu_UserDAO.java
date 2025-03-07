package thu_dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import thu_model.thu_DBConnection;
import thu_model.thu_User;

public class thu_UserDAO {
    public static thu_User authenticate(String username, String password) {
        try (Connection conn = thu_DBConnection.getConnection()) { // Đã sửa lỗi
            String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new thu_User(rs.getInt("id"), rs.getString("username"), rs.getString("password"), rs.getString("role"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
