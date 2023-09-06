package ru.adm123.appleCollect;

import lombok.NonNull;
import ru.adm123.appleCollect.model.InputData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    private static InputData.Edge[] edgeArray
            = new InputData.Edge[]{
            new InputData.Edge(0, 1),
            new InputData.Edge(0, 2),
            new InputData.Edge(1, 4),
            new InputData.Edge(1, 5),
            new InputData.Edge(2, 3),
            new InputData.Edge(2, 6)
    };

    private static InputData input1
            = new InputData(1, 7, edgeArray, new boolean[]{false, false, true, false, true, true, false});

    private static InputData input2
            = new InputData(1, 7, edgeArray, new boolean[]{false, false, true, false, false, true, false});

    public static void main(String[] args) {
        InputData input = input1;
        int stepCount = calculateStepCount(0, -1, getAdjacency(input), input);
        System.out.println("Минимальное время сбора яблок - " + stepCount * input.getTimeToStep());
    }

    /**
     * Составляем список смежности - структуры для представления графа, где каждый элемент списка представляет узел
     * графа и содержит список узлов, с которыми он связан.
     *
     * @param inputData {@link InputData} исходные данные задачи
     * @return список смежности для заданного дерева
     */
    @NonNull
    private static Map<Integer, List<Integer>> getAdjacency(@NonNull InputData inputData) {
        Map<Integer, List<Integer>> adjacency = new HashMap<>();
        for (InputData.Edge edge : inputData.getEdges()) {
            adjacency
                    .computeIfAbsent(edge.getFrom(), val -> new ArrayList<>())
                    .add(edge.getTo());
            adjacency
                    .computeIfAbsent(edge.getTo(), val -> new ArrayList<>())
                    .add(edge.getFrom());
        }
        return adjacency;
    }

    /**
     * Вычисление кол-ва шагов (перемещений по ребрам), необходимого для сбора всех яблок
     *
     * @param nodeNumber       номер текущего (того, который обрабатываем) узла дерева
     * @param parentNodeNumber номер родительского узла
     * @param adjacency        список смежности дерева
     * @param inputData        {@link InputData} исходные данные задачи
     */
    private static int calculateStepCount(int nodeNumber,
                                          int parentNodeNumber,
                                          @NonNull Map<Integer, List<Integer>> adjacency,
                                          @NonNull InputData inputData) {
        boolean[] hasApple = inputData.getHasApple();
        int stepCount = 0;
        // Проходим по списку соседних узлов
        for (int neighbourNodeNumber : adjacency.get(nodeNumber)) {
            // Рассматриваем только дочерние узлы, родительский - нет
            if (neighbourNodeNumber == parentNodeNumber) {
                continue;
            }
            // Рекурсивно выполняем то же самое для дочерних узлов
            stepCount += calculateStepCount(neighbourNodeNumber, nodeNumber, adjacency, inputData);
        }
        // Если узел не корневой и в нем есть яблоко или результат рекурсивного вызова ф-ции для
        // дочерних узлов вернул не 0 (т.е. где-то там, ниже по дереву, нашлось яблоко), то добавляем
        // к маршруту проход по ребру от родителя до этого узла и обратно
        if (nodeNumber > 0
                && (hasApple[nodeNumber] || stepCount > 0)) {
            stepCount += 2;
        }
        return stepCount;
    }

}