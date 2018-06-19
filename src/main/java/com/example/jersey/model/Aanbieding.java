package com.example.jersey.model;

import java.util.Date;

public class Aanbieding {

    private Date begin;
    private Date end;
    private float price;
    private String text;

    public Aanbieding(Date begin, Date end, float price, String text) {
        this.begin = begin;
        this.end = end;
        this.price = price;
        this.text = text;
    }

    public Date getBegin() {
        return begin;
    }

    public Date getEnd() {
        return end;
    }

    public float getPrice() {
        return price;
    }

    public String getText() {
        return text;
    }
}
