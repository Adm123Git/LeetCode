package ru.adm123.columnSort;

import lombok.NonNull;

import java.util.Arrays;

public class Main {

    private static String[] strs1 = new String[]{"abc", "bce", "cae"};
    private static String[] strs2 = new String[]{"cba", "daf", "ghi"};
    private static String[] strs3 = new String[]{"a", "b"};
    private static String[] strs4 = new String[]{};

    public static void main(String[] args) {
        String[][] inputArrays = new String[][]{strs1, strs2, strs3, strs4};
        for (String[] inputArray : inputArrays) {
            int notSortedColumnCount = getNotSortedColumnCount(inputArray);
            System.out.println(notSortedColumnCount + " column(s) need be deleted for " + Arrays.toString(inputArray));
        }
    }

    private static int getNotSortedColumnCount(@NonNull String... stringArray) {
        if (stringArray.length == 0) {
            return 0;
        }
        int notSortedColumnCount = 0;
        int strLength = stringArray[0].length();
        for (int i = 0; i < strLength; i++) {
            for (int j = 1; j < stringArray.length; j++) {
                if (stringArray[j].charAt(i) < stringArray[j - 1].charAt(i)) {
                    notSortedColumnCount++;
                    break;
                }
            }
        }
        return notSortedColumnCount;
    }

}
