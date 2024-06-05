package com.example.app_01.Constructor;

public class LichThi {
    private String maHP, tenHP, maLop, phongThi, ngayThi, kipThi;
    public LichThi(String maHP, String tenHP, String maLop, String phongThi, String ngayThi, String kipThi) {
        this.maHP = maHP;
        this.tenHP = tenHP;
        this.maLop = maLop;
        this.phongThi = phongThi;
        this.ngayThi = ngayThi;
        this.kipThi = kipThi;
    }

    public String getTenHP() {
        return tenHP;
    }

    public void setTenHP(String tenHP) {
        this.tenHP = tenHP;
    }

    public String getMaHP() {
        return maHP;
    }

    public void setMaHP(String maHP) {
        this.maHP = maHP;
    }

    public String getKipThi() {
        return kipThi;
    }

    public void setKipThi(String kipThi) {
        this.kipThi = kipThi;
    }

    public String getMaLop() {
        return maLop;
    }

    public void setMaLop(String maLop) {
        this.maLop = maLop;
    }

    public String getNgayThi() {
        return ngayThi;
    }

    public void setNgayThi(String ngayThi) {
        this.ngayThi = ngayThi;
    }

    public String getPhongThi() {
        return phongThi;
    }

    public void setPhongThi(String phongThi) {
        this.phongThi = phongThi;
    }
}
