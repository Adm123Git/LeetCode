package ru.adm123.treeString;

import lombok.NonNull;
import ru.adm123.treeString.structure.Node;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    private static Integer[] root0 = new Integer[]{1, 2, 3, 4, 5, 6, 7};
    private static Integer[] root1 = new Integer[]{1, 2, 3, 4};
    private static Integer[] root2 = new Integer[]{1, 2, 3, null, 4};

    public static void main(String[] args) {
        Node<Integer> tree = createTree(root2);
    }

    private static <T> Node<T> createTree(T[] root) {
        if (root == null
                || root.length == 0
                || root[0] == null) {
            throw new IllegalArgumentException();
        }
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
                node.setLeftChild(nextLevelNodeList.get(i * 2));
                node.setRightChild(nextLevelNodeList.get(i * 2 + 1));
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

    private static <T> void setNodeChildrenFromQueue(@NonNull Node<T> node,
                                                     @NonNull Queue<Node<T>> queue) {
        node.setLeftChild(queue.poll());
        node.setRightChild(queue.poll());
//        if (node.getLeftChild() != null) {
//            setNodeChildrenFromQueue(node.getLeftChild(), queue);
//        }
//        if (node.getRightChild() != null) {
//            setNodeChildrenFromQueue(node.getRightChild(), queue);
//        }
    }

}
