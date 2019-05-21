package it.unimi.di.se.lab09;

import java.util.Map;
import java.util.Set;

public class JustifiedFormatStrategy implements FormatStrategy {

    private String format;

    public JustifiedFormatStrategy(String format) {
        this.format = format;
    }

    @Override
    public void formatEntry(StringBuilder sb, Map.Entry<String, Set<Integer>> entry) {
        String formattedKey=String.format("%-10s", entry.getKey());
        sb.append(formattedKey)
                .append(entry.getValue())
                .append('\n');
    }
}
