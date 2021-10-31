package Model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;


public class Book {

    private String title;
    private String author;
    private String genre;
    private String isbn;
    private UUID uuid;
    private UUID borrowedBy;
    private LocalDateTime borrwedDate;


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

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getBorrowedBy() {
        return borrowedBy;
    }

    public void setBorrowedBy(UUID borrowedBy) {
        this.borrowedBy = borrowedBy;
    }

    public LocalDateTime getBorrwedDate() {
        return borrwedDate;
    }

    public void setBorrwedDate(LocalDateTime borrwedDate) {
        this.borrwedDate = borrwedDate;
    }

    public Book() {
    }

    public Book(String title, String author, String genre, String isbn, UUID uuid, UUID borrowedBy, LocalDateTime borrwedDate) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.isbn = isbn;
        this.uuid = uuid;
        this.borrowedBy = borrowedBy;
        this.borrwedDate = borrwedDate;
    }
}
