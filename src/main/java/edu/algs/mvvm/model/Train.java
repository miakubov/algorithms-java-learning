package edu.algs.mvvm.model;

import java.time.LocalDateTime;
import java.util.Observable;

// Класс моделирует поезд с возможностью уведомления наблюдателей (Observer pattern)
public class Train extends Observable {
    private String name;               // Название поезда
    private LocalDateTime arrivalTime; // Время прибытия поезда
    private LocalDateTime departureTime; // Время отправления поезда

    // Конструктор инициализирует название поезда
    public Train(String name) {
        this.name = name;
    }

    // Геттер для получения названия поезда
    public String getName() {
        return name;
    }

    // Установить время прибытия и уведомить наблюдателей
    public void setArrival(LocalDateTime time) {
        this.arrivalTime = time;
        setChanged();          // Отмечаем объект как изменённый
        notifyObservers();     // Оповещаем подписчиков о произошедшем изменении
    }

    // Установить время отправления и уведомить наблюдателей
    public void setDeparture(LocalDateTime time) {
        this.departureTime = time;
        setChanged();          // Отмечаем объект как изменённый
        notifyObservers();     // Оповещаем подписчиков о произошедшем изменении
    }

    // Получить время прибытия
    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    // Получить время отправления
    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    // Проверить, прибыл ли поезд (если установлено время прибытия)
    public boolean hasArrived() {
        return arrivalTime != null;
    }

    // Проверить, отправился ли поезд (если установлено время отправления)
    public boolean hasDeparted() {
        return departureTime != null;
    }
}
