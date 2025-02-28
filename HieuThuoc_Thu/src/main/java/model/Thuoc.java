package model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Thuoc {
    private int id;
    private String tenThuoc;
    private String loaiThuoc;
    private String thanhPhan;
    private String congDung;
    private String huongDan;
    private Date hanSuDung; // Sử dụng java.util.Date thay vì java.sql.Date
    private double giaBan;
    private int soLuong;
    private String hinhAnh;
    private int nccId;

    // Constructor không tham số
    public Thuoc() {}

    // Constructor đầy đủ
    public Thuoc(int id, String tenThuoc, String loaiThuoc, String thanhPhan, String congDung,
                 String huongDan, Date hanSuDung, double giaBan, int soLuong, String hinhAnh, int nccId) {
        this.id = id;
        this.tenThuoc = tenThuoc;
        this.loaiThuoc = loaiThuoc;
        this.thanhPhan = thanhPhan;
        this.congDung = congDung;
        this.huongDan = huongDan;
        this.hanSuDung = hanSuDung;
        this.giaBan = giaBan;
        this.soLuong = soLuong;
        this.hinhAnh = hinhAnh;
        this.nccId = nccId;
    }

    // Getters và Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenThuoc() {
        return tenThuoc;
    }

    public void setTenThuoc(String tenThuoc) {
        this.tenThuoc = tenThuoc;
    }

    public String getLoaiThuoc() {
        return loaiThuoc;
    }

    public void setLoaiThuoc(String loaiThuoc) {
        this.loaiThuoc = loaiThuoc;
    }

    public String getThanhPhan() {
        return thanhPhan;
    }

    public void setThanhPhan(String thanhPhan) {
        this.thanhPhan = thanhPhan;
    }

    public String getCongDung() {
        return congDung;
    }

    public void setCongDung(String congDung) {
        this.congDung = congDung;
    }

    public String getHuongDan() {
        return huongDan;
    }

    public void setHuongDan(String huongDan) {
        this.huongDan = huongDan;
    }

    public Date getHanSuDung() {
        return hanSuDung;
    }

    public void setHanSuDung(Date hanSuDung) {
        this.hanSuDung = hanSuDung;
    }

    public double getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(double giaBan) {
        this.giaBan = giaBan;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public int getNccId() {
        return nccId;
    }

    public void setNccId(int nccId) {
        this.nccId = nccId;
    }

    // Phương thức trả về hạn sử dụng dưới dạng chuỗi (fix lỗi khi giá trị null)
    public String getHanSuDungAsString() {
        if (hanSuDung != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            return sdf.format(hanSuDung);
        }
        return "Không có dữ liệu";
    }

    // Thêm phương thức toString để debug
    @Override
    public String toString() {
        return "Thuoc{" +
                "id=" + id +
                ", tenThuoc='" + tenThuoc + '\'' +
                ", loaiThuoc='" + loaiThuoc + '\'' +
                ", thanhPhan='" + thanhPhan + '\'' +
                ", congDung='" + congDung + '\'' +
                ", huongDan='" + huongDan + '\'' +
                ", hanSuDung=" + getHanSuDungAsString() +
                ", giaBan=" + giaBan +
                ", soLuong=" + soLuong +
                ", hinhAnh='" + hinhAnh + '\'' +
                ", nccId=" + nccId +
                '}';
    }
}
