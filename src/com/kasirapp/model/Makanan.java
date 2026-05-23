package com.kasirapp.model;

public class Makanan extends MenuItem {
    public Makanan() {
    }

    public Makanan(int idMenu, String namaMenu, double harga, int stok) {
        super(idMenu, namaMenu, harga, stok);
    }

    @Override
    public String getJenisMenu() {
        return "MAKANAN";
    }

    @Override
    public String getSatuan() {
        return "porsi";
    }
}
