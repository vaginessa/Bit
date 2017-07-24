package io.github.p4ndaj.bit.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by P4ndaJ on 7/24/17.
 */

public class StringUtils {
    public static boolean isAnEmail(String Email) {
        boolean isAnEmail;

        Pattern pattern = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");

        Matcher mat = pattern.matcher(Email);

        if (mat.matches()) {
            isAnEmail = true;
        } else {
            isAnEmail = false;
        }

        return isAnEmail;
    }
}
