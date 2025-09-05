package edu.algs.graphsTopology;

import edu.algs.graphsTopology.util.MatrixTopologySort;
import edu.algs.graphsTopology.util.GraphDrawer;

import java.util.List;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        // Название вершин графа
        String[] vertexNames = {"A", "B", "C", "D", "E", "F"}; 
        // Пример графа в виде матрицы смежности 
        // Должна быть ошибка, так как граф содержит цикл
        //   A  B  C  D  E  F
        int[][] adjacencyMatrix1 = {
            {0, 1, 0, 0, 0, 0}, //A->B
            {0, 0, 1, 1, 0, 1}, //B->C,B->D,B->F
            {0, 0, 0, 0, 1, 0}, //C->E
            {0, 0, 1, 0, 0, 0}, //D->C
            {0, 0, 0, 1, 0, 1}, //E->D,E->F
            {0, 0, 0, 0, 0, 0}  //F
        };
        // Пример графа в виде матрицы смежности
        // Направленный ациклический граф
        //   A  B  C  D  E  F
        int[][] adjacencyMatrix2 = {
            {0, 1, 0, 0, 0, 0}, //A->B 
            {0, 0, 1, 0, 0, 1}, //B->C,B->F
            {0, 0, 0, 1, 0, 0}, //C->D
            {0, 0, 0, 0, 0, 0}, //D
            {0, 0, 0, 1, 0, 0}, //E->D
            {0, 0, 0, 1, 0, 0}  //F->D
        };
        int[][][] matrices = {adjacencyMatrix1, adjacencyMatrix2};

        for (int i = 0; i < matrices.length; i++) {
            int[][] matrix = matrices[i];
            System.out.println("Матрица:");
            for (int[] row: matrix) {
                System.out.println(Arrays.toString(row));
            }
           // Разделитель в консоли
            System.out.println();
            // Создаём класс для отрисовки матрицы
            GraphDrawer graph = new GraphDrawer(matrix, vertexNames);
            // Вызываем метод отрисовки
            graph.drawGraph();
            // Создаём экземпляр класса с параметрами матрицы и именами узлов
            MatrixTopologySort topologicalSort = new MatrixTopologySort(matrix, vertexNames);
	    // Список для хранения порядка отсортированных вершин
            List<String> sortedOrder = topologicalSort.sort();
            // Разделитель в консоли
            System.out.println();
            // Выводим результат
            if (sortedOrder.isEmpty()) {
                System.out.println("Граф содержит цикл. Топологическая сортировка невозможна.");
            } else {
                System.out.println("Топологическая сортировка: " + sortedOrder);
            };
           // Разделитель в консоли
           System.out.println("======================================\n");
        }
   }
}

