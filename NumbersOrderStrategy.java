package it.unimi.di.se.lab09;

import java.util.Map;
import java.util.Set;

public class NumbersOrderStrategy implements OrderStrategy {
    @Override
    public int compare(Map.Entry<String, Set<Integer>> o1, Map.Entry<String, Set<Integer>> o2) {
        if(o2.getValue().size() == o1.getValue().size()) {
            return o1.getKey().compareTo(o2.getKey());
        }
        return Integer.compare(o2.getValue().size(),o1.getValue().size());
    }

    @Override
    public int compare(Object o, Object t1) {
        return 0;
    }
}
