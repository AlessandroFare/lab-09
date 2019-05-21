package it.unimi.di.se.lab09;

import java.util.Map;
import java.util.Set;

public interface FormatStrategy {
    void formatEntry(StringBuilder sb, Map.Entry<String, Set<Integer>> entry);
}
