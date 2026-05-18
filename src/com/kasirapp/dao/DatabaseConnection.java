package com.kasirapp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {
    public static final String URL = "jdbc:mysql://localhost:3306/kasir_kafe";
    public static final String SERVER_URL = "jdbc:mysql://localhost:3306";
    public static final String USER = "root";
    public static final String PASSWORD = "";

    private DatabaseConnection() {
    }

    public static Connection getConnection() throws SQLException {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            if (e.getMessage() != null && e.getMessage().contains("Unknown database")) {
                initializeDatabase();
                return DriverManager.getConnection(URL, USER, PASSWORD);
            }
            throw e;
        }
    }

    public static void initializeDatabase() throws SQLException {
        try (Connection connection = DriverManager.getConnection(SERVER_URL, USER, PASSWORD);
                Statement statement = connection.createStatement()) {
            statement.executeUpdate("CREATE DATABASE IF NOT EXISTS kasir_kafe");
            statement.executeUpdate("USE kasir_kafe");
            statement.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS menu ("
                    + "id_menu INT AUTO_INCREMENT PRIMARY KEY,"
                    + "nama_menu VARCHAR(100) NOT NULL,"
                    + "jenis_menu VARCHAR(20) NOT NULL,"
                    + "harga DOUBLE NOT NULL,"
                    + "stok INT NOT NULL,"
                    + "tingkat_pedas VARCHAR(50),"
                    + "ukuran VARCHAR(50)"
                    + ")");
            statement.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS transaksi ("
                    + "id_transaksi INT AUTO_INCREMENT PRIMARY KEY,"
                    + "kode_transaksi VARCHAR(50) NOT NULL,"
                    + "tanggal_transaksi DATETIME NOT NULL,"
                    + "total DOUBLE NOT NULL,"
                    + "bayar DOUBLE NOT NULL,"
                    + "kembalian DOUBLE NOT NULL,"
                    + "metode_pembayaran VARCHAR(20) NOT NULL"
                    + ")");
            statement.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS detail_transaksi ("
                    + "id_detail INT AUTO_INCREMENT PRIMARY KEY,"
                    + "id_transaksi INT NOT NULL,"
                    + "id_menu INT NULL,"
                    + "nama_menu VARCHAR(100) NOT NULL,"
                    + "harga DOUBLE NOT NULL,"
                    + "jumlah INT NOT NULL,"
                    + "subtotal DOUBLE NOT NULL,"
                    + "FOREIGN KEY (id_transaksi) REFERENCES transaksi(id_transaksi),"
                    + "FOREIGN KEY (id_menu) REFERENCES menu(id_menu) ON DELETE SET NULL"
                    + ")");
        }
    }

    public static boolean testConnection() {
        try {
            initializeDatabase();
            try (Connection connection = getConnection()) {
                return connection != null && !connection.isClosed();
            }
        } catch (SQLException e) {
            return false;
        }
    }

    public static String testConnectionMessage() {
        try {
            initializeDatabase();
            try (Connection connection = getConnection()) {
                if (connection != null && !connection.isClosed()) {
                    return null;
                }
                return "Koneksi database tidak aktif.";
            }
        } catch (SQLException e) {
            return e.getMessage();
        }
    }
}
