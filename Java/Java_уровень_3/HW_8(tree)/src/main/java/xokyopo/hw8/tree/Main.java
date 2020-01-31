package xokyopo.hw8.tree;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        Parser parser = new Parser(new File(new File("").getAbsolutePath() + "\\target\\classes\\data.txt"));

//TODO D:\GIT\Java\Java_%d1%83%d1%80%d0%be%d0%b2%d0%b5%d0%bd%d1%8c_3\HW_8(tree)\target\classes\data.txt (Системе не удается найти указанный путь)
//        Parser parser = new Parser(new File(Object.class.getResource("/data.txt").getFile()));
//        System.out.println(new Main().getClass().getResource("/data.txt").getPath());

        Tree tree = new Tree(parser.getParsingData());

        tree.getMetaObjectList().forEach(element->element.printInfo());
    }
}
