package com.kasirapp.dao;

import com.kasirapp.model.MetodePembayaran;
import com.kasirapp.model.Transaksi;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TransaksiDAO {
    public int insert(Connection conn, Transaksi transaksi) throws SQLException {
        String sql = "INSERT INTO transaksi (kode_transaksi, tanggal_transaksi, total, bayar, kembalian, metode_pembayaran) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, transaksi.getKodeTransaksi());
            ps.setTimestamp(2, Timestamp.valueOf(transaksi.getTanggalTransaksi()));
            ps.setDouble(3, transaksi.getTotal());
            ps.setDouble(4, transaksi.getBayar());
            ps.setDouble(5, transaksi.getKembalian());
            ps.setString(6, transaksi.getMetodePembayaran().name());
            ps.executeUpdate();

            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    return keys.getInt(1);
                }
            }
            throw new SQLException("ID transaksi tidak dibuat.");
        }
    }

    public List<Transaksi> findAll() {
        String sql = "SELECT * FROM transaksi ORDER BY tanggal_transaksi DESC";
        List<Transaksi> daftarTransaksi = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                daftarTransaksi.add(mapResultSet(rs));
            }
            return daftarTransaksi;
        } catch (SQLException e) {
            throw new RuntimeException("Gagal mengambil transaksi: " + e.getMessage(), e);
        }
    }

    public List<Transaksi> findByKode(String kodeTransaksi) {
        String sql = "SELECT * FROM transaksi WHERE kode_transaksi LIKE ? ORDER BY tanggal_transaksi DESC";
        List<Transaksi> daftarTransaksi = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + kodeTransaksi + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    daftarTransaksi.add(mapResultSet(rs));
                }
            }
            return daftarTransaksi;
        } catch (SQLException e) {
            throw new RuntimeException("Gagal mencari transaksi: " + e.getMessage(), e);
        }
    }

    public List<Transaksi> findByTanggal(LocalDate tanggal) {
        String sql = "SELECT * FROM transaksi WHERE DATE(tanggal_transaksi) = ? ORDER BY tanggal_transaksi DESC";
        List<Transaksi> daftarTransaksi = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDate(1, Date.valueOf(tanggal));
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    daftarTransaksi.add(mapResultSet(rs));
                }
            }
            return daftarTransaksi;
        } catch (SQLException e) {
            throw new RuntimeException("Gagal mencari transaksi berdasarkan tanggal: " + e.getMessage(), e);
        }
    }

    public Transaksi findById(int idTransaksi) {
        String sql = "SELECT * FROM transaksi WHERE id_transaksi = ?";

        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idTransaksi);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapResultSet(rs);
                }
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Gagal mencari transaksi: " + e.getMessage(), e);
        }
    }

    private Transaksi mapResultSet(ResultSet rs) throws SQLException {
        return new Transaksi(
                rs.getInt("id_transaksi"),
                rs.getString("kode_transaksi"),
                rs.getTimestamp("tanggal_transaksi").toLocalDateTime(),
                rs.getDouble("total"),
                rs.getDouble("bayar"),
                rs.getDouble("kembalian"),
                MetodePembayaran.valueOf(rs.getString("metode_pembayaran")));
    }
}
