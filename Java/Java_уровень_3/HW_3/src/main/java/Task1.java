import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/*
1. Прочитать файл (около 50 байт) в байтовый массив и вывести этот массив в консоль;
 */

public class Task1 {
    private File file;
    private StringBuilder text = new StringBuilder();

    public String readFile(String fileName) {
        if (this.getTextInFile(fileName)) {
            return this.text.toString();
        }
        return "";
    }

    private boolean getTextInFile(String fileName) {
        this.file = new File(fileName);
        this.text.setLength(0); // очищаем текущее значение
        if (this.file.exists() && this.file.isFile()) {
            try (FileInputStream fileInputStream = new FileInputStream(fileName)){
                int x;
                while ((x = fileInputStream.read()) != -1) { // вот тут я его считал как байт
                    text.append((char) x); // тут я его записал в нашу переменную.
                }
                this.file = null;
                return true;
            } catch (IOException e){
                e.printStackTrace();
            }
        } else {
            System.err.println("файл с именем " + fileName + " не существует!");
        }
        return false;
    }

}
