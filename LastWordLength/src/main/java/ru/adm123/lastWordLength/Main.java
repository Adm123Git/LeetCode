package ru.adm123.lastWordLength;

import lombok.NonNull;

public class Main {

    private static final String s1 = "Hello World";
    private static final String s2 = "   fly me   to   the moon  ";

    public static void main(String[] args) {
        int lastWordLength = getLastWordLength(s1);
        System.out.println("Длина последнего слова в заданной строке - " + lastWordLength + " символов");
    }

    private static int getLastWordLength(@NonNull String str) {
        int lastWordLength = 0;
        int currentWordLength = 0;
        for (int i = 0; i < str.length(); i++) {
            char symbol = str.charAt(i);
            if (symbol != ' ') {
                currentWordLength++;
            } else {
                if (currentWordLength != 0) {
                    lastWordLength = currentWordLength;
                }
                currentWordLength = 0;
            }
        }
        return lastWordLength;
    }

}
