CREATE DATABASE IF NOT EXISTS kasir_kafe;
USE kasir_kafe;

CREATE TABLE IF NOT EXISTS menu (
    id_menu INT AUTO_INCREMENT PRIMARY KEY,
    nama_menu VARCHAR(100) NOT NULL,
    jenis_menu VARCHAR(20) NOT NULL,
    harga DOUBLE NOT NULL,
    stok INT NOT NULL,
    UNIQUE KEY uk_menu_nama (nama_menu)
);

CREATE TABLE IF NOT EXISTS transaksi (
    id_transaksi INT AUTO_INCREMENT PRIMARY KEY,
    kode_transaksi VARCHAR(50) NOT NULL,
    tanggal_transaksi DATETIME NOT NULL,
    total DOUBLE NOT NULL,
    bayar DOUBLE NOT NULL,
    kembalian DOUBLE NOT NULL,
    metode_pembayaran VARCHAR(20) NOT NULL
);

CREATE TABLE IF NOT EXISTS detail_transaksi (
    id_detail INT AUTO_INCREMENT PRIMARY KEY,
    id_transaksi INT NOT NULL,
    id_menu INT NULL,
    nama_menu VARCHAR(100) NOT NULL,
    harga DOUBLE NOT NULL,
    jumlah INT NOT NULL,
    subtotal DOUBLE NOT NULL,
    FOREIGN KEY (id_transaksi) REFERENCES transaksi(id_transaksi),
    FOREIGN KEY (id_menu) REFERENCES menu(id_menu) ON DELETE SET NULL
);
