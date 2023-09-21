package ru.adm123.minLexicographicSimilarity;

import java.util.*;

public class Main {

    private static String baseStr_1 = "parser";
    private static String s1_1 = "parker";
    private static String s2_1 = "morris";

    private static String baseStr_2 = "hold";
    private static String s1_2 = "hello";
    private static String s2_2 = "world";

    private static String baseStr_3 = "sourcecode";
    private static String s1_3 = "leetcode";
    private static String s2_3 = "programs";

    public static void main(String[] args) {
        Map<Character, Set<Character>> mapOfEquivalent = getMapOfEquivalent(s1_1, s2_1);
        String minLexicographicSimilarityString = getMinLexicographicSimilarityString(baseStr_1, mapOfEquivalent);
        System.out.printf("Для заданных параметров лексикографически минимальная эквивалентная строка - \"%s\"", minLexicographicSimilarityString);

    }

    private static void checkParams(String s1,
                                    String s2) {
        if (s1 == null
                || s2 == null
                || s1.length() != s2.length()
                || s1.isEmpty()) {
            throw new IllegalArgumentException();
        }
    }

    private static Map<Character, Set<Character>> getMapOfEquivalent(String s1,
                                                                     String s2) {
        checkParams(s1, s2);
        Map<Character, Set<Character>> mapOfEquivalent = new HashMap<>();
        for (int i = 0; i < s1.length(); i++) {
            char s1Symbol = s1.charAt(i);
            char s2Symbol = s2.charAt(i);
            mapOfEquivalent
                    .computeIfAbsent(s1Symbol, key -> new TreeSet<>())
                    .add(s2Symbol);
            mapOfEquivalent
                    .computeIfAbsent(s2Symbol, key -> new TreeSet<>())
                    .add(s1Symbol);
            for (Set<Character> equivalentSet : mapOfEquivalent.values()) {
                if (equivalentSet.contains(s1Symbol)) {
                    equivalentSet.addAll(mapOfEquivalent.get(s1Symbol));
                }
                if (equivalentSet.contains(s2Symbol)) {
                    equivalentSet.addAll(mapOfEquivalent.get(s2Symbol));
                }
            }
        }
        return mapOfEquivalent;
    }

    private static String getMinLexicographicSimilarityString(String source,
                                                              Map<Character, Set<Character>> mapOfEquivalent) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < source.length(); i++) {
            char symbol = source.charAt(i);
            Set<Character> equivalentSymbols = mapOfEquivalent.get(symbol);
            if (equivalentSymbols == null) {
                stringBuilder.append(symbol);
                continue;
            }
            stringBuilder.append(equivalentSymbols.iterator().next());
        }
        return stringBuilder.toString();
    }

}