import javax.swing.*; // импортируем для использования GUI-компонентов
import java.awt.*; // импортируем для использования GUI-компонентов
import java.awt.event.ActionEvent; // импортируем для создания события (нажатие кнопки)
import java.awt.event.ActionListener; // импортируем для реагирования на событие (нажатие кнопки)

public class Football {

    // Создаём класс для обработки нажатия кнопок
    public static class ClickCounter implements ActionListener {
        private int MilanScore = 0; // счёт Милана
        private int MadridScore = 0; // счёт Мадрида
        private String Last_Scorer = "N/A"; // кто последний забил гол

        // Строки для отображения результата, последнего, кто забил, и победителя
        private JLabel ResultLabel;
        private JLabel Last_ScorerLabel;
        private JLabel WinnerLabel;

        // Кнопки команд
        private JButton AC_Milan;
        private JButton Real_Madrid;

        // Создаём метод для получения панели
        public JPanel getPanel() {

            // Экземпляр общей панели
            JPanel panel = new JPanel();
            panel.setLayout(new BorderLayout()); // делит панель на 5 частей

            // Экземпляр панели кнопок
            JPanel ButtonPanel = new JPanel();
            ButtonPanel.setLayout(new FlowLayout()); // компоненты располагаются один за другим

            // Экземпляр 1 кнопки
            AC_Milan = new JButton("AC Milan");
            // Добавляем текущий экземпляр ClickCounter в качестве слушателя события для кнопки
            AC_Milan.addActionListener(this);
            ButtonPanel.add(AC_Milan); // добавляем кнопку в панель

            // Экземпляр 2 кнопки
            Real_Madrid = new JButton("Real Madrid");
            // Добавляем текущий экземпляр ClickCounter в качестве слушателя события для кнопки
            Real_Madrid.addActionListener(this);
            ButtonPanel.add(Real_Madrid); // добавляем кнопку в панель

            // Экземпляр панели строк
            JPanel LabelPanel = new JPanel();
            LabelPanel.setLayout(new GridLayout(3, 1)); // табличное представление компонентов

            // Строка результата
            ResultLabel = new JLabel("Result: " + MilanScore + " X " + MadridScore, JLabel.CENTER);
            LabelPanel.add(ResultLabel);

            // Строка последней забившей гол команды
            Last_ScorerLabel = new JLabel("Last Scorer: N/A",  JLabel.CENTER);
            LabelPanel.add(Last_ScorerLabel);

            // Строка победителя
            WinnerLabel = new JLabel("Winner: DRAW", JLabel.CENTER);
            LabelPanel.add(WinnerLabel);

            // Добавляем панели кнопок и строк к общей панели
            panel.add(ButtonPanel, BorderLayout.NORTH);
            panel.add(LabelPanel, BorderLayout.CENTER);

            return panel;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            // e.getSource() возвращает ссылку на объект, от которого пришло событие
            if (e.getSource() == AC_Milan) {
                MilanScore++; // увеличиваем счётчик
                Last_Scorer = "AC Milan"; // последний, кто забил
                updateLabels(); // метод, обновляющий строки (ниже)

            }
            else if (e.getSource() == Real_Madrid) {
                MadridScore++; // увеличиваем счётчик
                Last_Scorer = "Real Madrid"; // последний, кто забил
                updateLabels(); // метод, обновляющий строки (ниже)
            }
        }

        // Метод для обновления значения строк после нажатия кнопок
        public void updateLabels() {
            // Обновление результата и последней забившей гол команды
            ResultLabel.setText("Result: " + MilanScore + " X " + MadridScore);
            Last_ScorerLabel.setText("Last Scorer: " + Last_Scorer);

            // Определение победителя ч/з if..else
            if (MilanScore > MadridScore) {
                WinnerLabel.setText("Winner: AC Milan");
            }
            else if (MadridScore > MilanScore) {
                WinnerLabel.setText("Winner: Real Madrid");
            }
            else {
                WinnerLabel.setText("Winner: DRAW");
            }
        }

    }

    public static void main(String args[]) {
        /*  Создаем фрейм окна с помощью конструктора.
        Конструктор берет параметр – название окна */
        JFrame frame = new JFrame("Match Score");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // устанавливаем реакцию окна на закрытие по умолчанию
        frame.setSize(300, 300); // задаем свойства окна – его размеры в пикселях
        ClickCounter counter = new ClickCounter(); // экземпляр работы кнопок
        JPanel panel = counter.getPanel(); // через метод получаем модель с кнопками и строками
        Container content = frame.getContentPane(); // создаём контейнер для добавления в него панели
        content.add(panel, BorderLayout.CENTER);  // добавляем панель, располагаем по центру
        panel.setPreferredSize(new Dimension(500,300));  // задаём размеры панели
        frame.pack(); //упаковываем во фрейм
        frame.setVisible(true); // отображаем полученный фрейм
    }
}