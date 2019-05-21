package main;

import main.Comparator.ListComparator;
import main.Comparator.SizeComparator;

import java.util.*;

class Seeker {

    private List<char[]> AllWordContainer;
    private Set<String> matchWords = new TreeSet<>();
    private int charOrdinal;
    private char[] letters;
    private boolean modified=false;


    Seeker(List<char[]> wc, char[] chars) {
        AllWordContainer = wc;
        this.letters = chars;
    }

    Set<String> deepSeek(){
        charOrdinal=0;
        deepSeek(AllWordContainer);
        return matchWords;
    }
//TODO: normal naming of variables
    private void deepSeek(List<char[]> words)
    {
        List<char[]> accurateWordContainer = new ArrayList<>(10000);
        for(int i=0;i<=letters.length-1;i++){
            int first,last;
            int left = 0;
            int right = words.size()-1;
            /*recursively binary search on vocabulary and narrow down it
            left only words that starts with seeking letters
            Example:
            if we have List with words:
                ...
                альбом
                декада
                дело
                грань
                градина
                гуща
                зарево
                нежность
                общение
                огонь
                ...
             And input letters: г, о, о, ь, н
             After first pass (when charOrdinal = 0) and narrow down left only words: альбом, грань, градина, гуща, нежность, общение, огонь;
             */
            first = firstIndex(words,left,right,i,charOrdinal);
            last = lastIndex(words,left,right,i,charOrdinal);
            if(first != -1 && last != -1) {
                accurateWordContainer.addAll(words.subList(first, last+1));
                modified=true;
            }
        }
        //sort by size and check only first words for a match
        if(this.charOrdinal>0)
            accurateWordContainer.sort(new SizeComparator());
        checkMatchedWords(accurateWordContainer);
        //increment which letter seek in all words
        /*
        if charOrdinal = 0
          [о,г,о,н,ь]
           ^
         */
        this.charOrdinal++;
        accurateWordContainer.sort(new ListComparator(charOrdinal));
        if(modified){
            modified=false;
            deepSeek(accurateWordContainer);
        }
    }

    private void checkMatchedWords(List<char[]> list)
    {
        while(true)
        {
            if (list.size()>0 && list.get(0).length-1==charOrdinal){
                matchWords.add(String.valueOf(list.get(0)));
                list.remove(0);
            } else {
                break;
            }
        }
    }

    private int firstIndex(List<char[]> words,int left,int right,int indexLettersForSeek, int seekCharInList){
        char c = letters[indexLettersForSeek];
        int foundedIndex=-1;
        if(right>=left){
            int mid = left + (right-left)/2;
            if((mid==0 || (words.get(mid-1).length-1>=seekCharInList && c > words.get(mid-1)[seekCharInList])) && words.get(mid)[seekCharInList]==c)
            {
                foundedIndex = mid;
            }else if (c>words.get(mid)[seekCharInList]){
                foundedIndex = firstIndex(words, (mid+1), right, indexLettersForSeek,seekCharInList);
            }else
                foundedIndex = firstIndex(words, left, (mid-1), indexLettersForSeek,seekCharInList);
        }
        return foundedIndex;
    }

    private int lastIndex(List<char[]> words,int left,int right,int indexLetters,int seekCharInList){
        char c = letters[indexLetters];
        int foundedIndex=-1;
        if(right>=left){
            int mid = left + (right-left)/2;
            if((mid==words.size()-1 || c < words.get(mid+1)[seekCharInList]) && words.get(mid)[seekCharInList]==c)
            {
                foundedIndex = mid;
            }else if (c < words.get(mid)[seekCharInList]){
                foundedIndex = lastIndex(words, left, (mid-1), indexLetters,seekCharInList);
            }else
                foundedIndex = lastIndex(words, (mid+1), right, indexLetters,seekCharInList);
        }
        return foundedIndex;
    }

}
