package com.example.kawalingkungan;

import android.webkit.WebView;

public class ModelInfo {
    public String tanggal;
    public String jam;
    public String magnitude;
    public String wilayah, bujur, lintang, kedalaman;
    public WebView _symbol;

    @Override
    public String toString() {
        return "ModelInfo{" +
                "tanggal='" + tanggal + '\'' +
                ", jam='" + jam + '\'' +
                ", magnitude='" + magnitude + '\'' +
                ", lintang='" + lintang + '\'' +
                ", bujur='" + bujur + '\'' +
                ", kedalaman='" + kedalaman + '\'' +
                ", wilayah='" + wilayah + '\'' +
                ", _symbol='" + _symbol + '\'' +
                '}';
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }
    public String getTanggal() {
        return tanggal;
    }

    public void setJam(String jam) {
        this.jam = jam;
    }

    public String getJam() {
        return jam;
    }

    public void setMagnitude(String magnitude) {
        this.magnitude = magnitude;
    }

    public String getMagnitude() {
        return magnitude;
    }

    public void setWilayah(String wilayah) {
        this.wilayah = wilayah;
    }

    public String getWilayah() {
        return wilayah;
    }

    public void set_symbol(WebView _symbol) {
        this._symbol = _symbol;
    }

    public WebView get_symbol() {
        return _symbol;
    }

    public void setLintang(String lintang) {
        this.lintang = lintang;
    }

    public String getLintang() {
        return lintang;
    }

    public void setBujur(String bujur) {
        this.bujur = bujur;
    }

    public String getBujur() {
        return bujur;
    }

    public void setKedalaman(String kedalaman) {
        this.kedalaman = kedalaman;
    }

    public String getKedalaman() {
        return kedalaman;
    }
}
