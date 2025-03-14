package thu_model;

public class thu_ChiTietDonHang {
    private int maChiTiet;
    private int maDonHang;
    private String tenSanPham;
    private int soLuong;
    private double donGia;

    // Constructor
    public thu_ChiTietDonHang(int maChiTiet, int maDonHang, String tenSanPham, int soLuong, double donGia) {
        this.maChiTiet = maChiTiet;
        this.maDonHang = maDonHang;
        this.tenSanPham = tenSanPham;
        this.soLuong = soLuong;
        this.donGia = donGia;
    }

    // Constructor không tham số
    public thu_ChiTietDonHang() {
    }

    // Getter và Setter
    public int getMaChiTiet() {
        return maChiTiet;
    }

    public void setMaChiTiet(int maChiTiet) {
        this.maChiTiet = maChiTiet;
    }

    public int getMaDonHang() {
        return maDonHang;
    }

    public void setMaDonHang(int maDonHang) {
        this.maDonHang = maDonHang;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }

    // Phương thức tính tổng giá
    public double tinhTongGia() {
        return this.soLuong * this.donGia;
    }

    // Phương thức hiển thị thông tin
    @Override
    public String toString() {
        return "thu_ChiTietDonHang{" +
                "maChiTiet=" + maChiTiet +
                ", maDonHang=" + maDonHang +
                ", tenSanPham='" + tenSanPham + '\'' +
                ", soLuong=" + soLuong +
                ", donGia=" + donGia +
                ", tongGia=" + tinhTongGia() +
                '}';
    }
}
