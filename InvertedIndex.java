package it.unimi.di.se.lab09;

import java.io.Reader;
import java.io.StringReader;
import java.util.*;

public class InvertedIndex {


    List<String> lines=new ArrayList<>();
    Map<String, Set<Integer>> index= new LinkedHashMap<>();
    FormatStrategy formatStrategy;
    private OrderStrategy orderStrategy;
    private FilterStrategy filterStrategy;

    public InvertedIndex setOrderStrategy(OrderStrategy orderStrategy) {
        this.orderStrategy=orderStrategy;
        return this;
    }

    public InvertedIndex setFormatStrategy(FormatStrategy formatStrategy) {
        this.formatStrategy = formatStrategy;
        return this;
    }

    public InvertedIndex setFilterStrategy(FilterStrategy filterStrategy) {
        this.filterStrategy=filterStrategy;
        return this;
    }

    public InvertedIndex(Reader input) {
        Scanner scan=new Scanner(input);
        while(scan.hasNextLine()) {
            lines.add(scan.nextLine());
        }
    }

    public String toString() {
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<lines.size();i++) {
            sb.append(i+": ")
                    .append(lines.get(i));
            if(i<lines.size()-1)
                sb.append('\n');
        }
        return sb.toString();
    }

    public void build() {
        for(int i=0;i<lines.size();i++) {
            Iterator<String> in;
            if(filterStrategy==null)
                in=new Scanner(lines.get(i));
            else
                in=filterStrategy.getScanner(lines.get(i));
            while(in.hasNext()) {
                String word=in.next();
                Set<Integer> docs=index.getOrDefault(word, new TreeSet<>());
                docs.add(i);
                index.putIfAbsent(word,docs);
            }
        }
    }

    public String output() {
        StringBuilder sb=new StringBuilder();
        List<Map.Entry<String, Set<Integer>>> orderedIndex=new ArrayList<>(index.entrySet());
        if(orderStrategy!=null)
            orderedIndex.sort(orderStrategy);
        for (Map.Entry<String, Set<Integer>> entry : orderedIndex) {
            formatStrategy.formatEntry(sb, entry);
        }
        return sb.toString();
    }
}
