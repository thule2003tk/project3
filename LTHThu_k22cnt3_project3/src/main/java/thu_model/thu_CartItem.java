package thu_model;

import java.math.BigDecimal;

public class thu_CartItem {
    private int id;             
    private String tenThuoc;    
    private String loaiThuoc;   
    private String thanhPhan;   
    private String congDung;    
    private String huongDan;    
    private String hanSuDung;   
    private BigDecimal giaBan;  
    private int soLuong;        
    private String hinhAnh;     
    private int nccId;          

    // Constructor đầy đủ
    public thu_CartItem(int id, String tenThuoc, String loaiThuoc, String thanhPhan, String congDung, 
                    String huongDan, String hanSuDung, BigDecimal giaBan, int soLuong, String hinhAnh, int nccId) {
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

    // Constructor đúng kiểu dữ liệu
    public thu_CartItem(int productId, String tenThuoc2, String loaiThuoc2, String thanhPhan2, String congDung2,
                        String huongDan2, String hanSuDung2, double giaBan2, int soLuong2, String hinhAnh2, int nccId2) {
        this.id = productId;
        this.tenThuoc = tenThuoc2;
        this.loaiThuoc = loaiThuoc2;
        this.thanhPhan = thanhPhan2;
        this.congDung = congDung2;
        this.huongDan = huongDan2;
        this.hanSuDung = hanSuDung2;
        this.giaBan = BigDecimal.valueOf(giaBan2); // Chuyển double thành BigDecimal
        this.soLuong = soLuong2;
        this.hinhAnh = hinhAnh2;
        this.nccId = nccId2;
    }

    // Getter & Setter
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTenThuoc() { return tenThuoc; }
    public void setTenThuoc(String tenThuoc) { this.tenThuoc = tenThuoc; }

    public String getLoaiThuoc() { return loaiThuoc; }
    public void setLoaiThuoc(String loaiThuoc) { this.loaiThuoc = loaiThuoc; }

    public String getThanhPhan() { return thanhPhan; }
    public void setThanhPhan(String thanhPhan) { this.thanhPhan = thanhPhan; }

    public String getCongDung() { return congDung; }
    public void setCongDung(String congDung) { this.congDung = congDung; }

    public String getHuongDan() { return huongDan; }
    public void setHuongDan(String huongDan) { this.huongDan = huongDan; }

    public String getHanSuDung() { return hanSuDung; }
    public void setHanSuDung(String hanSuDung) { this.hanSuDung = hanSuDung; }

    public BigDecimal getGiaBan() { return giaBan; }
    public void setGiaBan(BigDecimal giaBan) { this.giaBan = giaBan; }

    public int getSoLuong() { return soLuong; }
    public void setSoLuong(int soLuong) { this.soLuong = soLuong; }

    public String getHinhAnh() { return hinhAnh; }
    public void setHinhAnh(String hinhAnh) { this.hinhAnh = hinhAnh; }

    public int getNccId() { return nccId; }
    public void setNccId(int nccId) { this.nccId = nccId; }

    // Tính tổng tiền (đã sửa lỗi)
    public BigDecimal getTotal() {
        return giaBan.multiply(BigDecimal.valueOf(soLuong));
    }
}
