package com.kasirapp.service;

import com.kasirapp.model.KeranjangItem;
import com.kasirapp.model.MenuItem;
import com.kasirapp.model.MetodePembayaran;
import com.kasirapp.model.Transaksi;
import com.kasirapp.util.ValidationUtil;
import java.util.ArrayList;
import java.util.List;

public class KasirService {
    private List<KeranjangItem> keranjang = new ArrayList<>();
    private MenuService menuService;
    private TransaksiService transaksiService;

    public KasirService() {
        this.menuService = new MenuService();
        this.transaksiService = new TransaksiService();
    }

    public List<MenuItem> getDaftarMenu() {
        return menuService.getAllMenu();
    }

    public void tambahKeKeranjang(int idMenu, int jumlah) {
        ValidationUtil.validateJumlah(jumlah);

        MenuItem menu = menuService.getMenuById(idMenu);
        if (menu == null) {
            throw new IllegalArgumentException("Menu tidak ditemukan.");
        }

        KeranjangItem itemLama = cariItemKeranjang(idMenu);
        int jumlahBaru = jumlah;
        if (itemLama != null) {
            jumlahBaru += itemLama.getJumlah();
        }

        if (!menu.isStokCukup(jumlahBaru)) {
            throw new IllegalArgumentException("Stok menu tidak cukup.");
        }

        if (itemLama == null) {
            keranjang.add(new KeranjangItem(menu, jumlah));
        } else {
            itemLama.setJumlah(jumlahBaru);
        }
    }

    public void hapusItemKeranjang(int idMenu) {
        keranjang.removeIf(item -> item.getIdMenu() == idMenu);
    }

    public void kosongkanKeranjang() {
        keranjang.clear();
    }

    public List<KeranjangItem> getKeranjang() {
        return new ArrayList<>(keranjang);
    }

    public double hitungTotal() {
        double total = 0;
        for (KeranjangItem item : keranjang) {
            total += item.getSubtotal();
        }
        return total;
    }

    public Transaksi prosesBayar(double bayar, MetodePembayaran metodePembayaran) {
        if (keranjang.isEmpty()) {
            throw new IllegalStateException("Keranjang masih kosong.");
        }
        if (metodePembayaran == null) {
            throw new IllegalArgumentException("Metode pembayaran harus dipilih.");
        }

        double total = hitungTotal();
        double nilaiBayar = bayar;
        double kembalian = 0;

        if (metodePembayaran == MetodePembayaran.TUNAI) {
            if (bayar < total) {
                throw new IllegalArgumentException("Jumlah bayar tunai kurang dari total.");
            }
            kembalian = bayar - total;
        } else {
            nilaiBayar = total;
        }

        Transaksi transaksi = transaksiService.simpanTransaksi(
                new ArrayList<>(keranjang), total, nilaiBayar, kembalian, metodePembayaran);
        kosongkanKeranjang();
        return transaksi;
    }

    private KeranjangItem cariItemKeranjang(int idMenu) {
        for (KeranjangItem item : keranjang) {
            if (item.getIdMenu() == idMenu) {
                return item;
            }
        }
        return null;
    }
}
