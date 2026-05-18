package com.kasirapp.model;

public class KeranjangItem {
    private MenuItem menuItem;
    private int jumlah;

    public KeranjangItem() {
    }

    public KeranjangItem(MenuItem menuItem, int jumlah) {
        this.menuItem = menuItem;
        this.jumlah = jumlah;
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(MenuItem menuItem) {
        this.menuItem = menuItem;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public double getSubtotal() {
        return menuItem.getHarga() * jumlah;
    }

    public int getIdMenu() {
        return menuItem.getIdMenu();
    }

    public String getNamaMenu() {
        return menuItem.getNamaMenu();
    }

    public double getHarga() {
        return menuItem.getHarga();
    }
}
