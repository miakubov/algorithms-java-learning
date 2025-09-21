package edu.algs.intOOP.util;

import java.util.List;

// Интерфейс TrackableList расширяет стандартный интерфейс List и добавляет метод countAddCalls()
public interface TrackableList<T> extends List<T> {
    int countAddCalls();
}
