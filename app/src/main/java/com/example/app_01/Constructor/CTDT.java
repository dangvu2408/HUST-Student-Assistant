package com.example.app_01.Constructor;

public class CTDT {
    private String maHPCTDT, tenHPCTDT, kyhocCTDT, tinchiDT, maHPhoc, ghichuHPH, dienchuCTDT, diemsoCTDT, vienkhoaDT;
    public CTDT(String maHPCTDT, String tenHPCTDT, String kyhocCTDT, String tinchiDT, String maHPhoc, String ghichuHPH, String dienchuCTDT, String diemsoCTDT, String vienkhoaDT) {
        this.maHPCTDT = maHPCTDT;
        this.tenHPCTDT = tenHPCTDT;
        this.kyhocCTDT = kyhocCTDT;
        this.tinchiDT = tinchiDT;
        this.maHPhoc = maHPhoc;
        this.ghichuHPH = ghichuHPH;
        this.dienchuCTDT = dienchuCTDT;
        this.diemsoCTDT = diemsoCTDT;
        this.vienkhoaDT = vienkhoaDT;
    }

    public String getMaHPctdt() {
        return maHPCTDT;
    }

    public void setMaHPCTDT(String maHPCTDT) {
        this.maHPCTDT = maHPCTDT;
    }

    public String getGhichuHPH() {
        return ghichuHPH;
    }

    public void setGhichuHPH(String ghichuHPH) {
        this.ghichuHPH = ghichuHPH;
    }

    public String getDiemsoCTDT() {
        return diemsoCTDT;
    }

    public void setDiemsoCTDT(String diemsoCTDT) {
        this.diemsoCTDT = diemsoCTDT;
    }

    public String getDienchuCTDT() {
        return dienchuCTDT;
    }

    public void setDienchuCTDT(String dienchuCTDT) {
        this.dienchuCTDT = dienchuCTDT;
    }

    public String getKyhocCTDT() {
        return kyhocCTDT;
    }

    public void setKyhocCTDT(String kyhocCTDT) {
        this.kyhocCTDT = kyhocCTDT;
    }

    public String getMaHPhoc() {
        return maHPhoc;
    }

    public void setMaHPhoc(String maHPhoc) {
        this.maHPhoc = maHPhoc;
    }

    public String getTenHPCTDT() {
        return tenHPCTDT;
    }

    public void setTenHPCTDT(String tenHPCTDT) {
        this.tenHPCTDT = tenHPCTDT;
    }

    public String getTinchiDT() {
        return tinchiDT;
    }

    public void setTinchiDT(String tinchiDT) {
        this.tinchiDT = tinchiDT;
    }

    public String getVienkhoaDT() {
        return vienkhoaDT;
    }

    public void setVienkhoaDT(String vienkhoaDT) {
        this.vienkhoaDT = vienkhoaDT;
    }
}
