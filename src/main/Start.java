package main;

import java.io.File;
import java.util.List;
import java.util.Scanner;
import java.util.Set;


public class Start {

    public static void main(String[] args) {

        WordsContainer wc = new WordsContainer(new File("word_rus"));
        List<char[]> wordStructure = wc.getWordStructure();

        Scanner scanner = new Scanner(System.in);
        while(true)
        {
            System.out.println("Enter string (example арутчды):");
            String s = scanner.nextLine();
            Seeker seeker = new Seeker(wordStructure,s.toCharArray());

            Long time = System.currentTimeMillis();

            Set<String> foundedWords = seeker.deepSeek();

            Long time2 = System.currentTimeMillis()-time;
            System.out.println("Search time:" +time2+"ms.");
            System.out.println("Words found:" +foundedWords.size());
            for (String c:foundedWords)
            {
                System.out.println(c);
            }
        }

    }
}