package com.kasirapp.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Transaksi {
    private int idTransaksi;
    private String kodeTransaksi;
    private LocalDateTime tanggalTransaksi;
    private double total;
    private double bayar;
    private double kembalian;
    private MetodePembayaran metodePembayaran;
    private List<DetailTransaksi> daftarDetail = new ArrayList<>();

    public Transaksi() {
    }

    public Transaksi(int idTransaksi, String kodeTransaksi, LocalDateTime tanggalTransaksi,
            double total, double bayar, double kembalian, MetodePembayaran metodePembayaran) {
        this.idTransaksi = idTransaksi;
        this.kodeTransaksi = kodeTransaksi;
        this.tanggalTransaksi = tanggalTransaksi;
        this.total = total;
        this.bayar = bayar;
        this.kembalian = kembalian;
        this.metodePembayaran = metodePembayaran;
    }

    public int getIdTransaksi() {
        return idTransaksi;
    }

    public void setIdTransaksi(int idTransaksi) {
        this.idTransaksi = idTransaksi;
    }

    public String getKodeTransaksi() {
        return kodeTransaksi;
    }

    public void setKodeTransaksi(String kodeTransaksi) {
        this.kodeTransaksi = kodeTransaksi;
    }

    public LocalDateTime getTanggalTransaksi() {
        return tanggalTransaksi;
    }

    public void setTanggalTransaksi(LocalDateTime tanggalTransaksi) {
        this.tanggalTransaksi = tanggalTransaksi;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getBayar() {
        return bayar;
    }

    public void setBayar(double bayar) {
        this.bayar = bayar;
    }

    public double getKembalian() {
        return kembalian;
    }

    public void setKembalian(double kembalian) {
        this.kembalian = kembalian;
    }

    public MetodePembayaran getMetodePembayaran() {
        return metodePembayaran;
    }

    public void setMetodePembayaran(MetodePembayaran metodePembayaran) {
        this.metodePembayaran = metodePembayaran;
    }

    public void tambahDetail(DetailTransaksi detail) {
        daftarDetail.add(detail);
    }

    public List<DetailTransaksi> getDaftarDetail() {
        return daftarDetail;
    }

    public void setDaftarDetail(List<DetailTransaksi> daftarDetail) {
        this.daftarDetail = daftarDetail;
    }
}
