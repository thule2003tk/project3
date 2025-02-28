package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.GioHang;
import model.Thuoc;

public class GioHangDAO {
    private Connection conn;

    public GioHangDAO(Connection conn) {
        this.conn = conn;
    }

    public List<GioHang> layGioHang(int khachHangId) {
        List<GioHang> dsGioHang = new ArrayList<>();
        String sql = "SELECT gh.id, gh.khachhang_id, gh.thuoc_id, gh.so_luong, " +
                     "t.ten_thuoc, t.gia_ban, t.hinh_anh " +
                     "FROM giohang_thu gh " +
                     "JOIN thuoc_thu t ON gh.thuoc_id = t.id " +
                     "WHERE gh.khachhang_id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, khachHangId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Thuoc thuoc = new Thuoc(
                    rs.getInt("thuoc_id"),
                    rs.getString("ten_thuoc"),
                    sql, sql, sql, sql, null, rs.getDouble("gia_ban"),
                    khachHangId, rs.getString("hinh_anh") // Lấy ảnh thuốc nếu cần
, khachHangId
                );
                GioHang gioHang = new GioHang(
                    rs.getInt("id"),
                    khachHangId,
                    thuoc,
                    rs.getInt("so_luong")
                );
                dsGioHang.add(gioHang);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsGioHang;
    }

    public boolean themVaoGioHang(int khachHangId, int thuocId, int soLuong) {
        String sql = "INSERT INTO giohang_thu (khachhang_id, thuoc_id, so_luong) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, khachHangId);
            stmt.setInt(2, thuocId);
            stmt.setInt(3, soLuong);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean xoaKhoiGioHang(int gioHangId) {
        String sql = "DELETE FROM giohang_thu WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, gioHangId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

	public void capNhatSoLuong(int id, int i) {
		// TODO Auto-generated method stub
		
	}
}
