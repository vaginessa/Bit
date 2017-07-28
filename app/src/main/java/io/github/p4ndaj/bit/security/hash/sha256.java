package io.github.p4ndaj.bit.security.hash;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * Created by P4ndaJ on 7/28/17.
 */

public class sha256 {
    public static byte[] sha256Maker(String string) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(string.getBytes(StandardCharsets.UTF_8));
        // return to the hash
        return hash;
    }

    public static boolean twoHashAreEquals(byte[] hash1, byte[] hash2) {
        if (Arrays.equals(hash1, hash2)) {
            return true;
        } else {
            return false;
        }
    }
}
