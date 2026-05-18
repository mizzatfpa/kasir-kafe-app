package com.kasirapp.controller;

import com.kasirapp.model.DetailTransaksi;
import com.kasirapp.model.Transaksi;
import com.kasirapp.service.TransaksiService;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TransaksiController {
    private TransaksiService transaksiService;
    private String lastError;

    public TransaksiController() {
        this.transaksiService = new TransaksiService();
    }

    public List<Transaksi> getAllTransaksi() {
        try {
            clearError();
            return transaksiService.getAllTransaksi();
        } catch (Exception e) {
            setError(e);
            return new ArrayList<>();
        }
    }

    public List<Transaksi> cariByKode(String kodeTransaksi) {
        try {
            clearError();
            return transaksiService.cariByKode(kodeTransaksi);
        } catch (Exception e) {
            setError(e);
            return new ArrayList<>();
        }
    }

    public List<Transaksi> cariByTanggal(LocalDate tanggal) {
        try {
            clearError();
            return transaksiService.cariByTanggal(tanggal);
        } catch (Exception e) {
            setError(e);
            return new ArrayList<>();
        }
    }

    public List<DetailTransaksi> getDetailTransaksi(int idTransaksi) {
        try {
            clearError();
            return transaksiService.getDetailTransaksi(idTransaksi);
        } catch (Exception e) {
            setError(e);
            return new ArrayList<>();
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
