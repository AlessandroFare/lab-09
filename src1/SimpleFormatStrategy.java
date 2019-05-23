package it.unimi.di.se.lab09;

import java.util.Map;
import java.util.Set;

public class SimpleFormatStrategy implements FormatStrategy {
    @Override
    public void formatEntry(StringBuilder sb, Map.Entry<String, Set<Integer>> entry) {
        sb.append(entry.getKey())
                .append(entry.getValue())
                .append('\n');
    }
}
