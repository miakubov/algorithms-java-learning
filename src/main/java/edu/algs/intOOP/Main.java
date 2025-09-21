package edu.algs.intOOP;

import edu.algs.intOOP.util.*;
import java.util.List;

public class Main {
    // Универсальный метод test, принимающий любую реализацию List
    static public void test(List<Integer> list) {
        System.out.println("Список элементов:");
        for(Integer num : list) {
            System.out.print(num + " ");
        }
        System.out.println("\n");
    }
    
    public static void main(String[] args) {
        // Создание экземпляра TrackingList и заполнение его элементами
        TrackableList<Integer> trackableList = new TrackingList<>();
        trackableList.add(10);
        trackableList.add(100);
        trackableList.add(20);
        trackableList.add(40);
        trackableList.add(30);

        // Передача списка в тестируемый метод
        test(trackableList);

        // Получаем количество вызовов метода add
        int calls = trackableList.countAddCalls();
        System.out.println("Количество вызовов метода add: " + calls);
    }
}
