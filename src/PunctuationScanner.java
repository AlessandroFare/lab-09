package it.unimi.di.sweng.lab08;

import java.io.Reader;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class PunctuationScanner implements Iterator {

    Scanner scan;
    private static Set<Character> punctuation=new HashSet<>();

    static {
        punctuation.add('.');
        punctuation.add(';');
        punctuation.add('?');
        punctuation.add(',');
    }

    public PunctuationScanner(Reader in) {
        scan = new Scanner(in);
    }


    @Override
    public boolean hasNext() {
        return scan.hasNext();
    }

    @Override
    public Object next() {
        StringBuilder sb=new StringBuilder();
        String tmp=scan.next();
        for(int i=0;i<tmp.length();i++) {
            char c=tmp.charAt(i);
            if(!punctuation.contains(c))
                sb.append(c);
        }
        return sb.toString();
    }
}
