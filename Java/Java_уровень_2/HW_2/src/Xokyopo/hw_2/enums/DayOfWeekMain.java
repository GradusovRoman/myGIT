package Xokyopo.hw_2.enums;
/*
Требуется реализовать enum DayOfWeek, который будет представлять дни недели.
С его помощью необходимо решить задачу определения кол-ва рабочих часов до конца недели по заднному текущему дню.

 Возвращает кол-во оставшихся рабочих часов до конца
 недели по заданному текущему дню. Считается, что
 текущий день ещё не начался, и рабочие часы за него
 должны учитываться.

public class DayOfWeekMain {

 public static void main(final String[] args) {
 System.out.println(getWorkingHours(DayOfWeek.MONDAY));
 }

 */
public class DayOfWeekMain {
    public static void main(String[] args) {
        System.out.println(getWorkingHours(DayOfWeek.MONDAY));
    }

    private static String getWorkingHours(DayOfWeek _day) {
        return (_day.getWorkingHour() == 0) ? "Сегодня выходной" : "До конца недели " + _day.getWorkingHour() + " рабочих часов";
    }
}
