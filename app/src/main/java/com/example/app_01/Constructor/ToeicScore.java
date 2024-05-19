package com.example.app_01.Constructor;

public class ToeicScore {
    private String maso, hotensv, dob, hocki, note, ngaythi, nghe, doc, tong;
    public ToeicScore(String maso, String hotensv, String dob, String hocki, String note, String ngaythi, String nghe, String doc, String tong) {
        this.maso = maso;
        this.hotensv = hotensv;
        this.dob = dob;
        this.hocki = hocki;
        this.note = note;
        this.ngaythi = ngaythi;
        this.nghe = nghe;
        this.doc = doc;
        this.tong = tong;
    }

    public String getMaso() {
        return maso;
    }

    public void setMaso(String maso) {
        this.maso = maso;
    }

    public String getHotensv() {
        return hotensv;
    }

    public void setHotensv(String hotensv) {
        this.hotensv = hotensv;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getHocki() {
        return hocki;
    }

    public void setHocki(String hocki) {
        this.hocki = hocki;
    }

    public String getNgaythi() {
        return ngaythi;
    }

    public void setNgaythi(String ngaythi) {
        this.ngaythi = ngaythi;
    }

    public String getNghe() {
        return nghe;
    }

    public void setNghe(String nghe) {
        this.nghe = nghe;
    }

    public String getDoc() {
        return doc;
    }

    public void setDoc(String doc) {
        this.doc = doc;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getTong() {
        return tong;
    }

    public void setTong(String tong) {
        this.tong = tong;
    }
}
