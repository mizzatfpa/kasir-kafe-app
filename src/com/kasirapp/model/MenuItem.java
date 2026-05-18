package com.kasirapp.model;

public abstract class MenuItem {
    protected int idMenu;
    protected String namaMenu;
    protected double harga;
    protected int stok;

    public MenuItem() {
    }

    public MenuItem(int idMenu, String namaMenu, double harga, int stok) {
        this.idMenu = idMenu;
        this.namaMenu = namaMenu;
        this.harga = harga;
        this.stok = stok;
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

    public int getStok() {
        return stok;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }

    public abstract String getJenisMenu();

    public abstract String getInfoTambahan();

    public double hitungSubtotal(int jumlah) {
        return harga * jumlah;
    }

    public boolean isStokCukup(int jumlah) {
        return stok >= jumlah;
    }
}
