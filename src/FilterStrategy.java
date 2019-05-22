package it.unimi.di.se.lab09;

import java.util.Iterator;

public interface FilterStrategy {

    Iterator<String> getScanner(String s);
}
