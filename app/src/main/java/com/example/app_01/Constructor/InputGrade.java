package com.example.app_01.Constructor;

public class InputGrade {
    private String masinhvien, malop, tenlop, trongsoqt, diemqt, ttdiemqt, diemthi, ttdiemthi;

    public InputGrade() {
    }

    public InputGrade(String masinhvien, String malop, String tenlop, String trongsoqt, String diemqt, String ttdiemqt, String diemthi, String ttdiemthi) {
        this.masinhvien = masinhvien;
        this.malop = malop;
        this.tenlop = tenlop;
        this.trongsoqt = trongsoqt;
        this.diemqt = diemqt;
        this.ttdiemqt = ttdiemqt;
        this.diemthi = diemthi;
        this.ttdiemthi = ttdiemthi;
    }

    public String getDiemthi() {
        return diemthi;
    }

    public void setDiemthi(String diemthi) {
        this.diemthi = diemthi;
    }

    public String getTenlop() {
        return tenlop;
    }

    public void setTenlop(String tenlop) {
        this.tenlop = tenlop;
    }

    public String getMalop() {
        return malop;
    }

    public void setMalop(String malop) {
        this.malop = malop;
    }

    public String getDiemqt() {
        return diemqt;
    }

    public void setDiemqt(String diemqt) {
        this.diemqt = diemqt;
    }

    public String getMasinhvien() {
        return masinhvien;
    }

    public void setMasinhvien(String masinhvien) {
        this.masinhvien = masinhvien;
    }

    public String getTrongsoqt() {
        return trongsoqt;
    }

    public void setTrongsoqt(String trongsoqt) {
        this.trongsoqt = trongsoqt;
    }

    public String getTtdiemqt() {
        return ttdiemqt;
    }

    public void setTtdiemqt(String ttdiemqt) {
        this.ttdiemqt = ttdiemqt;
    }

    public String getTtdiemthi() {
        return ttdiemthi;
    }

    public void setTtdiemthi(String ttdiemthi) {
        this.ttdiemthi = ttdiemthi;
    }
}


