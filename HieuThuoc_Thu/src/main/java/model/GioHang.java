package model;

public class GioHang {
    private int id;
    private int khachHangId;
    private Thuoc thuoc;
    private int soLuong;

    public GioHang(int id, int khachHangId, Thuoc thuoc, int soLuong) {
        this.id = id;
        this.khachHangId = khachHangId;
        this.thuoc = thuoc;
        this.soLuong = soLuong;
    }

    // Getter và Setter
    public int getId() {
        return id;
    }

    public int getKhachHangId() {
        return khachHangId;
    }

    public Thuoc getThuoc() {
        return thuoc;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
}
