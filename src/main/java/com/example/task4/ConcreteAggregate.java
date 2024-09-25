package com.example.task4;

public class ConcreteAggregate implements Aggregate {
    private String filetopic;

    public ConcreteAggregate(String filetopic) {
        this.filetopic = filetopic;
    }

    @Override
    public Iterator getIterator() {
        return new ImageIterator(filetopic);
    }

    @Override
    public boolean hasNext(int i) {
        return false;
    }

    @Override
    public Object next() {
        return null;
    }
}