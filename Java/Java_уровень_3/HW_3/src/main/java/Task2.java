import java.io.*;
import java.util.ArrayList;

/*
2. Последовательно сшить 5 файлов в один (файлы примерно 100 байт). Может пригодиться следующая конструкция: ArrayList<InputStream> al = new ArrayList<>();
... Enumeration<InputStream> e = Collections.enumeration(al);
 */

public class Task2 {
    private String outputFile;

    public Task2(String outputFile) {
        this.outputFile = outputFile;
    }

    public void writeToFile(boolean append, String... filesNames) { //флаг говорит о том чт онекоторый файлы  в списке могут отсутсвовать
        try (BufferedWriter out = new BufferedWriter(new FileWriter(this.outputFile, append))) {

            for (String fileName : filesNames) {
                if (this.isFile(fileName) && this.isReadable(fileName)) {

                    try (BufferedReader in = new BufferedReader(new FileReader(fileName))) {
                        String text;
                        while ((text = in.readLine()) != null) {
                            out.write(text + "\n"); // читаем строку и пишем строку, а то получаем все в одну строчку, что не совсем коректно в нашем случае.
                        }
                    }

                } else {
                    System.err.println(String.format("файл %s %s", fileName, (this.isFile(fileName)) ? "не доступен для чтения" : "не существует"));
                }
            }

        } catch (IOException e ) {
            e.printStackTrace();
        }
    }

    public void writeToFile(String... filesNames) {
        this.writeToFile(false, filesNames);
    }

    private boolean isFile(String name) {
        File file = new File(name);
        return (file.exists() && file.isFile());
    }

    private boolean isReadable(String name) {
        File file = new File(name);
        return file.canRead();
    }

    private boolean isWriteable(String name) {
        File file = new File(name);
        return file.canWrite();
    }

}
