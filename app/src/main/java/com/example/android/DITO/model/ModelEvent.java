package com.example.android.DITO.model;

public class ModelEvent {
    public String uid;
    public String gambar;
    public String tipe;
    public String mogimogi;
    public Long mogumogu;


    public ModelEvent() {
    }

    public ModelEvent(String uid, String gambar, String tipe, String mogimogi, Long mogumogu) {
        this.uid = uid;
        this.gambar = gambar;
        this.tipe = tipe;
        this.mogimogi = mogimogi;
        this.mogumogu = mogumogu;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
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

