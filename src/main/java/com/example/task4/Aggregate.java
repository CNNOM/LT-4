package com.example.task4;

public interface Aggregate {
    public Iterator getIterator();

    boolean hasNext(int i);

    Object next();
}
