package main.Comparator;

import java.util.Comparator;

public class ListComparator implements Comparator<char[]> {

    private int indexOfCharInArray;

    public ListComparator(int indexOfCharInArray) {
        this.indexOfCharInArray = indexOfCharInArray;
    }

    @Override
    public int compare(char[] o1, char[] o2) {
        if(o1[indexOfCharInArray] < o2[indexOfCharInArray]){
            return -1;
        }
        if(o1[indexOfCharInArray] > o2[indexOfCharInArray]){
            return 1;
        }
        return 0;
    }

}
