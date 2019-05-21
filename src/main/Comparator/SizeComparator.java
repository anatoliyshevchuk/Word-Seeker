package main.Comparator;

import java.util.Comparator;

public class SizeComparator implements Comparator<char[]> {
    @Override
    public int compare(char[] o1, char[] o2) {
        if(o1.length < o2.length)
            return -1;
        if(o1.length > o2.length)
            return 1;
        return 0;
    }
}
