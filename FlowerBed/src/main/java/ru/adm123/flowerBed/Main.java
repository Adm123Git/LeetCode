package ru.adm123.flowerBed;

import lombok.NonNull;

public class Main {

    private static int n1 = 1;
    private static int[] flowerBed1 = new int[]{1, 0, 0, 0, 1};
    private static int n2 = 2;
    private static int[] flowerBed2 = new int[]{1, 0, 0, 0, 1};

    public static void main(String[] args) {
        boolean isItPossible = isItPossible(n2, flowerBed2);
        System.out.printf("На эту клумбу %sвлезет столько цветов", isItPossible ? "" : "не ");
    }

    private static boolean isItPossible(int flowerCount,
                                        @NonNull int[] flowerBed) {
        if (flowerCount < 0) {
            throw new IllegalArgumentException();
        }
        if (flowerBed.length == 0) {
            return false;
        }
        if (flowerCount == 0) {
            return true;
        }
        int possibilityCounter = 0;
        if (flowerBed.length > 1) {
            for (int i = 0; i < flowerBed.length; i++) {
                if (flowerBed[i] == 0) {
                    if (i > 0
                            && i < flowerBed.length - 1) {
                        if (flowerBed[i - 1] == 0 && flowerBed[i + 1] == 0) {
                            flowerBed[i] = 1;
                            possibilityCounter++;
                        }
                    }
                }
            }
            if (flowerBed[0] == 0
                    && flowerBed[1] == 0) {
                flowerBed[0] = 1;
                possibilityCounter++;
            }
            if (flowerBed[flowerBed.length - 1] == 0
                    && flowerBed[flowerBed.length - 2] == 0) {
                flowerBed[flowerBed.length - 1] = 1;
                possibilityCounter++;
            }
        }
        return possibilityCounter >= flowerCount;
    }

}
