package com.example.app_01.Constructor;

public class CourseScore {
    private String hocKi, maHP, tenHP, tinchi, lophoc, diemQT, diemthi, diemchu;

    public CourseScore() {}
    public CourseScore(String hocKi, String maHP, String tenHP, String tinchi, String lophoc, String diemQT, String diemthi, String diemchu) {
        this.hocKi = hocKi;
        this.maHP = maHP;
        this.tenHP = tenHP;
        this.tinchi = tinchi;
        this.lophoc = lophoc;
        this.diemQT = diemQT;
        this.diemthi = diemthi;
        this.diemchu = diemchu;
    }

    public String getHocKi() {
        return hocKi;
    }

    public void setHocKi(String hocKi) {
        this.hocKi = hocKi;
    }

    public String getMaHP() {
        return maHP;
    }

    public void setMaHP(String maHP) {
        this.maHP = maHP;
    }

    public String getTenHP() {
        return tenHP;
    }

    public void setTenHP(String tenHP) {
        this.tenHP = tenHP;
    }

    public String getTinchi() {
        return tinchi;
    }

    public void setTinchi(String tinchi) {
        this.tinchi = tinchi;
    }

    public String getLophoc() {
        return lophoc;
    }

    public void setLophoc(String lophoc) {
        this.lophoc = lophoc;
    }

    public String getDiemQT() {
        return diemQT;
    }

    public void setDiemQT(String diemQT) {
        this.diemQT = diemQT;
    }

    public String getDiemthi() {
        return diemthi;
    }

    public void setDiemthi(String diemthi) {
        this.diemthi = diemthi;
    }

    public String getDiemchu() {
        return diemchu;
    }

    public void setDiemchu(String diemchu) {
        this.diemchu = diemchu;
    }
}
