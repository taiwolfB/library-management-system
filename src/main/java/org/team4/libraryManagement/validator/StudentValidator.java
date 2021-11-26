package org.team4.libraryManagement.validator;


import org.team4.libraryManagement.service.DialogService;

import java.util.List;
import java.util.regex.Pattern;

public class StudentValidator implements Validator<List<String>>{

    private static final String EMAIL_PATTERN ="^[A-Za-z0-9+_.-]+@[A-za-z]+\\.[A-za-z]+$";
    private static final String FIRST_NAME_PATTERN = "^[A-za-z]+[\\s-]?[A-za-z]*[\\s-]?[A-za-z]*[\\s-]?[A-za-z]*[\\s-]?[A-za-z]*$";
    private static final String LAST_NAME_PATTERN = "^[A-za-z]+[-]?[A-za-z]*$";

    @Override
    public boolean validate(List<String> paramsList) {
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        if (!pattern.matcher(paramsList.get(0)).matches()) {
            DialogService.get().openDialog("error","Invalid E-mail");
            return false;

        }

        pattern = Pattern.compile(FIRST_NAME_PATTERN);
        if (!pattern.matcher(paramsList.get(1)).matches()) {
            DialogService.get().openDialog("error", "Invalid First name");
            return false;
        }

        pattern = Pattern.compile(LAST_NAME_PATTERN);
        if (!pattern.matcher(paramsList.get(2)).matches()) {
            DialogService.get().openDialog("error", "Invalid Last name");
            return false;
        }

        return true;

    }
}
