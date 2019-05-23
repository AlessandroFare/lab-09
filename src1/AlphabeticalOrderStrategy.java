package it.unimi.di.se.lab09;

import java.util.Map;
import java.util.Set;

public class AlphabeticalOrderStrategy implements OrderStrategy {
    @Override
    public int compare(Map.Entry<String, Set<Integer>> o1, Map.Entry<String, Set<Integer>> o2) {
        return  o1.getKey().compareTo(o2.getKey());
    }

}
