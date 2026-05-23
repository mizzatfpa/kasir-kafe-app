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

    public void tambahMakanan(String namaMenu, double harga, int stok) {
        validasiMenuDasar(namaMenu, harga, stok);
        validasiNamaMenuBelumDipakai(namaMenu, 0);
        menuDAO.insert(new Makanan(0, namaMenu.trim(), harga, stok));
    }

    public void tambahMinuman(String namaMenu, double harga, int stok) {
        validasiMenuDasar(namaMenu, harga, stok);
        validasiNamaMenuBelumDipakai(namaMenu, 0);
        menuDAO.insert(new Minuman(0, namaMenu.trim(), harga, stok));
    }

    public void ubahMakanan(int idMenu, String namaMenu, double harga, int stok) {
        validasiIdMenu(idMenu);
        validasiMenuDasar(namaMenu, harga, stok);
        validasiNamaMenuBelumDipakai(namaMenu, idMenu);

        boolean berhasil = menuDAO.update(new Makanan(idMenu, namaMenu.trim(), harga, stok));
        if (!berhasil) {
            throw new IllegalArgumentException("Menu tidak ditemukan.");
        }
    }

    public void ubahMinuman(int idMenu, String namaMenu, double harga, int stok) {
        validasiIdMenu(idMenu);
        validasiMenuDasar(namaMenu, harga, stok);
        validasiNamaMenuBelumDipakai(namaMenu, idMenu);

        boolean berhasil = menuDAO.update(new Minuman(idMenu, namaMenu.trim(), harga, stok));
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

    private void validasiNamaMenuBelumDipakai(String namaMenu, int idMenuSaatIni) {
        MenuItem menuDenganNamaSama = menuDAO.findByNama(namaMenu);
        if (menuDenganNamaSama != null && menuDenganNamaSama.getIdMenu() != idMenuSaatIni) {
            throw new IllegalArgumentException("Nama menu sudah digunakan.");
        }
    }
}
