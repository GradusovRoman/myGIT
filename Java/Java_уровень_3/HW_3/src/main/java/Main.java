


/*
+1. Прочитать файл (около 50 байт) в байтовый массив и вывести этот массив в консоль;
+2. Последовательно сшить 5 файлов в один (файлы примерно 100 байт). Может пригодиться следующая конструкция: ArrayList<InputStream> al = new ArrayList<>(); ... Enumeration<InputStream> e = Collections.enumeration(al);
+3. Написать консольное приложение, которое умеет постранично читать текстовые файлы (размером > 10 mb). Вводим страницу (за страницу можно принять 1800 символов), программа выводит ее в консоль. Контролируем время выполнения: программа не должна загружаться дольше 10 секунд, а чтение – занимать свыше 5 секунд.
+4. Сделать клиен-серверное приложение. Передать по сети сеарилизованный объект.
+5. Прочитать содержимое файла в обратном порядке.
*/

public class Main {
    public static void main(String[] args) {
       runFirstTask();
//        runSecondTask();
//        runThirdTask();
//        runFifthTask();
//        runForthTask();
    }

    public static void runFirstTask() {
        System.out.println("1. Прочитать файл (около 50 байт) в байтовый массив и вывести этот массив в консоль;");
        Task1 task1 = new Task1();
        System.out.println(task1.readFile("text.txt"));
        //System.out.println(task1.readFile("src"));
    }

    public static void runSecondTask() {
        System.out.println("2. Последовательно сшить 5 файлов в один (файлы примерно 100 байт). Может пригодиться" +
                "следующая конструкция: ArrayList<InputStream> al = new ArrayList<>(); ... Enumeration<InputStream> e = Collections.enumeration(al);");
        Task2 task2 = new Task2("out.txt");
        task2.writeToFile(true, "text1.txt","text2.txt","text3.txt" ); // дописывает в конец файла
//        task2.writeToFile( "text1.txt","text2.txt","text3.txt"); // просто перезаписать файл
    }

    public static void runThirdTask() {
        System.out.println("3. Написать консольное приложение, которое умеет постранично читать текстовые файлы (размером > 10 mb). " +
                "Вводим страницу (за страницу можно принять 1800 символов), программа выводит ее в консоль. Контролируем время выполнения: " +
                "программа не должна загружаться дольше 10 секунд, а чтение – занимать свыше 5 секунд.");
        Task3 task3 = new Task3();
        task3.runAPP();
    }

    public static void runFifthTask() {
        System.out.println("5. Прочитать содержимое файла в обратном порядке.");
        Task5 task5 = new Task5();
        task5.printCurrentCodePages();
        //читаем обычно потом переворачиваем
        //System.out.println(task5.reversFileReading("D:\\Book\\ССС\\q.txt"));
        //читаем в обратном порядке
        System.out.println(task5.reversFileReading2("D:\\Book\\ССС\\q.txt"));
    }

    public static void runForthTask() {
        System.out.println("4. Сделать клиен-серверное приложение. Передать по сети сеарилизованный объект.");
        Task4 task4 = new Task4();
    }
}
