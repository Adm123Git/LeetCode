package ru.adm123.monotonicIncrease;

import lombok.NonNull;

import java.util.regex.Pattern;

public class Main {

    private static String s1 = "00110";
    private static String s2 = "01010";
    private static String s3 = "00011000";
    private static String s4 = "010000000101";

    public static void main(String[] args) {
        int minFipCount = getMinFlipCount(s4);
        System.out.println("Минимально необходимое количество переворотов для получения монотонно увеличивающейся " +
                "строки из заданной - " + minFipCount);
    }

    private static int getMinFlipCount(@NonNull String str) {
        if (!needFlip(str)) {
            return 0;
        }
        int firstOnePosition = str.indexOf('1');
        String stringToFlipVariant1 = str.substring(firstOnePosition);
        int zeroCount = getSymbolCount('0', stringToFlipVariant1);
        int oneCount = getSymbolCount('1', stringToFlipVariant1);
        int flipCountVariant1 = Math.min(zeroCount, oneCount);
        int lastZeroPosition = str.lastIndexOf('0');
        String stringToFlipVariant2 = str.substring(0, lastZeroPosition + 1);
        int flipCountVariant2 = getSymbolCount('1', stringToFlipVariant2);
        return Math.min(flipCountVariant1, flipCountVariant2);
    }

    /**
     * Проверяет строку на бинарность, а также нужно ли делать перевороты, чтоб строка стала монотонно увеличивающейся
     *
     * @param str проверяемая строка
     * @return <b>true</b> если требуются перевороты, иначе <b>false</b>
     */
    private static boolean needFlip(@NonNull String str) {
        Pattern pattern = Pattern.compile("^[01]+$");
        if (!pattern.matcher(str).matches()) {
            throw new IllegalArgumentException();
        }
        return str.contains("10");
    }

    /**
     * Подсчет вхождений символа в строку
     *
     * @param symbol искомый символ
     * @param str    проверяемая строка
     * @return кол-во вхождений заданного символа в заданную строку
     */
    private static int getSymbolCount(char symbol,
                                      @NonNull String str) {
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == symbol) {
                count++;
            }
        }
        return count;
    }

}