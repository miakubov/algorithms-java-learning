package edu.algs.graphs;

import edu.algs.graphs.util.MatrixToEdge;

import java.util.List;

/**
 * Главный класс для тестирования преобразования матрицы в список рёбер.
 */
public class Main {

    public static void main(String[] args) {
        // Пример матрицы смежности для ориентированного графа на 5 вершинах
        int[][] adjacencyMatrix = {
                {0, 1, 0, 0, 1},
                {0, 0, 1, 0, 0},
                {1, 0, 0, 1, 0},
                {0, 1, 0, 0, 1},
                {1, 1, 0, 0, 1},
        };

        // Выводим матрицу смежности
        System.out.println("Матрица смежности:");
        for (int[] row: adjacencyMatrix) {
            for (int val: row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }

        // Преобразуем в список рёбер
        List<int[]> edgeList = MatrixToEdge.convertToEdgeList(adjacencyMatrix);

        // Выводим список рёбер
        System.out.println("\nСписок рёбер (from -> to):");
        for (int[] edge: edgeList) {
            System.out.printf("%d -> %d%n", edge[0], edge[1]);
        }
    }
}
