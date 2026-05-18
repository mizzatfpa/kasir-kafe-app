package com.kasirapp.controller;

import com.kasirapp.model.MenuItem;
import com.kasirapp.service.MenuService;
import java.util.ArrayList;
import java.util.List;

public class MenuController {
    private MenuService menuService;
    private String lastError;

    public MenuController() {
        this.menuService = new MenuService();
    }

    public List<MenuItem> getAllMenu() {
        try {
            clearError();
            return menuService.getAllMenu();
        } catch (Exception e) {
            setError(e);
            return new ArrayList<>();
        }
    }

    public MenuItem getMenuById(int idMenu) {
        try {
            clearError();
            return menuService.getMenuById(idMenu);
        } catch (Exception e) {
            setError(e);
            return null;
        }
    }

    public boolean tambahMakanan(String namaMenu, double harga, int stok, String tingkatPedas) {
        try {
            clearError();
            menuService.tambahMakanan(namaMenu, harga, stok, tingkatPedas);
            return true;
        } catch (Exception e) {
            setError(e);
            return false;
        }
    }

    public boolean tambahMinuman(String namaMenu, double harga, int stok, String ukuran) {
        try {
            clearError();
            menuService.tambahMinuman(namaMenu, harga, stok, ukuran);
            return true;
        } catch (Exception e) {
            setError(e);
            return false;
        }
    }

    public boolean ubahMakanan(int idMenu, String namaMenu, double harga, int stok, String tingkatPedas) {
        try {
            clearError();
            menuService.ubahMakanan(idMenu, namaMenu, harga, stok, tingkatPedas);
            return true;
        } catch (Exception e) {
            setError(e);
            return false;
        }
    }

    public boolean ubahMinuman(int idMenu, String namaMenu, double harga, int stok, String ukuran) {
        try {
            clearError();
            menuService.ubahMinuman(idMenu, namaMenu, harga, stok, ukuran);
            return true;
        } catch (Exception e) {
            setError(e);
            return false;
        }
    }

    public boolean hapusMenu(int idMenu) {
        try {
            clearError();
            menuService.hapusMenu(idMenu);
            return true;
        } catch (Exception e) {
            setError(e);
            return false;
        }
    }

    public String getLastError() {
        return lastError;
    }

    private void clearError() {
        lastError = null;
    }

    private void setError(Exception e) {
        lastError = e.getMessage();
    }
}
