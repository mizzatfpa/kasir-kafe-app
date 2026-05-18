package com.kasirapp.dao;

import com.kasirapp.model.DetailTransaksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DetailTransaksiDAO {
    public boolean insert(Connection conn, DetailTransaksi detail) throws SQLException {
        String sql = "INSERT INTO detail_transaksi (id_transaksi, id_menu, nama_menu, harga, jumlah, subtotal) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, detail.getIdTransaksi());
            ps.setInt(2, detail.getIdMenu());
            ps.setString(3, detail.getNamaMenu());
            ps.setDouble(4, detail.getHarga());
            ps.setInt(5, detail.getJumlah());
            ps.setDouble(6, detail.getSubtotal());
            return ps.executeUpdate() > 0;
        }
    }

    public List<DetailTransaksi> findByTransaksiId(int idTransaksi) {
        String sql = "SELECT * FROM detail_transaksi WHERE id_transaksi = ? ORDER BY id_detail";
        List<DetailTransaksi> daftarDetail = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idTransaksi);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    daftarDetail.add(mapResultSet(rs));
                }
            }
            return daftarDetail;
        } catch (SQLException e) {
            throw new RuntimeException("Gagal mengambil detail transaksi: " + e.getMessage(), e);
        }
    }

    private DetailTransaksi mapResultSet(ResultSet rs) throws SQLException {
        return new DetailTransaksi(
                rs.getInt("id_detail"),
                rs.getInt("id_transaksi"),
                rs.getInt("id_menu"),
                rs.getString("nama_menu"),
                rs.getDouble("harga"),
                rs.getInt("jumlah"),
                rs.getDouble("subtotal"));
    }
}
