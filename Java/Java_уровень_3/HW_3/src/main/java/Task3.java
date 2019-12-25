import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.Charset;
import java.util.Scanner;

/*
3. Написать консольное приложение, которое умеет постранично читать текстовые файлы (размером > 10 mb). Вводим страницу
(за страницу можно принять 1800 символов), программа выводит ее в консоль. Контролируем время выполнения: программа не
должна загружаться дольше 10 секунд, а чтение – занимать свыше 5 секунд.
 */
public class Task3 {
    private int currentPage = 1;
    private int maxPages = 0;
    private File file;
    private StringBuilder pages = new StringBuilder(); // задействуем
    private final int maxByteCount = 1800;
    private boolean work = true;
    private String codePages = "UTF-8"; // "Windows-1251"


    public void runAPP() {
        do {
            if (this.file == null) {
                this.openFileDialog();
                if (file == null) this.work = false;
            } else {
                this.showMainWindows();
                this.executeCommand(this.getUserInput());
            }
        }while (this.isWork());
    }

    public boolean isWork() {
        return this.work;
    }

    private void readPageFromFile() { //читаем файл постранично
        try (RandomAccessFile in = new RandomAccessFile(this.file, "r")){
            byte[] arr = new byte[this.maxByteCount];
            this.pages.setLength(0);
            this.setMaxPages((int)(in.length()/this.maxByteCount));
            in.seek((this.currentPage - 1) * this.maxByteCount);
            int x = in.read(arr);
            this.pages.append(new String(arr, 0, x , this.codePages));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void executeCommand(bookReaderFunctionKey key) {
        //TODO реализация выполнения команд
        if (key == bookReaderFunctionKey.QUIT) {
            this.work = false;
        } else if (key == bookReaderFunctionKey.OPENFILE) {
            this.openFileDialog();
        } else if (key == bookReaderFunctionKey.NEXT) {
            this.currentPage = (this.currentPage != this.maxPages)? this.currentPage + 1 : this.currentPage;
        } else if (key == bookReaderFunctionKey.BACK) {
            this.currentPage = (this.currentPage > 1)? this.currentPage - 1 : this.currentPage;
        } else if (key == bookReaderFunctionKey.CODEPAGE) {
            this.changeCodePageDialog();
        } else {
            System.err.println("\nданный функционал по каким то причинам еще не реализован\n");
        }
    }

    private bookReaderFunctionKey getUserInput() {
        this.printAppInfo();
        while (true) {
            String userAnswer = this.getInput();
            bookReaderFunctionKey command = bookReaderFunctionKey.getElementBySymbol(userAnswer);
            if (command !=null) {
                return command;
            }
        }
    }

    private void printAppInfo() {
        for (bookReaderFunctionKey element: bookReaderFunctionKey.values()) {
            if (!(element == bookReaderFunctionKey.BACK && this.currentPage == 1 || element == bookReaderFunctionKey.NEXT && this.currentPage == this.maxPages)) {
                System.out.println(element.info());
            }
        }
    }

    private void printBookPage() {
        System.out.printf("страница №%s из %s\n\n%s\n\n", this.currentPage, this.maxPages, this.pages.toString());
    }

    private void printFileInfo() {
        System.out.printf("открыт файл %s\n", this.file.getName());
    }

    //реализация главного окна программы
    private void showMainWindows() {
        //печатаем все части сообщений
        this.printFileInfo();
        //обновляем страницу и засекаем время ее открытия
        long start = System.currentTimeMillis();
        this.readPageFromFile();
        System.out.println("открытие страницы заняло : " + (System.currentTimeMillis() - start));

        this.printBookPage();
    }

    //диалог открытия файла
    private void openFileDialog() {
        while (true) {
            System.out.printf("Введите имя файла который хотите открыть (%s): ", bookReaderFunctionKey.QUIT.info());
            String fileName = this.getInput();

            if (fileName.length() == bookReaderFunctionKey.QUIT.getSymbol().length() && fileName.contains(bookReaderFunctionKey.QUIT.getSymbol())) {
                break;
            } else {
                File openFile = new File(fileName);
                if (!openFile.isFile()) {
                    System.err.printf("\nфайл с именем %s не существует\n", fileName);
                } else if (!openFile.canRead()) {
                    System.err.printf("\nфайл с именем %s нельля прочесть\n", fileName);
                } else {
                    this.setCurrentPage(1);
                    this.file = openFile;
                    break;
                }
            }

        }
    }

    private void setMaxPages(int number) {
        this.maxPages = number;
    }

    private void setCurrentPage(int number) {
        this.currentPage = number;
    }

    private void setCodePages(String newCodePages) {
        this.codePages = newCodePages;
    }

    private String getInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    private void changeCodePageDialog() {
        while (true) {
            System.out.printf("Введите новую кодировку файла (%s): ", bookReaderFunctionKey.QUIT.info());
            String codePages = this.getInput();

            if (!(codePages.length() == bookReaderFunctionKey.QUIT.getSymbol().length() && codePages.contains(bookReaderFunctionKey.QUIT.getSymbol()))) {
                if (Charset.isSupported(codePages)) {
                    this.setCodePages(codePages);
                    break;
                }
                else {
                    System.err.printf("\nданная кодировка не поддерживается (%s)\n", codePages);
                }
            }
        }
    }
}



//перечисление для управление работой книги
enum bookReaderFunctionKey {
    BACK("<", "предыдущая страница"), NEXT(">", "следующая страница"), QUIT("\\q", "выход"),
    OPENFILE("\\o", "открыть файл"), CODEPAGE("\\cp", "сменить кодировку");
    private final String symbol;
    private final String description;

    bookReaderFunctionKey(String symbol, String description) {
        this.symbol = symbol;
        this.description = description;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getDescription() {
        return description;
    }

    public String info() {
        return (this.getSymbol() + " - " + getDescription());
    }

    public static bookReaderFunctionKey getElementBySymbol(String _symbol) {
        for (bookReaderFunctionKey key: bookReaderFunctionKey.values()) {
            if(key.getSymbol().equals(_symbol)) return key;
        }
        return null;
    }
}
