package thu_dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import thu_model.thu_DBConnection;
import thu_model.thu_Thuoc;

public class thu_ThuocDAO {
    private Connection conn;

    public thu_ThuocDAO() throws ClassNotFoundException, SQLException {
        this.conn = thu_DBConnection.getConnection();
    }

    // Lấy danh sách tất cả thuốc
    public List<thu_Thuoc> getAllThuoc() {
        List<thu_Thuoc> danhSachThuoc = new ArrayList<>();
        String sql = "SELECT * FROM Thuoc_Thu";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                thu_Thuoc thuoc = new thu_Thuoc(
                    rs.getInt("id"),
                    rs.getString("tenThuoc"),
                    rs.getString("loaiThuoc"),
                    rs.getDouble("giaBan"),
                    rs.getInt("soLuong"),
                    rs.getString("nhaCungCap")
                );
                danhSachThuoc.add(thuoc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return danhSachThuoc;
    }

    // Thêm thuốc mới
    public boolean addThuoc(thu_Thuoc thuoc) {
        String sql = "INSERT INTO Thuoc_Thu (tenThuoc, loaiThuoc, giaBan, soLuong, nhaCungCap) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, thuoc.getTenThuoc());
            stmt.setString(2, thuoc.getLoaiThuoc());
            stmt.setDouble(3, thuoc.getGiaBan());
            stmt.setInt(4, thuoc.getSoLuong());
            stmt.setString(5, thuoc.getNhaCungCap());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Cập nhật thông tin thuốc
    public boolean updateThuoc(thu_Thuoc thuoc) {
        String sql = "UPDATE Thuoc_Thu SET tenThuoc=?, loaiThuoc=?, giaBan=?, soLuong=?, nhaCungCap=? WHERE id=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, thuoc.getTenThuoc());
            stmt.setString(2, thuoc.getLoaiThuoc());
            stmt.setDouble(3, thuoc.getGiaBan());
            stmt.setInt(4, thuoc.getSoLuong());
            stmt.setString(5, thuoc.getNhaCungCap());
            stmt.setInt(6, thuoc.getId());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Xóa thuốc theo ID
    public boolean deleteThuoc(int id) {
        String sql = "DELETE FROM Thuoc_Thu WHERE id=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Lấy thông tin thuốc theo ID
    public thu_Thuoc getThuocById(int id) {
        String sql = "SELECT * FROM Thuoc_Thu WHERE id=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new thu_Thuoc(
                    rs.getInt("id"),
                    rs.getString("tenThuoc"),
                    rs.getString("loaiThuoc"),
                    rs.getDouble("giaBan"),
                    rs.getInt("soLuong"),
                    rs.getString("nhaCungCap")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
