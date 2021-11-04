package org.team4.libraryManagement.validator;

import org.team4.libraryManagement.model.Book;

public class BookValidator implements Validator<Book>{
    @Override
    public boolean validate(Book book) {
        return false;
    }
}
