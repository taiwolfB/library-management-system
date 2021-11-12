package org.team4.libraryManagement.validator;


import org.team4.libraryManagement.service.DialogService;

import java.util.List;
import java.util.regex.Pattern;

public class BookValidator implements Validator<List<String>>{


    private static final String TITLE_PATTERN = "^[A-za-z]+[\\s-]?[A-za-z]*[\\s-]?[A-za-z]*[\\s-]?[A-za-z]*[\\s-]?[A-za-z]*$";
    private static final String AUTHOR_PATTERN = "^[A-za-z]+[- ]?[A-za-z]*$";
    private static final String GENRE_PATTERN = "^[A-za-z]+[-]?[A-za-z]*$";
    private static final String ISBN_PATTERN  = "^(?:ISBN(?:-13)?:? )?(?=[0-9]{13}$|(?=(?:[0-9]+[- ]){4})[- 0-9]{17}$)97[89][- ]?[0-9]{1,5}[- ]?[0-9]+[- ]?[0-9]+[- ]?[0-9]$";

    @Override
    public boolean validate(List<String> paramsList) {
        Pattern pattern = Pattern.compile(TITLE_PATTERN);
        if (!pattern.matcher(paramsList.get(0)).matches()) {
            DialogService.get().openDialog("error","Invalid title");
            return false;

        }

        pattern = Pattern.compile(AUTHOR_PATTERN);
        if (!pattern.matcher(paramsList.get(1)).matches()) {
            DialogService.get().openDialog("error", "Invalid name of the Author.");
            return false;
        }

        pattern = Pattern.compile(GENRE_PATTERN);
        if (!pattern.matcher(paramsList.get(2)).matches()) {
            DialogService.get().openDialog("error", "Invalid genre name");
            return false;
        }

        pattern = Pattern.compile(ISBN_PATTERN);
        if (!pattern.matcher(paramsList.get(3)).matches()) {
            DialogService.get().openDialog("error", "Invalid ISBN.");
            return false;
        }

        return true;
    }

}
