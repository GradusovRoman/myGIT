package Xokyopo.HW_5;
/*
1. Необходимо написать два метода, которые делают следующее:
1) Создают одномерный длинный массив, например:
*static final int size = 10000000;
*static final int h = size / 2;
*float[] arr = new float[size];
2) Заполняют этот массив единицами;
3) Засекают время выполнения: long a = System.currentTimeMillis();
4) Проходят по всему массиву и для каждой ячейки считают новое значение по формуле:
arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
5) Проверяется время окончания метода System.currentTimeMillis();
6) В консоль выводится время работы: System.out.println(System.currentTimeMillis() - a);
Отличие первого метода от второго:
Первый просто бежит по массиву и вычисляет значения.
Второй разбивает массив на два массива, в двух потоках высчитывает новые значения и потом склеивает эти массивы обратно в один.

Пример деления одного массива на два:
System.arraycopy(arr, 0, a1, 0, h);
System.arraycopy(arr, h, a2, 0, h);

Пример обратной склейки:
System.arraycopy(a1, 0, arr, 0, h);
System.arraycopy(a2, 0, arr, h, h);

Примечание:
System.arraycopy() копирует данные из одного массива в другой:
System.arraycopy(массив-источник, откуда начинаем брать данные из массива-источника, массив-назначение, откуда начинаем записывать данные в массив-назначение, сколько ячеек копируем)
По замерам времени:
Для первого метода надо считать время только на цикл расчета:
for (int i = 0; i < size; i++) {
arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
}
Для второго метода замеряете время разбивки массива на 2, просчета каждого из двух массивов и склейки.
 */

public class Main {

    static final int SIZE = 10000000;
    static final int THREADCOUNT = 214; //Количество потоков
    /**
    *TODO при THREADCOUNT = 215;
    *Exception in thread "main" java.lang.IllegalArgumentException: 9953488 > -9976592
	*at java.util.Arrays.copyOfRange(Arrays.java:3699)
	*at Xokyopo.HW_5.MagicMathematics.getArraysForTreads(MagicMathematics.java:89)
	*at Xokyopo.HW_5.MagicMathematics.getTimeForCalculatingWithTread(MagicMathematics.java:66)
	*at Xokyopo.HW_5.Main.main(Main.java:65)
     */
    static float[] arr = new float[SIZE];

    public static void main(String[] args) {
	// write your code here
        initializeArray(); //инициализируем массив единицами
        Timer timer = new Timer();
        MagicMathematics magicMathematics = new MagicMathematics(arr);

        runWithoutThreads(magicMathematics, timer);

        System.out.println("");

        runWithThreads(magicMathematics, timer);

    }

    public static void initializeArray () {
        for (int i = 0; i < SIZE; i++ ) {
            arr[i] = 1;
        }
    }

    //TODO Для удобства чтения (однопоток)
    public static void runWithoutThreads(MagicMathematics magicMathematics, Timer timer) {
        System.out.println("Количество потоков 1");
        timer.timerStart();

        long timeWithoutTread = magicMathematics.getTimeForCalculatingWithoutTread(); // расчет в однопотоке

        long timedef = timer.timerStop();
        printingInfo(timeWithoutTread, timedef, 1);
    }

    //TODO Для удобства чтения (многопоток)
    public static void runWithThreads(MagicMathematics magicMathematics, Timer timer) {
        System.out.println("Количество потоков " + THREADCOUNT);
        timer.timerStart();

        long timeWithTread = magicMathematics.getTimeForCalculatingWithTread(THREADCOUNT); // расчет в многопотоке

        long timedef = timer.timerStop();
        printingInfo(timeWithTread, timedef, THREADCOUNT);
    }

    public static int findBestThreadCount(MagicMathematics _magicMathematics) {
        //TODO поиск наилучшего соотношеия потоков к скорости выпонения
        System.out.println("поиск наилучшего соотношеия потоков к скорости выпонения старт");
        long minTime = 99999;
        int bestThreadCount = 0;
        for (int i = 1; i < 215; i++) {
            long time = _magicMathematics.getTimeForCalculatingWithTread(i);
            if (time < minTime) {
                minTime = time;
                bestThreadCount = i;
            }
        }
        System.out.println("bestTime = \t" + minTime);
        System.out.println("bestThreadCount = \t" + bestThreadCount);
        System.out.println("поиск наилучшего соотношеия потоков к скорости выпонения конец");
        return bestThreadCount;
    }

    public static void printingInfo(long _timeWithTread, long _timedef, int _numberOfThreads) {
        String msg = "на выполнение %s в %s потоке(ах) вы затратили %s милесекунд(ы)\n";
        System.out.printf(msg, "", _numberOfThreads, _timeWithTread);
        System.out.printf(msg, "метода", _numberOfThreads, _timedef);
    }
}
