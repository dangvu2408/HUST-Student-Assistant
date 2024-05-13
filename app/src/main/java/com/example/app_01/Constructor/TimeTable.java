package com.example.app_01.Constructor;

public class TimeTable {
    private String thoigian, tuanhoc, phonghoc, malop, loailop, nhom, mahocphan, tenlop, ghichu, hinhthuc, giangvien, link, codeteams;
    public TimeTable() {}
    public TimeTable(String str01, String str02, String str03, String str04, String str05, String str06, String str07, String str08, String str09, String str10, String str11, String str12, String str13) {
        this.thoigian = str01;
        this.tuanhoc = str02;
        this.phonghoc = str03;
        this.malop = str04;
        this.loailop = str05;
        this.nhom = str06;
        this.mahocphan = str07;
        this.tenlop = str08;
        this.ghichu = str09;
        this.hinhthuc = str10;
        this.giangvien = str11;
        this.link = str12;
        this.codeteams = str13;
    }

    public String getThoigian() {
        return thoigian;
    }

    public void setThoigian(String thoigian) {
        this.thoigian = thoigian;
    }

    public String getTuanhoc() {
        return tuanhoc;
    }

    public void setTuanhoc(String tuanhoc) {
        this.tuanhoc = tuanhoc;
    }

    public String getPhonghoc() {
        return phonghoc;
    }

    public void setPhonghoc(String phonghoc) {
        this.phonghoc = phonghoc;
    }

    public String getMalop() {
        return malop;
    }

    public void setMalop(String malop) {
        this.malop = malop;
    }

    public String getLoailop() {
        return loailop;
    }

    public void setLoailop(String loailop) {
        this.loailop = loailop;
    }

    public String getNhom() {
        return nhom;
    }

    public void setNhom(String nhom) {
        this.nhom = nhom;
    }

    public String getMahocphan() {
        return mahocphan;
    }

    public void setMahocphan(String mahocphan) {
        this.mahocphan = mahocphan;
    }

    public String getTenlop() {
        return tenlop;
    }

    public void setTenlop(String tenlop) {
        this.tenlop = tenlop;
    }

    public String getGhichu() {
        return ghichu;
    }

    public void setGhichu(String ghichu) {
        this.ghichu = ghichu;
    }

    public String getHinhthuc() {
        return hinhthuc;
    }

    public void setHinhthuc(String hinhthuc) {
        this.hinhthuc = hinhthuc;
    }

    public String getGiangvien() {
        return giangvien;
    }

    public void setGiangvien(String giangvien) {
        this.giangvien = giangvien;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getCodeteams() {
        return codeteams;
    }

    public void setCodeteams(String codeteams) {
        this.codeteams = codeteams;
    }
}
