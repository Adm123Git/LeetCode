package ru.adm123.levelAverage;


import lombok.NonNull;
import ru.adm123.levelAverage.structure.Node;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    private static Integer[] root1 = new Integer[]{3, 9, 20, null, null, 15, 7};
    private static Integer[] root2 = new Integer[]{3, 9, 20, 15, 7};

    public static void main(String[] args) {
        Node<Integer> tree = Node.from(root2);
        Float[] levelAverageArray = getLevelAverage(tree);
        System.out.println("Массив со средними узлов по уровням: " + Arrays.toString(levelAverageArray));
    }

    @NonNull
    private static Float[] getLevelAverage(@NonNull Node<Integer> node) {
        Map<Integer, List<Node<Integer>>> levelNodeMap = new HashMap<>();
        int level = 0;
        levelNodeMap.put(level, Collections.singletonList(node));
        if (nodeHasChild(node)) {
            boolean hasNextLevel = true;
            while (hasNextLevel) {
                hasNextLevel = false;
                for (Node<Integer> nodeInLevel : levelNodeMap.get(level)) {
                    if (nodeInLevel == null) {
                        continue;
                    }
                    if (nodeHasChild(nodeInLevel)) {
                        hasNextLevel = true;
                        levelNodeMap
                                .computeIfAbsent(level + 1, value -> new ArrayList<>())
                                .add(nodeInLevel.getLeftChild());
                        levelNodeMap
                                .computeIfAbsent(level + 1, value -> new ArrayList<>())
                                .add(nodeInLevel.getRightChild());
                    }
                }
                level++;
            }
        }
        List<Float> levelAverageList = levelNodeMap.values().stream()
                .map(Main::getNodeListAverage)
                .collect(Collectors.toList());
        return levelAverageList.toArray(levelAverageList.toArray(new Float[0]));
    }

    private static <T> boolean nodeHasChild(@NonNull Node<T> node) {
        return node.getLeftChild() != null
                || node.getRightChild() != null;
    }

    private static float getNodeListAverage(@NonNull List<Node<Integer>> nodeList) {
        return nodeList.stream()
                .mapToInt(Node::getValue)
                .sum() * 1f / nodeList.size();
    }

}