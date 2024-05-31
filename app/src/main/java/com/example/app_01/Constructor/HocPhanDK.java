package com.example.app_01.Constructor;

public class HocPhanDK {
    private String maHPDK, tenHPDK, ngayDK, TTDK, soTCDK;
    private boolean isChecked;
    public HocPhanDK(String maHPDK, String tenHPDK, String ngayDK, String TTDK, String soTCDK, boolean isChecked) {
        this.maHPDK = maHPDK;
        this.tenHPDK = tenHPDK;
        this.ngayDK = ngayDK;
        this.TTDK = TTDK;
        this.soTCDK = soTCDK;
        this.isChecked = isChecked;
    }

    public String getMaHPDK() {
        return maHPDK;
    }

    public void setMaHPDK(String maHPDK) {
        this.maHPDK = maHPDK;
    }

    public String getNgayDK() {
        return ngayDK;
    }

    public void setNgayDK(String ngayDK) {
        this.ngayDK = ngayDK;
    }

    public String getSoTCDK() {
        return soTCDK;
    }

    public void setSoTCDK(String soTCDK) {
        this.soTCDK = soTCDK;
    }

    public String getTenHPDK() {
        return tenHPDK;
    }

    public void setTenHPDK(String tenHPDK) {
        this.tenHPDK = tenHPDK;
    }

    public String getTTDK() {
        return TTDK;
    }

    public void setTTDK(String TTDK) {
        this.TTDK = TTDK;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
