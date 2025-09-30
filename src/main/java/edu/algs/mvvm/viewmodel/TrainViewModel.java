package edu.algs.mvvm.viewmodel;

import edu.algs.mvvm.model.Train;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

// Представляет слой представления моделей (View Model), отслеживающий состояние и изменения поездов
public class TrainViewModel extends Observable {
    private Train train;              // Текущий активный поезд
    private List<Train> trains = new ArrayList<>(); // Коллекция всех поездов

    // Конструктор принимает начальный экземпляр поезда
    public TrainViewModel(Train train) {
        this.train = train;
    }

    // Обновляет коллекцию поездов новым экземпляром и уведомляет наблюдателей
    public void updateTrain(Train train) {
        trains.add(train);       // Добавляем новый поезд в коллекцию
        setChanged();            // Метим объект как изменившийся
        notifyObservers(train);  // Отправляем уведомление подписчикам с объектом нового поезда
    }

    // Удаляет все поезда из коллекции и уведомляет наблюдателей
    public void removeAllTrains() {
        trains.clear();          // Очищаем коллекцию поездов
        setChanged();            // Фиксируем изменение состояния
        notifyObservers();       // Уведомляем подписчиков
    }

    // Предоставляет доступ к коллекции всех поездов
    public List<Train> getTrains() {
        return trains;
    }
}
