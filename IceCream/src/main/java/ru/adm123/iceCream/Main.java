package ru.adm123.iceCream;

import lombok.NonNull;

import java.util.Arrays;

public class Main {

    private static int coins1 = 7;
    private static int coins2 = 0;
    private static int[] costs1 = new int[]{1, 3, 2, 4, 1};
    private static int[] costs2 = new int[]{10, 6, 8, 7, 7, 8};

    public static void main(String[] args) {
        int iceCreamCount = getMaxIceCreamCount(coins1, costs1);
        System.out.println("На эти деньги при таких ценах получится купить " + iceCreamCount + " стаканчиков мороженого");
    }

    private static int getMaxIceCreamCount(int coins,
                                           @NonNull int[] costs) {
        if (coins < 1
                || costs.length == 0) {
            return 0;
        }
        int count = 0;
        // Отсортируем массив цен...
        int[] sortedCosts = Arrays.stream(costs)
                .sorted()
                .toArray();
        // ...и пройдемся по нему, стараясь купить мороженое, начиная с самого дешевого
        for (int cost : sortedCosts) {
            if (coins < cost) {
                return count;
            }
            count++;
            coins -= cost;
        }
        return count;
    }

}
