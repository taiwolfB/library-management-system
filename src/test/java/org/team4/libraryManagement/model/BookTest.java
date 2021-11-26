package org.team4.libraryManagement.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.team4.libraryManagement.Presentation.BooksController;
import org.team4.libraryManagement.Presentation.Dialogs.BookFormDialogController;
import org.team4.libraryManagement.Presentation.Dialogs.StudentFormController;
import org.team4.libraryManagement.dao.GeneralDAO;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void isOverdueSuccessful()
    {
        Date dateTest = new Date(0);
        BooksController isOverdueBooksController = new BooksController();
        assertEquals(true,isOverdueBooksController.isOverdue(dateTest));
    }

    @Test
    void isOverdueUnsuccessful()
    {
        Date dateTest = new Date();
        BooksController isOverdueBooksController = new BooksController();
        assertEquals(false,isOverdueBooksController.isOverdue(dateTest));
        dateTest = null;
        assertEquals(false,isOverdueBooksController.isOverdue(dateTest));
    }

    @Test
    void lendBookSuccessful()
    {
        Book selectedBook = new Book("TestTitle","TestAuther");
        selectedBook.setUuid("1234");
        selectedBook.setBorrowedBy("3585609706");
        java.sql.Date date = new java.sql.Date(System.currentTimeMillis());
        selectedBook.setBorrowedDate(date);
        Student student = new GeneralDAO<>(Student.class).selectById("3585609706");
        if(student.isBlacklisted()) {
            //Blacklisted student
        }else {
            new GeneralDAO<>(Book.class).updateBook(selectedBook);
        }

        assertEquals(selectedBook,new GeneralDAO<>(Book.class).selectById("1234"));
    }

    @Test
    void lendBookUnsuccessful()
    {
        Book selectedBook = new Book("TestTitle","TestAuther");
        selectedBook.setUuid("1235");
        selectedBook.setBorrowedBy("3585609706");
        java.sql.Date date = new java.sql.Date(System.currentTimeMillis());
        selectedBook.setBorrowedDate(date);
        Student student = new GeneralDAO<>(Student.class).selectById("3585609706");
        if(student.isBlacklisted()) {
            //Blacklisted student
        }else {
            new GeneralDAO<>(Book.class).updateBook(selectedBook);
        }

        assertEquals(null,new GeneralDAO<>(Book.class).selectById("1235"));
    }


    @Test
    void updateBookSuccessful()
    {
        BookFormDialogController bookFormDialogController = new BookFormDialogController();
        bookFormDialogController.setSuccessfulTest();
        bookFormDialogController.updateBook();
        assertEquals("UpdateTestTitle",new GeneralDAO<>(Book.class).selectById("1234").getTitle());
    }

    @Test
    void updateBookUnsuccessful()
    {
        BookFormDialogController bookFormDialogController = new BookFormDialogController();
        bookFormDialogController.setUnsuccessfulTest();
        bookFormDialogController.updateBook();
        assertEquals("UpdateTestTitle",new GeneralDAO<>(Book.class).selectById("1234").getTitle());
    }

}
