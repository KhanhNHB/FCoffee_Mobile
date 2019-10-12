package com.example.fcoffee.utils;

import com.example.fcoffee.common.Error;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class FormatMoney {

    public static String formatVND(float money) {
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat nf = NumberFormat.getCurrencyInstance(localeVN);
        return nf.format(money);
    }

}
