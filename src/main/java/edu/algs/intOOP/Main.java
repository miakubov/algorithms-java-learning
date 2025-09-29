package edu.algs.intOOP;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

public class Main {

    @SuppressWarnings("unchecked") // подавляем предупреждение кастинга
    public static void main(String[] args) {
        // Создаём список строк с изначальным элементом
        List<String> strings = new ArrayList<>(List.of("Бегемот"));

        // Создаём обработчик вызовов, который будет оборачивать методы списка
        ArrayListInvocationHandler handler = new ArrayListInvocationHandler(strings);

        // Создаём динамический прокси на основе оригинального списка
        // Что происходит:
        // - используем загрузчик классов оригинального списка
        // - прокси реализует все интерфейсы оригинального списка
        // - все вызовы методов прокси будут перехвачены handler-ом
        strings = (List<String>) Proxy.newProxyInstance(
                strings.getClass().getClassLoader(),
                strings.getClass().getInterfaces(),
                handler
        );

        // Вызываем метод test, который модифицирует список через прокси
        test(strings);

        // Выводим итоговое содержимое списка
        System.out.println(strings);

        // Выводим количество добавлений элементов через прокси (подсчитывается в handler-е)
        System.out.println(handler.count);
    }

    // Метод, который модифицирует список: три раза добавляет элементы и один раз удаляет
    public static void test(List<String> strings) {
        strings.add("Жираф");
        strings.add("Слон");
        strings.add("Тигр");
        strings.remove(1);
    }
}

// Класс-обработчик вызовов методов прокси
class ArrayListInvocationHandler implements InvocationHandler {
    Object list;   // оригинальный объект списка, методы которого будем вызывать
    int count;     // счетчик вызовов методов "add"

    // Конструктор принимает оригинальный список
    public ArrayListInvocationHandler(Object list) {
        this.list = list;
    }

    // Метод, который вызывается при любом вызове метода прокси.
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // Если вызывается метод, начинающийся на "add" (например, add, addAll) —
        // увеличиваем счётчик добавлений
        if (method.getName().startsWith("add")) count++;

        // Вызываем метод на оригинальном объекте со всеми переданными аргументами
        return method.invoke(list, args);
    }
}
