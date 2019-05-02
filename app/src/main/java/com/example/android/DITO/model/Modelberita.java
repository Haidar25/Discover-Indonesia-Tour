package com.example.android.DITO.model;

public class Modelberita {

    public String uid;
    public String judul;
    public String gambar;
    public String tipe;
    public String berita;
    public String author;
    public String mogimogi;
    public Long mogumogu;


    public Modelberita() {
    }

    public Modelberita(String uid, String judul, String gambar, String tipe, String berita, String author, String mogimogi, Long mogumogu) {
        this.uid = uid;
        this.judul = judul;
        this.gambar = gambar;
        this.tipe = tipe;
        this.berita = berita;
        this.author = author;
        this.mogimogi = mogimogi;
        this.mogumogu = mogumogu;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }

    public String getBerita() {
        return berita;
    }

    public void setBerita(String berita) {
        this.berita = berita;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getMogimogi() {
        return mogimogi;
    }

    public void setMogimogi(String mogimogi) {
        this.mogimogi = mogimogi;
    }

    public Long getMogumogu() {
        return mogumogu;
    }

    public void setMogumogu(Long mogumogu) {
        this.mogumogu = mogumogu;
    }
}
