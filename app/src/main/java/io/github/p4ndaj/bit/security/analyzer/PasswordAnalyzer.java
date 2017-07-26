package io.github.p4ndaj.bit.security.analyzer;

/**
 * Created by P4ndaJ on 7/26/17.
 */

public class PasswordAnalyzer {

    /**
     * Security level: 0;
     * "Password is very bad"
     * <p>
     * Security level: 1
     * "Password is bad"
     * <p>
     * Security level: 2
     * "Password is good"
     * <p>
     * Security level: 3
     * "Password is very good"
     * <p>
     * This is based only on length because usually for hack password
     * is used the "Social Engineering" ... otherwise is used the brute-force Attack
     */

    public static int getSecurityLevel(String Password) {
        int security_level = 0;

        /* checking length */
        if (Password.length() == 0) {
            security_level = -1;
        } else if (Password.length() <= 4) {
            security_level = 0;
        } else if (Password.length() <= 8 && Password.length() > 4) {
            security_level = 1;
        } else if (Password.length() <= 16 && Password.length() > 8) {
            security_level = 2;
        } else if (Password.length() > 16) {
            security_level = 3;
        }

        return security_level;
    }
}
