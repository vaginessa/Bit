package io.github.p4ndaj.bit.utils;

import android.content.Context;
import android.widget.Toast;

import io.github.p4ndaj.bit.R;
import io.github.p4ndaj.bit.preferences.InternalPreferences;

/**
 * Created by P4ndaJ on 7/25/17.
 */

public class DebugUtils {
    /* this method is an int because in the future i can add more user type */
    public static int isDebugUser(String Email, String Password, Context context) {
        if (Email.equals("Debug") && Password.equals("Debug")) {
            Toast.makeText(context, R.string.debug_recommendation, Toast.LENGTH_SHORT).show();
            isDebugSession(context);
            return 1;
        } else {
            return 0;
        }
    }

    private static void isDebugSession(Context context) {
        InternalPreferences.getInstance(context.getApplicationContext()).setIsDebugSession();
        InternalPreferences.getInstance(context.getApplicationContext()).setLogcatDump(true);
    }
}
