package it.unimi.di.se.lab09;

import java.util.Iterator;
import java.util.Scanner;

public class StopWordFilterStrategy implements FilterStrategy, Iterator<String> {

    private Scanner scanner;
    private StringBuilder sb=new StringBuilder();

    public StopWordFilterStrategy addStopWord(String word) {
        sb.append('|').append(word);
        return this;
    }

    @Override
    public Iterator<String> getScanner(String s) {
        scanner = new Scanner(s);
        return this;
    }

    @Override
    public boolean hasNext() {
        while(scanner.hasNext(sb.toString()))
            scanner.next();
        return scanner.hasNext();
    }

    @Override
    public String next() {
        hasNext();
        return scanner.next();
    }
}
