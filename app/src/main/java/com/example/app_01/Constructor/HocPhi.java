package com.example.app_01.Constructor;

public class HocPhi {
    private String maHocPhan, tenHocphan, tienTrenMotTC, tinChihp, heSoHP, tongTienHP, trangThaiDK, loaiDK, ghichU;
    public HocPhi(String maHocPhan, String tenHocphan, String tienTrenMotTC, String tinChihp, String heSoHP, String tongTienHP, String trangThaiDK, String loaiDK, String ghichU) {
        this.maHocPhan = maHocPhan;
        this.tenHocphan = tenHocphan;
        this.tienTrenMotTC = tienTrenMotTC;
        this.tinChihp = tinChihp;
        this.heSoHP = heSoHP;
        this.tongTienHP = tongTienHP;
        this.trangThaiDK = trangThaiDK;
        this.loaiDK = loaiDK;
        this.ghichU = ghichU;
    }

    public String getMaHocPhan() {
        return maHocPhan;
    }

    public void setMaHocPhan(String maHocPhan) {
        this.maHocPhan = maHocPhan;
    }

    public String getTenHocphan() {
        return tenHocphan;
    }

    public void setTenHocphan(String tenHocphan) {
        this.tenHocphan = tenHocphan;
    }

    public String getTienTrenMotTC() {
        return tienTrenMotTC;
    }

    public void setTienTrenMotTC(String tienTrenMotTC) {
        this.tienTrenMotTC = tienTrenMotTC;
    }

    public String getHeSoHP() {
        return heSoHP;
    }

    public void setHeSoHP(String heSoHP) {
        this.heSoHP = heSoHP;
    }

    public String getTinChihp() {
        return tinChihp;
    }

    public void setTinChihp(String tinChihp) {
        this.tinChihp = tinChihp;
    }

    public String getLoaiDK() {
        return loaiDK;
    }

    public void setLoaiDK(String loaiDK) {
        this.loaiDK = loaiDK;
    }

    public String getGhichU() {
        return ghichU;
    }

    public void setGhichU(String ghichU) {
        this.ghichU = ghichU;
    }

    public String getTongTienHP() {
        return tongTienHP;
    }

    public void setTongTienHP(String tongTienHP) {
        this.tongTienHP = tongTienHP;
    }

    public String getTrangThaiDK() {
        return trangThaiDK;
    }

    public void setTrangThaiDK(String trangThaiDK) {
        this.trangThaiDK = trangThaiDK;
    }
}
