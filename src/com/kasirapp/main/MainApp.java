package com.kasirapp.main;

import com.kasirapp.dao.DatabaseConnection;

public class MainApp {
    public static void main(String[] args) {
        System.out.println("Aplikasi KasirApp sederhana");
        System.out.println("Mengecek koneksi database...");

        String error = DatabaseConnection.testConnectionMessage();
        if (error == null) {
            System.out.println("Koneksi database berhasil.");
            System.out.println("Backend siap digunakan oleh GUI NetBeans.");
        } else {
            System.out.println("Koneksi database gagal: " + error);
        }
    }
}
