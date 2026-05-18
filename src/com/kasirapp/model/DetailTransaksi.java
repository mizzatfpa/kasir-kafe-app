package com.kasirapp.model;

public class DetailTransaksi {
    private int idDetail;
    private int idTransaksi;
    private int idMenu;
    private String namaMenu;
    private double harga;
    private int jumlah;
    private double subtotal;

    public DetailTransaksi() {
    }

    public DetailTransaksi(int idDetail, int idTransaksi, int idMenu, String namaMenu,
            double harga, int jumlah, double subtotal) {
        this.idDetail = idDetail;
        this.idTransaksi = idTransaksi;
        this.idMenu = idMenu;
        this.namaMenu = namaMenu;
        this.harga = harga;
        this.jumlah = jumlah;
        this.subtotal = subtotal;
    }

    public int getIdDetail() {
        return idDetail;
    }

    public void setIdDetail(int idDetail) {
        this.idDetail = idDetail;
    }

    public int getIdTransaksi() {
        return idTransaksi;
    }

    public void setIdTransaksi(int idTransaksi) {
        this.idTransaksi = idTransaksi;
    }

    public int getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(int idMenu) {
        this.idMenu = idMenu;
    }

    public String getNamaMenu() {
        return namaMenu;
    }

    public void setNamaMenu(String namaMenu) {
        this.namaMenu = namaMenu;
    }

    public double getHarga() {
        return harga;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }
}
