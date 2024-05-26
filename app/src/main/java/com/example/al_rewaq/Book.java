package com.example.al_rewaq;

public class Book {
    private String bookName;
    private String imageUrl;

    public Book(String bookName, String imageUrl) {
        this.bookName = bookName;
        this.imageUrl = imageUrl;
    }

    public String getBookName() {
        return bookName;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
