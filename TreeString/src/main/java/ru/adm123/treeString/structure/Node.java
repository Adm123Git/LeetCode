package ru.adm123.treeString.structure;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

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

    @Override
    public String toString() {
        return "{" +
                "\"value\": " + value + "," +
                "\"leftChild\": " + leftChild + "," +
                "\"rightChild\": " + rightChild + "" +
                "}";
    }

}
