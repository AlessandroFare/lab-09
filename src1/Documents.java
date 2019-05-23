package it.unimi.di.se.lab09;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Documents {
    List<String> frasi=new ArrayList<>();
    int index=0;

    public Documents(Iterator<String> in, int i) {
        index=i;
        while(in.hasNext()) {
            frasi.add(in.next());
        }
    }

    public String getWord(int i) {
        return frasi.get(i);
    }

    public int getSize() {
        return frasi.size();
    }
}
