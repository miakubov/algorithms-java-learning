package edu.algs.lcs;

import edu.algs.lcs.util.LongestCommonSubsequence;

public class Main {
    // Примеры строк для поиска подпоследовательностей с вызвом процедуры testLCS
    public static void main(String[] args) {
        testLCS("AGGTAB", "GXTXAYB");
        testLCS("ABCBDAB", "BDCABA");
        testLCS("HELLO", "WORLD");
    }
    
    // Процедура, создающая экземпляр класса для обнаружения подполследовательности.
    private static void testLCS(String s1, String s2) {
        LongestCommonSubsequence lcs = new LongestCommonSubsequence(s1, s2);
        // Выводим результаты на экран 
        System.out.println("s1 = " + s1);
        System.out.println("s2 = " + s2);
        System.out.println("Length of LCS: " + lcs.getLength());
        System.out.println("LCS: " + lcs.getLCS());
        System.out.println("--------------------------");
    }
}
