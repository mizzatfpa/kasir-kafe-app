package com.kasirapp.service;

import com.kasirapp.dao.MenuDAO;
import com.kasirapp.model.Makanan;
import com.kasirapp.model.MenuItem;
import com.kasirapp.model.Minuman;
import com.kasirapp.util.ValidationUtil;
import java.util.List;

public class MenuService {
    private MenuDAO menuDAO;

    public MenuService() {
        this.menuDAO = new MenuDAO();
    }

    public List<MenuItem> getAllMenu() {
        return menuDAO.findAll();
    }

    public MenuItem getMenuById(int idMenu) {
        return menuDAO.findById(idMenu);
    }

    public void tambahMakanan(String namaMenu, double harga, int stok, String tingkatPedas) {
        validasiMenuDasar(namaMenu, harga, stok);
        ValidationUtil.validateNotEmpty(tingkatPedas, "Tingkat pedas");
        menuDAO.insert(new Makanan(0, namaMenu.trim(), harga, stok, tingkatPedas.trim()));
    }

    public void tambahMinuman(String namaMenu, double harga, int stok, String ukuran) {
        validasiMenuDasar(namaMenu, harga, stok);
        ValidationUtil.validateNotEmpty(ukuran, "Ukuran");
        menuDAO.insert(new Minuman(0, namaMenu.trim(), harga, stok, ukuran.trim()));
    }

    public void ubahMakanan(int idMenu, String namaMenu, double harga, int stok, String tingkatPedas) {
        validasiIdMenu(idMenu);
        validasiMenuDasar(namaMenu, harga, stok);
        ValidationUtil.validateNotEmpty(tingkatPedas, "Tingkat pedas");

        boolean berhasil = menuDAO.update(new Makanan(idMenu, namaMenu.trim(), harga, stok, tingkatPedas.trim()));
        if (!berhasil) {
            throw new IllegalArgumentException("Menu tidak ditemukan.");
        }
    }

    public void ubahMinuman(int idMenu, String namaMenu, double harga, int stok, String ukuran) {
        validasiIdMenu(idMenu);
        validasiMenuDasar(namaMenu, harga, stok);
        ValidationUtil.validateNotEmpty(ukuran, "Ukuran");

        boolean berhasil = menuDAO.update(new Minuman(idMenu, namaMenu.trim(), harga, stok, ukuran.trim()));
        if (!berhasil) {
            throw new IllegalArgumentException("Menu tidak ditemukan.");
        }
    }

    public void hapusMenu(int idMenu) {
        validasiIdMenu(idMenu);
        boolean berhasil = menuDAO.delete(idMenu);
        if (!berhasil) {
            throw new IllegalArgumentException("Menu tidak ditemukan.");
        }
    }

    private void validasiMenuDasar(String namaMenu, double harga, int stok) {
        ValidationUtil.validateNotEmpty(namaMenu, "Nama menu");
        ValidationUtil.validatePositive(harga, "Harga");
        ValidationUtil.validateNonNegative(stok, "Stok");
    }

    private void validasiIdMenu(int idMenu) {
        if (idMenu <= 0) {
            throw new IllegalArgumentException("ID menu tidak valid.");
        }
    }
}
