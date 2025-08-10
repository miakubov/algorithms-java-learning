package edu.algs.customCollector;

import edu.algs.customCollector.sortCollection.SortedCollection;

import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        // Создаем коллекцию для хранения целых чисел с использованием компаратора по возрастанию
        SortedCollection<Integer> sortedCollection = new SortedCollection<>(Comparator.naturalOrder());

        // Добавляем элементы
        sortedCollection.add(5);
        sortedCollection.add(3);
        sortedCollection.add(8);
        sortedCollection.add(1);
	
        // Получаем все элементы
        System.out.println("Добавленные элементы: " + sortedCollection.getAll()); // [1, 3, 5, 8]

        // Получаем элемент по индексу
        System.out.println("Элемент по индексу 2: " + sortedCollection.get(2)); // 5

        // Проверяем наличие элемента
        System.out.println("Содержит ли 3? " + sortedCollection.contains(3)); // true
        System.out.println("Содержит ли 7? " + sortedCollection.contains(7)); // false

        // Получаем индекс элемента
        System.out.println("Индекс элемента 8: " + sortedCollection.indexOf(8)); // 3
        System.out.println("Индекс элемента 7: " + sortedCollection.indexOf(7)); // -1

        // Удаляем элемент по индексу
        System.out.println("Удаляем элемент по индексу 1: " + sortedCollection.remove(1)); // 3

        // Получаем все элементы
        System.out.println("Все элементы: " + sortedCollection.getAll()); // [1, 5, 8]
    }
}
