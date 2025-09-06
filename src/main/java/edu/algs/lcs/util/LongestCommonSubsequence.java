package edu.algs.lcs.util;

public class LongestCommonSubsequence {

    private final String s1; // Первая строка для поиска LCS
    private final String s2; // Вторая строка для поиска LCS
    private final int[][] dp; // Таблица для динамического программирования

    // Конструктор, принимающий две строки
    public LongestCommonSubsequence(String s1, String s2) {
        this.s1 = s1;
        this.s2 = s2;
        // Инициализация таблицы dp размером (len(s1) + 1) x (len(s2) + 1)
        dp = new int[s1.length() + 1][s2.length() + 1];
        buildDPTable(); // Строим таблицу dp
    }

    // Метод для построения таблицы DP с длинами LCS для всех подстрок s1[0..i-1] и s2[0..j-1]
    private void buildDPTable() {
        // Проходим по всем индексам строк
        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                // Если символы совпадают, увеличиваем длину LCS на 1
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    // В противном случае, берем максимальную длину LCS из двух вариантов
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
    }

    // Метод возвращает длину LCS
    public int getLength() {
        // Длина LCS находится в правом нижнем углу таблицы dp
        return dp[s1.length()][s2.length()];
    }

    // Метод для восстановления строки LCS из таблицы dp
    public String getLCS() {
        StringBuilder lcs = new StringBuilder(); // Строковый буфер для LCS
        int i = s1.length(), j = s2.length(); // Начинаем с конца строк

        // Пытаемся восстановить LCS, следуя по таблице dp
        while (i > 0 && j > 0) {
            if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                lcs.append(s1.charAt(i - 1)); // Если символы совпадают, добавляем его в LCS
                i--; // Двигаемся к предыдущему символу в обеих строках
                j--;
            } else if (dp[i - 1][j] > dp[i][j - 1]) {
                i--; // Если длина LCS без символа из s1 больше, двигаемся вверх по таблице
            } else {
                j--; // Если длина LCS без символа из s2 больше, двигаемся влево по таблице
            }
        }

        // Поскольку мы восстанавливали LCS с конца, нужно перевернуть результат
        return lcs.reverse().toString();
    }
}
