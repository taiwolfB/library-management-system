package org.team4.libraryManagement.model;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;


public class Book {

    private String title;
    private String author;
    private String genre;
    private String isbn;
    private String uuid;
    private String borrowedBy;
    private Date borrwedDate;


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

    public Date getBorrwedDate() {
        return borrwedDate;
    }

    public void setBorrwedDate(Date borrwedDate) {
        this.borrwedDate = borrwedDate;
    }

    public Book(String title, String author, String genre, String isbn, String uuid, String borrowedBy, Date borrwedDate) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.isbn = isbn;
        this.uuid = uuid;
        this.borrowedBy = borrowedBy;
        this.borrwedDate = borrwedDate;
    }
}
