package edu.algs.intOOP.util;

import edu.algs.intOOP.util.TrackableList;
import java.util.ArrayList;

// Обёртка вокруг стандартного ArrayList, позволяющая подсчитывать вызовы метода add
public class TrackingList<T> extends ArrayList<T> implements TrackableList<T> {
    private int addCount = 0;
    
    public boolean add(T element) {
        this.addCount++;
        return super.add(element);
    }
    
    public int countAddCalls() {
        return this.addCount;
    }
}
