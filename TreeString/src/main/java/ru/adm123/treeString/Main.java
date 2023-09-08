package ru.adm123.treeString;

import lombok.NonNull;
import ru.adm123.treeString.structure.Node;

public class Main {

    private static Integer[] root0 = new Integer[]{1, 2, 3, null, null, 6, 7};
    private static Integer[] root1 = new Integer[]{1, 2, 3, 4};
    private static Integer[] root2 = new Integer[]{1, 2, 3, null, 4};

    public static void main(String[] args) {
        Node<Integer> tree = Node.from(root0);
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

}
