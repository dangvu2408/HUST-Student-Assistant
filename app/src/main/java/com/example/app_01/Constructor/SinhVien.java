package com.example.app_01.Constructor;

public class SinhVien {

    private String HoTen, MSSV;
    private int numberlist;


    public SinhVien() {
        //empty constructor
    }
    public SinhVien(String HoTen, String MSSV, int numberlist) {
        this.HoTen = HoTen;
        this.numberlist = numberlist;
        this.MSSV = MSSV;
    }

    public int getNumberlist() {
        return numberlist;
    }
    public void setNumberlist(int numberlist) {
        this.numberlist = numberlist;
    }
    public String getHoTen() {
        return HoTen;
    }
    public void setHoTen(String HoTen) {
        this.HoTen = HoTen;
    }
    public String getMSSV() {
        return MSSV;
    }
    public void setMSSV(String MSSV) {
        this.MSSV = MSSV;
    }
}
