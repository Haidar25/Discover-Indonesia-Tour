package com.example.android.DITO.model;

public class ItemModel {
    int imgStud, imgProf;
    String judul, penulis, contributor, tanggal;

    public ItemModel(int imgStud, int imgProf, String judul, String penulis, String contributor, String tanggal) {
        this.imgStud = imgStud;
        this.imgProf = imgProf;
        this.judul = judul;
        this.penulis = penulis;
        this.contributor = contributor;
        this.tanggal = tanggal;
    }

    public int getImgStud() {
        return imgStud;
    }

    public void setImgStud(int imgStud) {
        this.imgStud = imgStud;
    }

    public int getImgProf() {
        return imgProf;
    }

    public void setImgProf(int imgProf) {
        this.imgProf = imgProf;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getPenulis() {
        return penulis;
    }

    public void setPenulis(String penulis) {
        this.penulis = penulis;
    }

    public String getContributor() {
        return contributor;
    }

    public void setContributor(String contributor) {
        this.contributor = contributor;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }
}
