package ru.adm123.levelAverage.structure;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Класс, описывающий узел двоичного дерева
 */
@Getter
public class Node<T> {

    /**
     * Значение узла ("полезная нагрузка")
     */
    @NonNull
    private final T value;
    /**
     * Левый узел-потомок
     */
    @Setter
    private Node<T> leftChild;
    /**
     * Правый узел-потомок
     */
    @Setter
    private Node<T> rightChild;

    public Node(@NonNull T value,
                Node<T> leftChild,
                Node<T> rightChild) {
        this(value);
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    public Node(@NonNull T value) {
        this.value = value;
    }

    /**
     * Метод собирает бинарное дерево из массива "значений" узлов
     *
     * @param root массив значений узлов
     * @param <V>  тип значения исходного массива и узла дерева
     * @return {@link Node} корневой узел дерева
     */
    public static <V> Node<V> from(@NonNull V[] root) {
        Queue<Node<V>> nodeQueue = arrayToNodeQueue(root);
        Node<V> rootNode = nodeQueue.poll();
        if (rootNode == null) {
            throw new IllegalArgumentException();
        }
        int horizontalLevel = 1;
        List<Node<V>> levelNodeList = new ArrayList<>(Collections.singletonList(rootNode));
        while (!nodeQueue.isEmpty()) {
            int levelNodeCount = horizontalLevel * 2;
            List<Node<V>> nextLevelNodeList = new ArrayList<>(levelNodeCount);
            for (int i = 0; i < levelNodeCount; i++) {
                nextLevelNodeList.add(nodeQueue.poll());
            }
            for (int i = 0; i < levelNodeList.size(); i++) {
                Node<V> node = levelNodeList.get(i);
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
     * @param <V>   тип данных в массиве
     * @return {@link Queue<Node>}
     */
    @NonNull
    private static <V> Queue<Node<V>> arrayToNodeQueue(V[] array) {
        if (array == null) {
            throw new IllegalArgumentException();
        }
        return Arrays.stream(array)
                .map(Node::nodeFromValue)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    private static <V> Node<V> nodeFromValue(V value) {
        return value == null ? null : new Node<>(value);
    }

    @Override
    public String toString() {
        return "{" +
                "\"value\": " + value + "," +
                "\"leftChild\": " + leftChild + "," +
                "\"rightChild\": " + rightChild + "" +
                "}";
    }

}
