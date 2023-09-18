package ru.adm123.nodeCount;

import lombok.Getter;
import lombok.NonNull;

import java.util.*;

public class Main {

    /**
     * Ребро дерева
     */
    @Getter
    public static class Edge {

        /**
         * Номер первого узла из тех, что соединяет это ребро
         */
        private final int first;
        /**
         * Номер второго узла из тех, что соединяет это ребро
         */
        private final int second;

        private Edge(int first,
                     int second) {
            this.first = first;
            this.second = second;
        }

        @NonNull
        public static Edge of(int first,
                              int second) {
            return new Edge(first, second);
        }

    }

    private static Edge[] edges_1 = new Edge[]{
            Edge.of(0, 1),
            Edge.of(0, 2),
            Edge.of(1, 4),
            Edge.of(1, 5),
            Edge.of(2, 3),
            Edge.of(2, 6)
    };
    private static String labels_1 = "abaedcd";

    private static Edge[] edges_2 = new Edge[]{
            Edge.of(0, 1),
            Edge.of(1, 2),
            Edge.of(0, 3)
    };
    private static String labels_2 = "bbbb";

    private static Edge[] edges_3 = new Edge[]{
            Edge.of(0, 1),
            Edge.of(0, 2),
            Edge.of(1, 3),
            Edge.of(0, 4)
    };
    private static String labels_3 = "aabab";

    public static void main(String[] args) {

        Map<Integer, List<Integer>> treeAdjacencyList = getAdjacencyList(edges_3);
        int[] result = calculateNodeCountWithSameLabel(labels_3, treeAdjacencyList);
        System.out.println("Ответ = " + Arrays.toString(result));
    }

    /**
     * Метод создает список смежности дерева, чьи ребра заданы на входе
     *
     * @param treeEdges массив ребер дерева, представленных объектами класса {@link Edge}
     * @return список связности дерева
     */
    @NonNull
    private static Map<Integer, List<Integer>> getAdjacencyList(@NonNull Edge[] treeEdges) {
        Map<Integer, List<Integer>> connectivityList = new HashMap<>();
        for (Edge edge : treeEdges) {
            connectivityList
                    .computeIfAbsent(edge.first, key -> new ArrayList<>())
                    .add(edge.second);
            connectivityList
                    .computeIfAbsent(edge.second, key -> new ArrayList<>());
        }
        return connectivityList;
    }

    /**
     * Вычисление кол-ва узлов в поддеревьях узлов дерева с той же меткой, что у корня поддерева
     *
     * @param labels            строка, i-ый символ которой представляет метку i-го узла
     * @param treeAdjacencyList список смежности дерева
     * @return массив, в котором на i-ом месте - кол-во узлов из поддерева i-го узла с той же меткой, что у него
     */
    @NonNull
    private static int[] calculateNodeCountWithSameLabel(@NonNull String labels,
                                                         @NonNull Map<Integer, List<Integer>> treeAdjacencyList) {
        if (labels.length() != treeAdjacencyList.size()) {
            throw new IllegalArgumentException();
        }
        int[] result = new int[labels.length()];
        for (int i = 0; i < labels.length(); i++) {
            List<Integer> childrenNodes = treeAdjacencyList.get(i);
            if (childrenNodes == null) {
                throw new IllegalArgumentException();
            }
            Queue<Integer> nodeNumberQueue = new LinkedList<>();
            nodeNumberQueue.add(i);
                while (!nodeNumberQueue.isEmpty()) {
                    int nodeNumber = nodeNumberQueue.poll();
                    if (labels.charAt(nodeNumber) == labels.charAt(i)) {
                        result[i]++;
                    }
                    nodeNumberQueue.addAll(treeAdjacencyList.get(nodeNumber));
                }
        }
        return result;
    }

}