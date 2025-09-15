package edu.algs.streamParallel.util;
/**
 * Класс-утилита для проверки строк на корретность значений
 * и приведения строк имён к корректному виду.
 */
public class partsProc{

    /**
     * Проверяем, что длинна имени больше 0.
     * Проверяем, возможно ли приведение первого элемента массива к целому числу.
     *
     * @param parts Массив строковых значений
     * @return true, если первый элемент можно преобразовать в число
     */
    public static boolean isValidKey(String[] parts) {
        // Длина первой части должна быть ненулевой
        if (parts[0].trim().length() == 0) {
            return false;
        }
        // Пробуем строку (вторую часть) преобразовать в число
        try {
            Integer.parseInt(parts[1].trim());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Приводит строку к виду, где первая буква заглавная,а остальные — строчные.
     *
     * @param input Исходная строка
     * @return Преобразованная строка
     */
    public static String capitalize(String input) {
        if (input.isEmpty()) return input;
        return Character.toUpperCase(input.charAt(0)) +
               input.substring(1).toLowerCase();
    }
}
