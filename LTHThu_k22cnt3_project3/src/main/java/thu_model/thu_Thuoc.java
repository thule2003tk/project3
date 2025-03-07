package thu_model;

public class thu_Thuoc {
    private int id;
    private String tenThuoc;
    private String loaiThuoc;
    private double giaBan;
    private int soLuong;
    private String nhaCungCap;

    // Constructor không tham số
    public thu_Thuoc() {}

    // Constructor đầy đủ
    public thu_Thuoc(int id, String tenThuoc, String loaiThuoc, double giaBan, int soLuong, String nhaCungCap) {
        this.id = id;
        this.tenThuoc = tenThuoc;
        this.loaiThuoc = loaiThuoc;
        this.giaBan = giaBan;
        this.soLuong = soLuong;
        this.nhaCungCap = nhaCungCap;
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

    public String getNhaCungCap() {
        return nhaCungCap;
    }

    public void setNhaCungCap(String nhaCungCap) {
        this.nhaCungCap = nhaCungCap;
    }

    @Override
    public String toString() {
        return "thu_Thuoc{" +
                "id=" + id +
                ", tenThuoc='" + tenThuoc + '\'' +
                ", loaiThuoc='" + loaiThuoc + '\'' +
                ", giaBan=" + giaBan +
                ", soLuong=" + soLuong +
                ", nhaCungCap='" + nhaCungCap + '\'' +
                '}';
    }

	public String getThanhPhan() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getHinhAnh() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getHanSuDung() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getCongDung() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getHuongDan() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getNccId() {
		// TODO Auto-generated method stub
		return 0;
	}
}
