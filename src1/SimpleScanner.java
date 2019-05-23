package it.unimi.di.se.lab09;

import java.io.Reader;
import java.io.StringReader;
import java.util.Iterator;
import java.util.Scanner;

public class SimpleScanner implements Iterator<String> {

    Scanner scan=null;

    public SimpleScanner(Reader stringReader) {
        scan=new Scanner(stringReader);
    }

    @Override
    public boolean hasNext() {
        return scan.hasNext();
    }

    @Override
    public String next() {
        return scan.next();
    }
}
