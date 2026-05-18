package com.kasirapp.dao;

import com.kasirapp.model.Makanan;
import com.kasirapp.model.MenuItem;
import com.kasirapp.model.Minuman;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MenuDAO {
    public List<MenuItem> findAll() {
        String sql = "SELECT * FROM menu ORDER BY id_menu";
        List<MenuItem> daftarMenu = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                daftarMenu.add(mapResultSet(rs));
            }
            return daftarMenu;
        } catch (SQLException e) {
            throw new RuntimeException("Gagal mengambil data menu: " + e.getMessage(), e);
        }
    }

    public MenuItem findById(int idMenu) {
        String sql = "SELECT * FROM menu WHERE id_menu = ?";

        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idMenu);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapResultSet(rs);
                }
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Gagal mencari menu: " + e.getMessage(), e);
        }
    }

    public boolean insert(MenuItem menu) {
        String sql = "INSERT INTO menu (nama_menu, jenis_menu, harga, stok, tingkat_pedas, ukuran) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            fillStatement(ps, menu);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Gagal menambah menu: " + e.getMessage(), e);
        }
    }

    public boolean update(MenuItem menu) {
        String sql = "UPDATE menu SET nama_menu = ?, jenis_menu = ?, harga = ?, stok = ?, tingkat_pedas = ?, ukuran = ? WHERE id_menu = ?";

        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            fillStatement(ps, menu);
            ps.setInt(7, menu.getIdMenu());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Gagal mengubah menu: " + e.getMessage(), e);
        }
    }

    public boolean delete(int idMenu) {
        String sql = "DELETE FROM menu WHERE id_menu = ?";

        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idMenu);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Gagal menghapus menu: " + e.getMessage(), e);
        }
    }

    public boolean kurangiStok(Connection conn, int idMenu, int jumlah) throws SQLException {
        String sql = "UPDATE menu SET stok = stok - ? WHERE id_menu = ? AND stok >= ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, jumlah);
            ps.setInt(2, idMenu);
            ps.setInt(3, jumlah);
            return ps.executeUpdate() > 0;
        }
    }

    private MenuItem mapResultSet(ResultSet rs) throws SQLException {
        String jenisMenu = rs.getString("jenis_menu");
        int idMenu = rs.getInt("id_menu");
        String namaMenu = rs.getString("nama_menu");
        double harga = rs.getDouble("harga");
        int stok = rs.getInt("stok");

        if ("MAKANAN".equalsIgnoreCase(jenisMenu)) {
            return new Makanan(idMenu, namaMenu, harga, stok, rs.getString("tingkat_pedas"));
        }
        if ("MINUMAN".equalsIgnoreCase(jenisMenu)) {
            return new Minuman(idMenu, namaMenu, harga, stok, rs.getString("ukuran"));
        }
        throw new SQLException("Jenis menu tidak dikenal: " + jenisMenu);
    }

    private void fillStatement(PreparedStatement ps, MenuItem menu) throws SQLException {
        ps.setString(1, menu.getNamaMenu());
        ps.setString(2, menu.getJenisMenu());
        ps.setDouble(3, menu.getHarga());
        ps.setInt(4, menu.getStok());

        if (menu instanceof Makanan) {
            Makanan makanan = (Makanan) menu;
            ps.setString(5, makanan.getTingkatPedas());
            ps.setString(6, null);
        } else if (menu instanceof Minuman) {
            Minuman minuman = (Minuman) menu;
            ps.setString(5, null);
            ps.setString(6, minuman.getUkuran());
        } else {
            throw new SQLException("Tipe object menu tidak dikenal.");
        }
    }
}
