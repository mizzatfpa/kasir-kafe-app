package com.kasirapp.util;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class FormatUtil {
    private static final Locale LOCALE_INDONESIA = Locale.forLanguageTag("id-ID");
    private static final DateTimeFormatter FORMAT_TANGGAL = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    private static final DateTimeFormatter FORMAT_TANGGAL_PENDEK = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    private FormatUtil() {
    }

    public static String formatRupiah(double value) {
        NumberFormat formatter = NumberFormat.getCurrencyInstance(LOCALE_INDONESIA);
        return formatter.format(value);
    }

    public static String formatTanggal(LocalDateTime dateTime) {
        if (dateTime == null) {
            return "";
        }
        return dateTime.format(FORMAT_TANGGAL);
    }

    public static String formatTanggalPendek(LocalDate date) {
        if (date == null) {
            return "";
        }
        return date.format(FORMAT_TANGGAL_PENDEK);
    }
}
