package com.kasirapp.model;

public class Makanan extends MenuItem {
    private String tingkatPedas;

    public Makanan() {
    }

    public Makanan(int idMenu, String namaMenu, double harga, int stok, String tingkatPedas) {
        super(idMenu, namaMenu, harga, stok);
        this.tingkatPedas = tingkatPedas;
    }

    public String getTingkatPedas() {
        return tingkatPedas;
    }

    public void setTingkatPedas(String tingkatPedas) {
        this.tingkatPedas = tingkatPedas;
    }

    @Override
    public String getJenisMenu() {
        return "MAKANAN";
    }

    @Override
    public String getInfoTambahan() {
        return tingkatPedas;
    }
}
