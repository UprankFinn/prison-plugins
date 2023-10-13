package dev.uprank.prison.util;

import org.bukkit.Location;

import java.text.DecimalFormat;
import java.util.Random;

public class MathUtil {

    public static Integer staticRandom(Integer minimum, Integer maximum) {
        Random random = new Random();
        return random.nextInt(maximum - minimum + 1) + minimum;
    }

        public static String kuerzeInteger(int zahl) {
            if (zahl >= 1_000_000_000) { // Größer oder gleich einer Milliarde
                double result = (double) zahl / 1_000_000_000;
                return formatiereZahl(result) + "B";
            } else if (zahl >= 1_000_000) { // Größer oder gleich einer Million
                double result = (double) zahl / 1_000_000;
                return formatiereZahl(result) + "M";
            } else if (zahl >= 1_000) { // Größer oder gleich tausend
                double result = (double) zahl / 1_000;
                return formatiereZahl(result) + "K";
            } else {
                return String.valueOf(zahl);
            }
        }

        public static String formatiereZahl(double zahl) {
            DecimalFormat format = new DecimalFormat("#.##");
            return format.format(zahl);
        }

}
