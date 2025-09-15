package edu.algs.streamParallel;

import edu.algs.streamParallel.util.partsProc;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) throws IOException {
        // Проверка наличия аргумента командной строки
        if (args.length != 1) {
            System.err.println("Запускать необходимо из каталога src c указанием расположения файла имён:\njava -cp main/java edu.algs.streamParallel.Main <filename>");
            return;
        }
        // Передаём в переменную путь к файлу с именами
        String fileName = args[0];

        try (Stream<String> lines = Files.lines(Paths.get(fileName))) {
            // Обрабатываем каждую строку параллельно
            Map<Integer, List<String>> result =
                    lines.parallel()
                         // Разделение строки по символу ":"
                         .map(line -> line.split(Pattern.quote(":")))
                         // Проверяем длину массива (ровно два элемента)
                         .filter(parts -> parts.length == 2)
                         // Отфильтровываем некорректные записи
                         .filter(parts -> partsProc.isValidKey(parts))
                         // Создаем словарь, где ключи — целые числа, а значения — имена с первой заглавной буквой
                         .collect(Collectors.groupingBy(
                                 parts -> Integer.parseInt(parts[1].trim()), // Преобразуем строку в число
                                 Collectors.mapping(part -> partsProc.capitalize(part[0].trim()), Collectors.toList())
                         ));

            // Печать итогового результата
            System.out.println(result);
        }
    }
}
