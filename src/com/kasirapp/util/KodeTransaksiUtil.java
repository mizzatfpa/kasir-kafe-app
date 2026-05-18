package com.kasirapp.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class KodeTransaksiUtil {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");

    private KodeTransaksiUtil() {
    }

    public static String generateKodeTransaksi() {
        return "TRX" + LocalDateTime.now().format(FORMATTER);
    }
}
