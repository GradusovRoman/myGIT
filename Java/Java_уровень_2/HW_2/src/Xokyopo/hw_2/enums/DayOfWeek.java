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

public enum DayOfWeek {
    MONDAY("Понедельник", 40),TOESDAY("Вторник", 32),WEDNESDEY("Среда", 24),
    THURSDEY("Четверг", 16),FRIDEY("Пятница", 8),SATURDEY("Суббота", 0),SUNDEY("Воскресенье", 0);

    String rus;
    int workingHour;

    DayOfWeek(String rus, int workingHour) {
        this.rus = rus;
        this.workingHour = workingHour;
    }

    public String getRus() {
        return rus;
    }

    public int getWorkingHour() {
        return workingHour;
    }
}
