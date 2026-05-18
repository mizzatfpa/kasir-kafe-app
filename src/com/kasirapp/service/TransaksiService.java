package com.kasirapp.service;

import com.kasirapp.dao.DatabaseConnection;
import com.kasirapp.dao.DetailTransaksiDAO;
import com.kasirapp.dao.MenuDAO;
import com.kasirapp.dao.TransaksiDAO;
import com.kasirapp.model.DetailTransaksi;
import com.kasirapp.model.KeranjangItem;
import com.kasirapp.model.MetodePembayaran;
import com.kasirapp.model.Transaksi;
import com.kasirapp.util.KodeTransaksiUtil;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class TransaksiService {
    private TransaksiDAO transaksiDAO;
    private DetailTransaksiDAO detailTransaksiDAO;
    private MenuDAO menuDAO;

    public TransaksiService() {
        this.transaksiDAO = new TransaksiDAO();
        this.detailTransaksiDAO = new DetailTransaksiDAO();
        this.menuDAO = new MenuDAO();
    }

    public Transaksi simpanTransaksi(List<KeranjangItem> keranjang, double total, double bayar,
            double kembalian, MetodePembayaran metodePembayaran) {
        if (keranjang == null || keranjang.isEmpty()) {
            throw new IllegalArgumentException("Keranjang masih kosong.");
        }

        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false);
            try {
                Transaksi transaksi = new Transaksi();
                transaksi.setKodeTransaksi(KodeTransaksiUtil.generateKodeTransaksi());
                transaksi.setTanggalTransaksi(LocalDateTime.now());
                transaksi.setTotal(total);
                transaksi.setBayar(bayar);
                transaksi.setKembalian(kembalian);
                transaksi.setMetodePembayaran(metodePembayaran);

                int idTransaksi = transaksiDAO.insert(conn, transaksi);
                transaksi.setIdTransaksi(idTransaksi);

                for (KeranjangItem item : keranjang) {
                    DetailTransaksi detail = new DetailTransaksi();
                    detail.setIdTransaksi(idTransaksi);
                    detail.setIdMenu(item.getIdMenu());
                    detail.setNamaMenu(item.getNamaMenu());
                    detail.setHarga(item.getHarga());
                    detail.setJumlah(item.getJumlah());
                    detail.setSubtotal(item.getSubtotal());

                    if (!detailTransaksiDAO.insert(conn, detail)) {
                        throw new SQLException("Detail transaksi gagal disimpan.");
                    }
                    if (!menuDAO.kurangiStok(conn, item.getIdMenu(), item.getJumlah())) {
                        throw new SQLException("Stok menu tidak cukup atau menu tidak ditemukan.");
                    }
                    transaksi.tambahDetail(detail);
                }

                conn.commit();
                return transaksi;
            } catch (SQLException | RuntimeException e) {
                conn.rollback();
                throw new RuntimeException("Gagal menyimpan transaksi: " + e.getMessage(), e);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Gagal membuka koneksi transaksi: " + e.getMessage(), e);
        }
    }

    public List<Transaksi> getAllTransaksi() {
        return transaksiDAO.findAll();
    }

    public List<Transaksi> cariByKode(String kodeTransaksi) {
        return transaksiDAO.findByKode(kodeTransaksi == null ? "" : kodeTransaksi.trim());
    }

    public List<Transaksi> cariByTanggal(LocalDate tanggal) {
        if (tanggal == null) {
            throw new IllegalArgumentException("Tanggal harus diisi.");
        }
        return transaksiDAO.findByTanggal(tanggal);
    }

    public List<DetailTransaksi> getDetailTransaksi(int idTransaksi) {
        if (idTransaksi <= 0) {
            throw new IllegalArgumentException("ID transaksi tidak valid.");
        }
        return detailTransaksiDAO.findByTransaksiId(idTransaksi);
    }
}
