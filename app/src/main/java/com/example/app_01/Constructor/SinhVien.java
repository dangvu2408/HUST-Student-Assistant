package com.example.app_01.Constructor;

public class SinhVien {

    private String MSSV, ho, dem, ten, ngaysinh, tenlop, ctdt, trangthai;

    public SinhVien(String MSSV, String ho, String dem, String ten, String ngaysinh, String tenlop, String ctdt, String trangthai) {
        this.MSSV = MSSV;
        this.ho = ho;
        this.dem = dem;
        this.ten = ten;
        this.ngaysinh = ngaysinh;
        this.tenlop = tenlop;
        this.ctdt = ctdt;
        this.trangthai = trangthai;
    }
    public SinhVien() {
    }

    public String getMSSV() {
        return MSSV;
    }

    public void setMSSV(String MSSV) {
        this.MSSV = MSSV;
    }

    public String getHo() {
        return ho;
    }

    public void setHo(String ho) {
        this.ho = ho;
    }

    public String getDem() {
        return dem;
    }

    public void setDem(String dem) {
        this.dem = dem;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getTenlop() {
        return tenlop;
    }

    public void setTenlop(String tenlop) {
        this.tenlop = tenlop;
    }

    public String getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(String ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    public String getCtdt() {
        return ctdt;
    }

    public void setCtdt(String ctdt) {
        this.ctdt = ctdt;
    }

    public String getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }
}
