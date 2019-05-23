package it.unimi.di.se.lab09;

import javax.swing.text.Document;
import java.io.Reader;
import java.io.StringReader;
import java.util.*;

public class InvertedIndex {

    List<Documents> docs=new ArrayList<Documents>();
    Map<String, Set<Integer>> index=new LinkedHashMap<>();
    FormatStrategy formatStrategy;
    OrderStrategy orderStrategy;



    public InvertedIndex setOrderStrategy(OrderStrategy orderStrategy) {
        this.orderStrategy=orderStrategy;
        return this;
    }
    public InvertedIndex setFormatStrategy(FormatStrategy formatStrategy) {
        this.formatStrategy=formatStrategy;
        return this;
    }


    public InvertedIndex(Reader input) {
        Scanner in=new Scanner(input);
        int i=0;
        while(in.hasNextLine()) {
            Iterator<String> s=new SimpleScanner(new StringReader(in.nextLine()));
            docs.add(new Documents(s,i++));
        }
    }


    public void build() {
        for(int i=0;i<docs.size();i++) {
            for(int j=0;j<docs.get(i).getSize();j++) {
                String word=docs.get(i).getWord(j);
                Set<Integer> line=index.getOrDefault(word, new TreeSet<>());
                line.add(i);
                index.putIfAbsent(word,line);
            }
        }

    }
    public String toString() {
        StringBuilder sb=new StringBuilder();
        int i=0;
        List<Map.Entry<String, Set<Integer>>> orderedIndex=new ArrayList<>(index.entrySet());
        if(orderStrategy!=null)
            orderedIndex.sort(orderStrategy);
        for ( Map.Entry<String, Set<Integer>> entry: orderedIndex) {
            sb.append("[")
                    .append(i++)
                    .append("] ");
            formatStrategy.formatEntry(sb, entry);
        }
        return sb.toString();
    }
}
