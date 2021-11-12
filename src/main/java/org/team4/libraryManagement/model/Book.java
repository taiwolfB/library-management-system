package org.team4.libraryManagement.model;

import java.time.LocalDateTime;
import java.sql.Date;
import java.util.UUID;


public class Book {

    private String title;
    private String author;
    private String genre;
    private String isbn;
    private String uuid;
    private String borrowedBy;
    private Date borrowedDate;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getBorrowedBy() {
        return borrowedBy;
    }

    public void setBorrowedBy(String borrowedBy) {
        this.borrowedBy = borrowedBy;
    }

    public Date getBorrowedDate() {
        return borrowedDate;
    }

    public void setBorrowedDate(Date borrwedDate) {
        this.borrowedDate = borrwedDate;
    }

    public Book(String title, String author, String genre, String isbn, String uuid, String borrowedBy, Date borrwedDate) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.isbn = isbn;
        this.uuid = uuid;
        this.borrowedBy = borrowedBy;
        this.borrowedDate = borrwedDate;
    }

    public Book(String title, String author, String genre, String isbn, String uuid, String borrowedBy) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.isbn = isbn;
        this.uuid = uuid;
        this.borrowedBy = borrowedBy;
    }

    public Book(String title, String author, String genre, String isbn, String uuid) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.isbn = isbn;
        this.uuid = uuid;
    }

    public Book(String title, String author, String genre, String isbn) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.isbn = isbn;
    }

    public Book(String title, String author, String genre) {
        this.title = title;
        this.author = author;
        this.genre = genre;
    }

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public Book(String uuid) {
        this.uuid = uuid;
    }

    public Book() {
    }
}
