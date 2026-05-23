package com.kasirapp.model;

public class Minuman extends MenuItem {
    public Minuman() {
    }

    public Minuman(int idMenu, String namaMenu, double harga, int stok) {
        super(idMenu, namaMenu, harga, stok);
    }

    @Override
    public String getJenisMenu() {
        return "MINUMAN";
    }

    @Override
    public String getSatuan() {
        return "gelas";
    }
}
