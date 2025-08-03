package edu.algs.findminmax.util;

/**
 * Класс-утилита для поиска минимума и максимума в массиве с минимальным количеством сравнений.
 */
public class MinMaxUtil {

    /**
     * Находит минимум и максимум в массиве за O(n), используя не более 3(n/2) сравнений.
     *
     * @param arr входной массив
     * @return массив {min, max}
     */
    public static  int[] findMinMax(int[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Массив пустой");
        }

        int min, max;
        int i = 0;

        // Если количество элементов чётное — сравниваем первые 2 элемента
        // Чтобы правильно инициализировать min и max и дальше двигаться парами
        if (arr.length % 2 == 0) {
            if (arr[i]  <  arr[i + 1]) {
                min = arr[i];
                max = arr[i + 1];
            } else {
                min = arr[i+1];
                max = arr[i];
            }
            i = 2;  // следующие пары начинаются с 2-го индекса
        } else {// если нечётное — и min и max равны первому элементу
            min = max = arr[0];
            i = 1;
        }

        // Обходим массив парами: сравниваем элементы между собой, затем min и max
        while (i < arr.length - 1) {
            int localMin, localMax;
            // Сначала внутри пары узнаём, какой из двух меньше, а какой — больше (1 сравнение)
            if (arr[i] < arr[i + 1]) {
                localMin = arr[i];
                localMax = arr[i + 1];
            } else {
                localMin = arr[i + 1];
                localMax = arr[i];
            }
            // Теперь сравниваем локальный минимум с глобальным (ещё 1 сравнение)
            if (localMin < min) min = localMin;
             // И локальный максимум с глобальным (ещё 1 сравнение)
            if (localMax > max) max = localMax;
            i += 2;
        }
        return new int[] {min, max};
    }
}
