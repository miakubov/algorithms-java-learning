package edu.algs.graphs.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс с утилитарными функциями для графов.
 */
public class MatrixToEdge {

    /**
     * Преобразует матрицу смежности в список рёбер.
     * Предполагается, что граф ориентированный.
     *
     * На вход подаётся матрица смежности (NxN), где значение > 0 означает наличие ребра из i в j.
     * На выходе получаем список рёбер, каждое ребро представлено int[] {from, to}.
     */
    public static List<int[]> convertToEdgeList(int[][] adjacencyMatrix) {
        List<int[]> edgeList = new ArrayList<>();

        // Перебираем строки (источник ребра)
        for (int from = 0; from < adjacencyMatrix.length; from++) {
            // Перебираем столбцы (приёмник ребра)
            for (int to = 0; to < adjacencyMatrix[from].length; to++) {
                // Если есть ребро (значение ненулевое)
                if (adjacencyMatrix[from][to]!= 0) {
                    edgeList.add(new int[]{from, to});
                }
            }
        }
        return edgeList;
    }
}
