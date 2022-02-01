package com.example.pockemonapp.model;

import java.util.ArrayList;



public class PockemonResponce {
    private  int count;
    private String next , previous;
    private ArrayList<Pockemon> results;

    public PockemonResponce(int count, String next, String previous, ArrayList<Pockemon> results) {
        this.count = count;
        this.next = next;
        this.previous = previous;
        this.results = results;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public ArrayList<Pockemon> getResults() {
        return results;
    }

    public void setResults(ArrayList<Pockemon> results) {
        this.results = results;
    }
}
