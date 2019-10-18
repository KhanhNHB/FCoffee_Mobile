package com.group3.fcoffee.utils;

import java.text.NumberFormat;
import java.util.Locale;

public class FormatMoney {

    public static String formatVND(float money) {
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat nf = NumberFormat.getCurrencyInstance(localeVN);
        return nf.format(money);
    }

}
