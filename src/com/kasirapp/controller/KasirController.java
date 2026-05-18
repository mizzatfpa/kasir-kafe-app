package com.kasirapp.controller;

import com.kasirapp.model.KeranjangItem;
import com.kasirapp.model.MenuItem;
import com.kasirapp.model.MetodePembayaran;
import com.kasirapp.model.Transaksi;
import com.kasirapp.service.KasirService;
import java.util.ArrayList;
import java.util.List;

public class KasirController {
    private KasirService kasirService;
    private String lastError;

    public KasirController() {
        this.kasirService = new KasirService();
    }

    public List<MenuItem> getDaftarMenu() {
        try {
            clearError();
            return kasirService.getDaftarMenu();
        } catch (Exception e) {
            setError(e);
            return new ArrayList<>();
        }
    }

    public boolean tambahKeKeranjang(int idMenu, int jumlah) {
        try {
            clearError();
            kasirService.tambahKeKeranjang(idMenu, jumlah);
            return true;
        } catch (Exception e) {
            setError(e);
            return false;
        }
    }

    public boolean hapusItemKeranjang(int idMenu) {
        try {
            clearError();
            kasirService.hapusItemKeranjang(idMenu);
            return true;
        } catch (Exception e) {
            setError(e);
            return false;
        }
    }

    public void kosongkanKeranjang() {
        clearError();
        kasirService.kosongkanKeranjang();
    }

    public List<KeranjangItem> getKeranjang() {
        return kasirService.getKeranjang();
    }

    public double hitungTotal() {
        return kasirService.hitungTotal();
    }

    public Transaksi prosesBayar(double bayar, MetodePembayaran metodePembayaran) {
        try {
            clearError();
            return kasirService.prosesBayar(bayar, metodePembayaran);
        } catch (Exception e) {
            setError(e);
            return null;
        }
    }

    public String getLastError() {
        return lastError;
    }

    private void clearError() {
        lastError = null;
    }

    private void setError(Exception e) {
        lastError = e.getMessage();
    }
}
