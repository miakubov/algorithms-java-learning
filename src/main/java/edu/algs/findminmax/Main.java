package edu.algs.findminmax;

import edu.algs.findminmax.util.MinMaxUtil;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        // Пример: исходный массив
        int[] array = {7, 2, 15, -6, 9, 0, 21, 5};

        // Вызов утилиты для поиска min и max
        int[] minmax = MinMaxUtil.findMinMax(array);

        // Вывод результатов
        System.out.println("Массив: " + Arrays.toString(array));
        System.out.println("Минимум: " + minmax[0]);
        System.out.println("Максимум: " + minmax[1]);
    }
}
