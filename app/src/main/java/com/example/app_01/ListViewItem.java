package com.example.app_01;

public class ListViewItem {

    private String HoTen, MSSV;
    private int numberlist;


    public ListViewItem() {
        //empty constructor
    }
    public ListViewItem(int numberlist, String HoTen, String MSSV) {
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
