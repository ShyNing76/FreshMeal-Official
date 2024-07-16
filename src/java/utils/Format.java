/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.text.NumberFormat;
import java.util.Locale;

/**
 *
 * @author ADMIN
 */
public class Format {

    public static String formatCurrency(int money) {
        NumberFormat numberFormat = NumberFormat.getInstance(Locale.US);
        String formattedValue = numberFormat.format(money);
        return formattedValue;
    }
}
