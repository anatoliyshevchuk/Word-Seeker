package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class WordsContainer {
    private List<char[]> wordStructure = new ArrayList<>();
    private File f = null;

    WordsContainer(File file)  {
        //TODO: check file and throw EX
        if(file!=null && file.isFile())
            this.f=file;
    }

    List<char[]> getWordStructure()
    {
        Scanner scanner = null;
        try {
            scanner = new Scanner(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (scanner.hasNextLine()) {
            wordStructure.add(scanner.next().toCharArray());
        }
        return wordStructure;
    }
}
