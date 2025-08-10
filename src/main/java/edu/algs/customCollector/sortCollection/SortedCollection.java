package edu.algs.customCollector.sortCollection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortedCollection<T> {
    private List<T> elements; // Список для хранения элементов
    private Comparator<? super T> comparator; // Компаратор для сортировки элементов

    // Конструктор, принимающий компаратор
    public SortedCollection(Comparator<? super T> comparator) {
        this.elements = new ArrayList<>(); // Инициализируем список
        this.comparator = comparator; // Устанавливаем компаратор
    }

    // Метод для добавления элемента в коллекцию
    public void add(T value) {
        // Находим индекс, куда нужно вставить новый элемент
        int index = Collections.binarySearch(elements, value, comparator);
        // Если элемент уже существует, binarySearch вернет индекс, где он находится
        if (index < 0) {
            // Если элемент не найден, возвращаем отрицательное значение, которое указывает на место вставки
            index = -(index + 1);
            elements.add(index, value); // Вставляем элемент в нужное место
        }
    }

    // Метод для получения элемента по индексу
    public T get(int index) {
        return elements.get(index); // O(1)
    }

    // Метод для проверки наличия элемента в коллекции
    public boolean contains(T value) {
        int index = Collections.binarySearch(elements, value, comparator);
        return index >= 0; // Если индекс не отрицательный, элемент найден
    }

    // Метод для получения индекса элемента в коллекции
    public int indexOf(T value) {
        int index = Collections.binarySearch(elements, value, comparator);
        return index >= 0 ? index : -1; // Возвращаем индекс или -1, если элемент не найден
    }

    // Метод для удаления элемента по индексу
    public T remove(int index) {
        return elements.remove(index); // Удаляем элемент и возвращаем его
    }

    // Метод для получения размера коллекции
    public int size() {
        return elements.size(); // Возвращаем количество элементов в коллекции
    }

    // Метод для получения всех элементов в коллекции
    public List<T> getAll() {
        return new ArrayList<>(elements); // Возвращаем копию списка элементов
    }
}
