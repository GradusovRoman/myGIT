package xokyopo.hw8.tree;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Parser {
    private File file;

    public Parser(File file) {
        this.file = file;
    }

    public List<Integer[]> getParsingData() {
        List<Integer[]> list = new ArrayList<>();

        try (BufferedReader fileReader = new BufferedReader(new FileReader(this.file))) {
            String line;
            while ((line = fileReader.readLine()) != null) {
                List<String> parserList = new ArrayList<>(Arrays.asList(line.split(" ")));

                for (int i = 0; i < parserList.size(); i++) {
                    if (parserList.get(i).equals("")) parserList.remove(i);
                }

                if (parserList.size() > 0) {
                    Integer[] export = new Integer[parserList.size()];
                    try {
                        for (int i = 0; i < parserList.size(); i++) {
                            export[i] = Integer.parseInt(parserList.get(i));
                        }
                        list.add(export);
                    } catch (Exception e) { }
                }

            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return list;
    }
}
