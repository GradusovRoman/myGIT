
package Calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Calc extends JFrame{
    private String name = "Калькулятор";
    //память где хранится результат вычислений
    private double mem ;
    //переменная
    private String number ;
    //знак переменной
    private String sign ;
    //отложенный оператор
    private String operation;
    // флаг говорящий что вводим новое число
    private boolean is_new_number ;
    //задаем двумерный массив определяющий расположение кнопок и их текст
    private String[][] box = {
            {"x^n","C","<-","/"},
            {"1","2","3","*"},
            {"4","5","6","-"},
            {"7","8","9","+"},
            {"+/-","0",".","="}
    };
    //текстовый ярлык, что бы выводить наши числа
    private JLabel label;
    //Задаем размер окна
    private Dimension minSize = new Dimension (400 , 600);
    //Задем отстпум кнопок от края сетки
    private Insets button_inset = new Insets(1,1,1,1);
    //задаем форматтер расположения объектов
    private GridBagConstraints constraints = new GridBagConstraints();


    public Calc(){
        //зададим заголовок окна
        this.setTitle(this.name);
        //инициализируем переменные
        this.clear_calc();

        //рисуем компоненты
        this.drow_window();

        //выводим окно по центру экрана
        this.setLocationRelativeTo(null);
    }

    //метод рисования окна
    private void drow_window(){
        //определеям действие при нажатии на крестик у окна
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //задаем рекомендованный размер окна
        this.setPreferredSize(this.minSize);
        //задаем минимальный размер окна
        this.setMinimumSize(this.minSize);

        //Определяем компановщик объектов
        this.setLayout(new GridBagLayout());

        //говорим форматтеру что объекты растягивать во всех направлениях
        this.constraints.fill = GridBagConstraints.BOTH;
        //без этих значений объекты не заполняют все пространство хз почему, мб подскажете
        this.constraints.weighty = 1;
        this.constraints.weightx = 1;

        //определяем в какую ячейку положить объект
        this.constraints.gridx = 0;
        this.constraints.gridy = 0;
        //говорим что обект заполняет все доступные поля по ширене
        this.constraints.gridwidth = GridBagConstraints.REMAINDER;
        //Создаем нашу надпись, и выравниваем в ней текст по правому краю
        this.label = new JLabel (this.number, SwingConstants.RIGHT);
        //Добавляем наш объект на окно и передаем форматтер что бы он спозицеонировал ее
        this.add(this.label, constraints);
        //добавляем наши кнопки
        this.drow_button();
    }


    //рисуем кнопки на форме
    private void drow_button(){
        //задаем размеры кнопок в 1 ячейку
        this.constraints.gridwidth = 1;
        this.constraints.gridheight = 1;
        // устанавливаем отступы от границ ячейки
        this.constraints.insets = this.button_inset;
        //Создаем кнопки циклом
        for (int y = 0; y < box.length; y++){
            for (int x = 0; x < box[0].length; x++){
                //определяем позицию кнопок через форматтер
                this.constraints.gridx = x;
                this.constraints.gridy = y + 1; // + 1, так как в 0 строке у нас находится ярлык
                //создаем кнопку и в качестве текста передаем ей значение из массива
                JButton button = new JButton(box[y][x]);
                //в качестве экшен команды устанавливаем тоже значение что и текст на кнопке
                button.setActionCommand(box[y][x]);
                //определяем Акшен лисенер
                button.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        //Экшен лисенер будет передавать в нашу функцию текст кнопки (так как button.setActionCommand(box[y][x]);)
                        on_Click(e.getActionCommand());
                    }
                });
                //добавляем кнопку на панель и передаем значения форматтера для определения ее расположения.
                this.add(button, this.constraints);
            }
        }
    }



    //метод, выполняющий действия.
    private void run_operation(String _key){
        //сообщение что на 0 делить нельзя
        String division_by_zero = "деление на 0 невозможно";
        //флаг для проверки деления на 0
        boolean err_division_by_zero = false;
        //получаем переменную из текста ярлыка
        double new_number = Double.parseDouble(this.label.getText());

        //если отложенное действие не пусто то выполняем условие
        if (!this.operation.equals("")){
            //Свитчем выбераем какое действие нужно
            switch (this.operation) {
                case ("+"):
                    this.mem += new_number;
                    break;
                case ("-"):
                    this.mem -= new_number;
                    break;
                case ("*"):
                    this.mem *= new_number;
                    break;
                case ("x^n"):
                    this.mem = Math.pow(this.mem, new_number);
                    break;
                case ("/"):
                    if (new_number != 0) {
                        this.mem /= new_number;
                    } else {
                        //если у нас ошибка деления на 0 то обнуляем все вычесления и поднимае флаг
                        this.clear_calc();
                        err_division_by_zero = true;
                    }
                    break;
            }
            //после вычеслений
            //если наше число отрицательное но задаем значение - в других случаях ""
            this.sign = (this.mem < 0 && !err_division_by_zero)? "-": "";
            //если у нас не делеине на ноль то присваевам модуль вычесленного числа
            this.number = (err_division_by_zero)? division_by_zero: "" + Math.abs(this.mem);
        }else {
            //елси у нас нет отложенно действия то просто сохраняем нашу переменную
            this.mem = new_number;
        }
        // обнавляем отложенный оператор новым значением или обнуляем если введен "="
        this.operation = (_key.equals("="))? "": _key;
    }


    //обрабатывает все нажатия клавиш
    private void on_Click(String key){
        /*
        тут при помощи свитча обрабатываем нажатия
        данный способ применялся в Delphi как рекомендованный для перехвата вводимых клавиш, но тут он тоже применим
         */
        switch (key){
            case ("1"):
            case ("2"):
            case ("3"):
            case ("4"):
            case ("5"):
            case ("6"):
            case ("7"):
            case ("8"):
            case ("9"):
            case ("0"):
            case ("."):
                //если у нас в поле 0 или это новое число (когда нажимаем "=" и выводим результат по новое число true как и при очистки переменных)
                this.number = (this.number.equals("0") || this.is_new_number)? key: this.number + key;
                this.sign = (this.number.equals("0") || this.is_new_number)? "" : this.sign;
                //если добавли хотя бы один символ  то переключаем наш лаг, что бы собирать число в поле
                this.is_new_number = false;
                break;
            case ("+/-"):
                //переключает знаки у числа в поле
                this.sign = (this.sign.equals("-"))? "":"-";
                break;
            case ("+"):
            case ("-"):
            case ("*"):
            case ("/"):
            case ("x^n"):
            case ("="):
                //тут вызываем наши вычисления передавая в метод знак
                //ошибка при двойном вызове.
                this.run_operation(key);
                //после вычеслений сообщаем что можно вводить новое число
                this.is_new_number = true;
                break;
            case ("C"):
                //очисщает переменные калькулятора
                this.clear_calc();
                break;
            case ("<-"):
                //стирает последний введенный символ если это новое число а не результат вычислений
                if (!this.is_new_number) {
                    this.number = (this.number.length() - 1 > 0) ? this.number.substring(0, this.number.length() - 1) : "0";
                    this.sign = (this.number.equals("0")) ? "" : this.sign;
                }
                break;
        }
        //обновляем значение текста ярлыка
        this.label.setText(this.sign + this.number);
    }

    //очищает переменные калькулятора
    private void clear_calc(){
        this.operation = "";
        this.number = "0";
        this.mem = 0;
        this.sign = "";
        this.is_new_number = true;
    }
}
