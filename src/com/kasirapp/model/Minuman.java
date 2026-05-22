package com.kasirapp.model;

public class Minuman extends MenuItem {
    private String ukuran;

    public Minuman() {
    }

    public Minuman(int idMenu, String namaMenu, double harga, int stok, String ukuran) {
        super(idMenu, namaMenu, harga, stok);
        this.ukuran = ukuran;
    }

    public String getUkuran() {
        return ukuran;
    }

    public void setUkuran(String ukuran) {
        this.ukuran = ukuran;
    }

    @Override
    public String getJenisMenu() {
        return "MINUMAN";
    }

    @Override
    public String getInfoTambahan() {
        return ukuran;
    }
}
