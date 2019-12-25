import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/*
5. Прочитать содержимое файла в обратном порядке.
 */
public class Task5 {
    private final String currentCodePages = "windows-1251";

    public String reversFileReading(String fileName) {
        File file = new File(fileName);
        StringBuilder string = new StringBuilder();
        if(file.isFile() && file.canRead()) {
            try (RandomAccessFile in = new RandomAccessFile(fileName, "r")){
                byte[] arr =new byte[10000];
                int x;
                while ((x = in.read(arr)) != -1) {
                    string.append(new String(arr, 0, x, this.currentCodePages));
                }
                return string.reverse().toString();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.err.printf("\nфайл %s %s\n", fileName, (file.isFile()) ? "не существует" : "не доступен для чтения");
        }
        return null;
    }

    //тут буду читать с последнего символа
    public String reversFileReading2(String fileName) {
        File file = new File(fileName);
        StringBuilder string = new StringBuilder();
        if(file.isFile() && file.canRead()) {
            try (RandomAccessFile in = new RandomAccessFile(fileName, "r")){
                for (long pset = in.length() - 2; pset >= 0; pset--) {
                    in.seek(pset);
                    byte[] b = {(byte) in.read()};
                    string.append(new String(b, 0,b.length,this.currentCodePages));
                }
                return string.toString();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.err.printf("\nфайл %s %s\n", fileName, (file.isFile()) ? "не существует" : "не доступен для чтения");
        }
        return null;
    }

    public void printCurrentCodePages() {
        System.out.println("текущая кодировка : " + this.currentCodePages);
    }
}
