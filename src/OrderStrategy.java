package it.unimi.di.se.lab09;

import java.util.Comparator;
import java.util.Map;
import java.util.Set;

public interface OrderStrategy extends Comparator {
    public int compare(Map.Entry<String, Set<Integer>> o1, Map.Entry<String, Set<Integer>> o2);
}
