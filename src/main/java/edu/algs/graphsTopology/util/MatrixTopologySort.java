package edu.algs.graphsTopology.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MatrixTopologySort {

    // Константы для меток вершин
    private static final int UNVISITED = 0;
    private static final int VISITING = 1;
    private static final int VISITED = 2;

    private int[][] adjacencyMatrix; // Матрица смежности
    private String[] vertexNames; // Названия вершин
    private int[] visited; // Массив для отслеживания состояния вершин
    private List<String> result; // Список для хранения результата топологической сортировки
    private boolean hasCycle; // Флаг наличия цикла

    public MatrixTopologySort(int[][] adjacencyMatrix, String[] vertexNames) {
        this.adjacencyMatrix = adjacencyMatrix;
        this.vertexNames = vertexNames;
        this.visited = new int[vertexNames.length];
        this.result = new ArrayList<>();
        this.hasCycle = false;
    }

    // Метод для выполнения топологической сортировки
    public List<String> sort() {
        for (int i = 0; i < vertexNames.length; i++) {
            if (visited[i] == UNVISITED) {
                dfs(i);
            }
        }
        // Если есть цикл, возвращаем пустой список
        if (hasCycle) {
            return Collections.emptyList();
        }
        // Переворачиваем результат, чтобы получить правильный порядок
        Collections.reverse(result);
        return result;
    }

    // Метод DFS для обхода графа
    private void dfs(int vertex) {
        // Помечаем вершину как "в процессе посещения"
        visited[vertex] = VISITING;

        for (int i = 0; i < adjacencyMatrix[vertex].length; i++) {
            if (adjacencyMatrix[vertex][i] == 1) { // Если есть ребро от vertex к i
                if (visited[i] == UNVISITED) {
                    dfs(i);
                } else if (visited[i] == VISITING) {
                    // Мы нашли цикл
                    hasCycle = true;
                }
            }
        }

        // Помечаем вершину как "посещенную"
        visited[vertex] = VISITED;
        result.add(vertexNames[vertex]); // Добавляем вершину в результат
    }
}
