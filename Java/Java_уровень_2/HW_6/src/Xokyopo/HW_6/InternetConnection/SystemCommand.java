package Xokyopo.HW_6.InternetConnection;

public enum SystemCommand {
    EXIT("\\exit", "используйте для выхода"), PRIVATE("\\P", "используйте для отправки личного сообщения пользователю");

    String text;
    String description;

    SystemCommand(String text, String description) {
        this.text = text;
        this.description = description;
    }

    public String getText() {
        return text;
    }

    public String getDescription() {
        return description;
    }

    public void printInfo() {
        System.out.println(this.getInfo());
    }

    public String getInfo() {
        return (this.text + " - " + this.description);
    }

}
