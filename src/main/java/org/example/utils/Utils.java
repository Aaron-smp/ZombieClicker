package org.example.utils;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class Utils {

    public static double truncateDouble(String numString){
        numString.replace(",", ".");
        double num = Double.parseDouble(numString);
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
        DecimalFormat df = new DecimalFormat("#.##", symbols);
        df.setRoundingMode(RoundingMode.DOWN);
        return Double.parseDouble(df.format(num));
    }
}
