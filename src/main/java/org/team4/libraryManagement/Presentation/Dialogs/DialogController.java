package org.team4.libraryManagement.Presentation.Dialogs;

import java.util.Random;

public abstract class DialogController {

    public abstract void initDialog(Object param);

    public String generateRandomNumericString() {

        int leftLimit = 48; // digit '0'
        int rightLimit = 57; // digit '9'
        int targetStringLength = 10;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        return  buffer.toString();
    }
}
