package tests;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class DecimalFormatTest {
    public static void main(String[] args) {
        test1();
    }

    public static void test1() {
        Locale locale = new Locale("en", "UK");
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(locale);
        String pattern = "###,000.#########";// 012.12345679
        DecimalFormat sDecimalFormat = new DecimalFormat(pattern, symbols);
        System.out.println(sDecimalFormat.format(12.1234567896));
    }
}