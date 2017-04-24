package com.rgand.x_prt.lastfmhits.util;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * Created by x_prt on 24.04.2017
 */

public class NumberFormatter {

    public static String spacesForBigNumber(String numberAsText) {
        DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
        DecimalFormatSymbols symbols = formatter.getDecimalFormatSymbols();

        symbols.setGroupingSeparator(' ');
        formatter.setDecimalFormatSymbols(symbols);
        return formatter.format(Long.parseLong(numberAsText));
    }
}
