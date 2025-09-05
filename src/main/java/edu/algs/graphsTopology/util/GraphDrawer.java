package edu.algs.graphsTopology.util;

public class GraphDrawer {
    private int[][] adjMatrix;
    private String[] vertexNames;
    private int vertices;

    public GraphDrawer(int[][] matrix, String[] vertexNames) {
        if (matrix.length!= vertexNames.length)
            throw new IllegalArgumentException("Размер матрицы и массива имён вершин должен совпадать");
        this.adjMatrix = matrix;
        this.vertexNames = vertexNames;
        this.vertices = vertexNames.length;
    }

    // Метод рисования графа псевдографикой в консоли
    public void drawGraph() {
        System.out.println("Псевдографическое представление графа:");

        for (int i = 0; i < vertices; i++) {
            System.out.print(vertexNames[i] + ":");
            boolean hasEdges = false;
            for (int j = 0; j < vertices; j++) {
                if (adjMatrix[i][j]!= 0) {
                    System.out.print(" --> " + vertexNames[j]);
                    hasEdges = true;
                }
            }
            if (!hasEdges) System.out.print(" (нет исходящих ребер)");
            System.out.println();
        }
    }
}
