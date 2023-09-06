package ru.adm123.appleCollect.model;

import lombok.Getter;

/**
 * Класс, инкапсулирующий исходные данные
 */
@Getter
public class InputData {

    /**
     * Время прохода по одному ребру
     */
    private final int timeToStep;
    /**
     * число вершин
     */
    private final int n;
    /**
     * Массив ребер
     */
    private final Edge[] edges;
    /**
     * Массив флагов наличия яблока в i-той вершине
     */
    private final boolean[] hasApple;

    public InputData(int timeToStep,
                     int n,
                     Edge[] edges,
                     boolean[] hasApple) {
        if (n <= 1
                || edges == null
                || edges.length != n - 1
                || hasApple == null
                || hasApple.length != n) {
            throw new IllegalArgumentException();
        }
        this.timeToStep = Math.max(timeToStep, 1);
        this.n = n;
        this.edges = edges;
        this.hasApple = hasApple;
    }

    /**
     * Класс, описывающий ребро
     */
    @Getter
    public static class Edge {

        /**
         * Номер вершины
         */
        private final int from;
        /**
         * Номер вершины
         */
        private final int to;

        public Edge(int from,
                    int to) {
            if (from < 0
                    || to == from) {
                throw new IllegalArgumentException();
            }
            this.from = from;
            this.to = to;
        }

    }

}
