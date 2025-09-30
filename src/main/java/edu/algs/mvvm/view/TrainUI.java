package edu.algs.mvvm.view;

import edu.algs.mvvm.model.Train;
import edu.algs.mvvm.viewmodel.TrainViewModel;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Observer;

public class TrainUI extends JFrame implements Observer {
    private JTextField trainNameTextField;
    private JButton arrivalButton;
    private JButton departureButton;
    private JLabel statusLabel;
    private TrainViewModel viewModel;
    private JTable trainTable;
    private DefaultTableModel tableModel;

    public TrainUI(TrainViewModel vm) {
        super("Табло железнодорожной станции");      // Заголовок окна
        this.viewModel = vm;                         // Сохраняем ссылку на модель ViewModel
        vm.addObserver(this);                        // Регистрируем текущий UI как наблюдатель изменений в модели
    
        // Настраиваем таблицу отображения поездов
        setupTable();                                // Создаём и настраиваем компоненты таблицы
    
        // Формируем панели ввода и управления действиями
        JPanel inputPanel = createInputPanel();       // Создаём панель для ввода новых поездов
        JPanel actionPanel = createActionPanel();     // Создаём панель кнопок действий (добавить, удалить и т.п.)
    
        // Настраиваем общий макет окна с использованием GridBagLayout
        GridBagLayout layout = new GridBagLayout();  // Используем GridBagLayout для гибкого размещения компонентов
        setLayout(layout);                            // Применяем созданный макет к окну
    
        // Настройки расположения элементов с использованием GridBagConstraints
        GridBagConstraints gbc = new GridBagConstraints();
    
        // Размещаем вводную панель сверху окна
        gbc.gridy = 0;                               // Начальная позиция по вертикали
        gbc.fill = GridBagConstraints.HORIZONTAL;    // Растягивать панель горизонтально
        gbc.weightx = 1.0;                           // Доля занимаемого пространства по ширине
        add(inputPanel, gbc);                        // Добавляем панель ввода в окно
    
        // Таблица поездов размещается ниже панели ввода
        gbc.gridy++;                                 // Переходим на следующую строку вертикально
        add(new JScrollPane(trainTable), gbc);       // Добавляем прокручиваемую таблицу с поездами
    
        // Панель с элементами управления помещаем ниже таблицы
        gbc.gridy++;                                 // Следующая строка вертикально
        add(actionPanel, gbc);                       // Добавляем панель с кнопками
    
        // Статусная метка внизу окна
        gbc.gridy++;                                 // Ещё одна строка снизу
        gbc.anchor = GridBagConstraints.PAGE_END;   // Прилепляем статусную метку к нижней части окна
        add(statusLabel, gbc);                       // Добавляем статусную метку в интерфейс
    
        // Завершаем настройку основного окна
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Окно закрывается при нажатии крестика
        setSize(580, 580);                          // Заданный размер окна
        setResizable(false);                         // Запрещаем изменять размер окна
        setLocationRelativeTo(null);                 // Центрирование окна на экране
        setVisible(true);                            // Показываем окно на экране
    }

    // Настраиваем таблицу с тремя колонками
    private void setupTable() {
        tableModel = new DefaultTableModel(new Object[][]{}, new String[]{"Название поезда", "Время прибытия", "Время отправления"});
        trainTable = new JTable(tableModel) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Делаем ячейки таблицы не редактируемыми
            }
        };
        trainTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Только один поезд выбирается одновременно
    }
    public static final int TRAIN_NAME_COLUMN_INDEX = 0; // Индекс столбца с названием поезда

    private JPanel createInputPanel() {
        JPanel panel = new JPanel();                   // Создаём основную панель для ввода данных
    
        trainNameTextField = new JTextField(20);      // Поле для ввода названия поезда (шириной 20 символов)
        arrivalButton = new JButton("Прибывает");     // Кнопка для отметки прибытия поезда
        statusLabel = new JLabel("Ожидание следующего поезда..."); // Статусная метка с информацией о состоянии системы
    
        arrivalButton.addActionListener(e -> onArrivalClick()); // Назначаем обработчик события нажатия кнопки
    
        // Добавляем описание для текстового поля
        JLabel label = new JLabel("Название поезда:"); // Надпись, объясняющая назначение текстового поля
        panel.add(label);                              // Добавляем надпись на панель
        panel.add(trainNameTextField);                 // Добавляем поле ввода на панель
        panel.add(arrivalButton);                      // Добавляем кнопку "Прибывает" на панель
        panel.add(statusLabel);                        // Добавляем статусную метку на панель
    
        return panel;                                  // Возвращаем готовую панель
    }
    private JPanel createActionPanel() {
        JPanel panel = new JPanel();                     // Создаём панель для действий
        departureButton = new JButton("Отправляется");   // Кнопка для обозначения момента отправления поезда
        departureButton.addActionListener(e -> onDepartureClick()); // Назначаем обработчик события нажатия кнопки
        panel.add(departureButton);                      // Добавляем кнопку на панель
        return panel;                                    // Возвращаем сформированную панель действий
    }

    private void onArrivalClick() {
        String name = trainNameTextField.getText().trim();
        if (!name.isEmpty()) {
            // Проверяем, есть ли поезд с таким именем в списке
            if (isTrainAlreadyExists(name)) {
                JOptionPane.showMessageDialog(this, "Такой поезд уже прибыл.", "Ошибка", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Train train = new Train(name);
            train.setArrival(LocalDateTime.now()); // Устанавливаем текущее время прибытия
            viewModel.updateTrain(train); // Сообщаем VM о событии
            clearTextField(); // Очищаем поле ввода
        }
    }

    private void onDepartureClick() {
        int selectedRow = trainTable.getSelectedRow(); // Получаем выбранную строку
        if (selectedRow != -1) {
            String trainName = (String) trainTable.getValueAt(selectedRow, TRAIN_NAME_COLUMN_INDEX); // Получаем название поезда из строки
            Train train = findTrainByName(trainName); // Поиск поезда по имени
            if (train != null && train.hasArrived()) {
                train.setDeparture(LocalDateTime.now()); // Обновляем время отправления
                viewModel.updateTrain(train); // Уведомляем ViewModel о изменении
            } else {
                JOptionPane.showMessageDialog(this, "Такой поезд ещё не прибыл.", "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Сначала выберите поезд из таблицы.", "Ошибка", JOptionPane.WARNING_MESSAGE);
        }
    }

    // Вспомогательный метод для поиска поезда по имени
    private Train findTrainByName(String trainName) {
        for (Train train : viewModel.getTrains()) {
            if (train.getName().equals(trainName)) {
                return train;
            }
        }
        return null;
    }

    // Проверяет, есть ли поезд с указанным именем в списке.
    private boolean isTrainAlreadyExists(String name) {
        List<Train> trains = viewModel.getTrains();
        for (Train train : trains) {
            if (train.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    // Очищает текстовое поле ввода.
    private void clearTextField() {
        trainNameTextField.setText("");
    }

    @Override
    public void update(java.util.Observable o, Object arg) {
        Train updatedTrain = (Train) arg;
        statusLabel.setText("Поезд " + updatedTrain.getName() + ", прибыл=" + formatTime(updatedTrain.getArrivalTime()) +
                          ", отправлен=" + formatTime(updatedTrain.getDepartureTime()));

        // Обновляем таблицу
        refreshTable(updatedTrain);
    }

    private String formatTime(LocalDateTime time) {
        if (time == null) return "";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yyyy");
        return time.format(formatter);
    }

    private void refreshTable(Train train) {

        // Сначала ищем поезд в таблице и обновляем его статус
        boolean found = false;
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            String currentName = (String)tableModel.getValueAt(i, 0);
            if (currentName.equals(train.getName())) {
                tableModel.setValueAt(formatTime(train.getArrivalTime()), i, 1); // Время прибытия
                tableModel.setValueAt(formatTime(train.getDepartureTime()), i, 2); // Время отправления
                found = true;
                break;
            }
        }


        // Если поезд не найден, добавляем его как новую запись
        if (!found) {
            tableModel.insertRow(0, new Object[]{
                train.getName(),
                formatTime(train.getArrivalTime()),
                formatTime(train.getDepartureTime())
            });
        }
	 
        // Ограничиваем таблицу первыми 20 элементами
        int rows = tableModel.getRowCount();
        while (rows >= 20) {
            tableModel.removeRow(rows - 1);
            rows--;
        }
    }
}
