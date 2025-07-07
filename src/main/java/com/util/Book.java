package com.util;

/**
 * Book
 */
public class Book {

    public int id;
    public String title;
    public float price;


    public Book(int id, String title, float price) {
        this.id = id;
        this.title = title;
        this.price = price;
    }

    @Override
    public String toString() {
        return id + " " + title + " " + price;
    }
}
