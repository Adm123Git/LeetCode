package ru.adm123.treeString;

import lombok.NonNull;
import ru.adm123.treeString.structure.Node;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    private static Integer[] root0 = new Integer[]{1, 2, 3, null, null, 6, 7};
    private static Integer[] root1 = new Integer[]{1, 2, 3, 4};
    private static Integer[] root2 = new Integer[]{1, 2, 3, null, 4};

    public static void main(String[] args) {
        Node<Integer> tree = createTree(root0);
        String treeString = treeToString(tree);
        System.out.printf("Бинарное дерево из этого массива при обходе его предварительным порядком в виде строки будет %s", treeString);
    }

    @NonNull
    private static <T> String treeToString(@NonNull Node<T> node) {
        StringBuilder resultCollector = new StringBuilder();
        resultCollector
                .append(node.getValue());
        if (node.getLeftChild() != null) {
            resultCollector.append("(")
                    .append(treeToString(node.getLeftChild()))
                    .append(")");
        }
        if (node.getRightChild() != null) {
            if (node.getLeftChild() == null) {
                resultCollector.append("()");
            }
            resultCollector
                    .append("(")
                    .append(treeToString(node.getRightChild()))
                    .append(")");
        }
        return resultCollector.toString();
    }

    /**
     * Метод собирает бинарное дерево из массива "значений" узлов
     *
     * @param root массив значений узлов
     * @param <T>  тип значения исходного массива и узла дерева
     * @return {@link Node} корневой узел дерева
     */
    @NonNull
    private static <T> Node<T> createTree(@NonNull T[] root) {
        Queue<Node<T>> nodeQueue = arrayToNodeQueue(root);
        Node<T> rootNode = nodeQueue.poll();
        if (rootNode == null) {
            throw new IllegalArgumentException();
        }
        int horizontalLevel = 1;
        List<Node<T>> levelNodeList = new ArrayList<>(Collections.singletonList(rootNode));
        while (!nodeQueue.isEmpty()) {
            int levelNodeCount = horizontalLevel * 2;
            List<Node<T>> nextLevelNodeList = new ArrayList<>(levelNodeCount);
            for (int i = 0; i < levelNodeCount; i++) {
                nextLevelNodeList.add(nodeQueue.poll());
            }
            for (int i = 0; i < levelNodeList.size(); i++) {
                Node<T> node = levelNodeList.get(i);
                if (node != null) {
                    node.setLeftChild(nextLevelNodeList.get(i * 2));
                    node.setRightChild(nextLevelNodeList.get(i * 2 + 1));
                }
            }
            levelNodeList = nextLevelNodeList;
            horizontalLevel++;
        }
        return rootNode;
    }

    /**
     * Метод преобразует массив в очередь узлов бинарного дерева, типизированных типом элементов массива,
     * с сохранением порядка следования элементов
     *
     * @param array исходный массив
     * @param <T>   тип данных в массиве
     * @return {@link Queue<Node>}
     */
    @NonNull
    private static <T> Queue<Node<T>> arrayToNodeQueue(T[] array) {
        if (array == null) {
            throw new IllegalArgumentException();
        }
        return Arrays.stream(array)
                .map(Main::nodeFromValue)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    private static <T> Node<T> nodeFromValue(T value) {
        return value == null ? null : new Node<>(value);
    }

}
