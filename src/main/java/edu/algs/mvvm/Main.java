package edu.algs.mvvm;

import edu.algs.mvvm.model.Train;
import edu.algs.mvvm.view.TrainUI;
import edu.algs.mvvm.viewmodel.TrainViewModel;

public class Main {
    public static void main(String[] args) {
        // Создаем экземпляр модели поезда с именем
        Train train = new Train("Правозик из Ромашково");
        
        // Создаем ViewModel, передавая ей ссылку на модель
        TrainViewModel vm = new TrainViewModel(train);
        
        // Создаем представление (GUI)
        TrainUI ui = new TrainUI(vm);
        
        // Отображаем окно на экране
        ui.setVisible(true);
    }
}
