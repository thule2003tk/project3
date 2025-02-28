package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import config.DatabaseConnection;
import model.Thuoc;

public class ThuocDAO {
    
    public ThuocDAO(Connection conn) {
		// TODO Auto-generated constructor stub
	}

	// Lấy danh sách thuốc
    public List<Thuoc> getAllThuoc() {
        List<Thuoc> danhSachThuoc = new ArrayList<>();
        String query = "SELECT * FROM thuoc_thu";
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Thuoc thuoc = new Thuoc(
                    rs.getInt("id"),
                    rs.getString("ten_thuoc"),
                    rs.getString("loai_thuoc"),
                    rs.getString("thanh_phan"),
                    rs.getString("cong_dung"),
                    rs.getString("huong_dan"),
                    rs.getDate("han_su_dung"),
                    rs.getDouble("gia_ban"),
                    rs.getInt("so_luong"),
                    rs.getString("hinh_anh"),
                    rs.getInt("ncc_id")
                );
                danhSachThuoc.add(thuoc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return danhSachThuoc;
    }

    // Thêm thuốc
    public boolean addThuoc(Thuoc thuoc) {
        String query = "INSERT INTO thuoc_thu (ten_thuoc, loai_thuoc, thanh_phan, cong_dung, huong_dan, han_su_dung, gia_ban, so_luong, hinh_anh, ncc_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, thuoc.getTenThuoc());
            pstmt.setString(2, thuoc.getLoaiThuoc());
            pstmt.setString(3, thuoc.getThanhPhan());
            pstmt.setString(4, thuoc.getCongDung());
            pstmt.setString(5, thuoc.getHuongDan());
            pstmt.setDate(6, new java.sql.Date(thuoc.getHanSuDung().getTime()));
            pstmt.setDouble(7, thuoc.getGiaBan());
            pstmt.setInt(8, thuoc.getSoLuong());
            pstmt.setString(9, thuoc.getHinhAnh());
            pstmt.setInt(10, thuoc.getNccId());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Cập nhật thuốc
    public boolean updateThuoc(Thuoc thuoc) {
        String query = "UPDATE thuoc_thu SET ten_thuoc=?, loai_thuoc=?, thanh_phan=?, cong_dung=?, huong_dan=?, han_su_dung=?, gia_ban=?, so_luong=?, hinh_anh=?, ncc_id=? WHERE id=?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, thuoc.getTenThuoc());
            pstmt.setString(2, thuoc.getLoaiThuoc());
            pstmt.setString(3, thuoc.getThanhPhan());
            pstmt.setString(4, thuoc.getCongDung());
            pstmt.setString(5, thuoc.getHuongDan());
            pstmt.setDate(6, new java.sql.Date(thuoc.getHanSuDung().getTime()));
            pstmt.setDouble(7, thuoc.getGiaBan());
            pstmt.setInt(8, thuoc.getSoLuong());
            pstmt.setString(9, thuoc.getHinhAnh());
            pstmt.setInt(10, thuoc.getNccId());
            pstmt.setInt(11, thuoc.getId());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Xóa thuốc
    public boolean deleteThuoc(int id) {
        String query = "DELETE FROM thuoc_thu WHERE id=?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Lấy thuốc theo ID
    public Thuoc getThuocById(int id) {
        String query = "SELECT * FROM thuoc_thu WHERE id=?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Thuoc(
                    rs.getInt("id"),
                    rs.getString("ten_thuoc"),
                    rs.getString("loai_thuoc"),
                    rs.getString("thanh_phan"),
                    rs.getString("cong_dung"),
                    rs.getString("huong_dan"),
                    rs.getDate("han_su_dung"),
                    rs.getDouble("gia_ban"),
                    rs.getInt("so_luong"),
                    rs.getString("hinh_anh"),
                    rs.getInt("ncc_id")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
