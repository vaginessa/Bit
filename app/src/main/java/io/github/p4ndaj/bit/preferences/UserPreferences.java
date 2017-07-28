package io.github.p4ndaj.bit.preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;

import java.util.Arrays;

/**
 * Created by P4ndaJ on 7/28/17.
 */

public class UserPreferences {
    private static UserPreferences instance;

    private final SharedPreferences sharedPreferences;

    private String CurrentUser;

    public UserPreferences(Context context) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        CurrentUser = "current_user";
    }

    public static UserPreferences getInstance(@NonNull final Context context) {
        if (instance == null) {
            instance = new UserPreferences(context.getApplicationContext());
        }

        return instance;
    }

    public String getPassword(String Email) {
        return sharedPreferences.getString(Email, "NOT_FOUND");
    }

    public void setPassword(String Email, String Password) {
        sharedPreferences.edit().putString(Email, Password).apply();
    }

    public void setCurrentUser(String Email) {
        sharedPreferences.edit().putString(CurrentUser, Email).apply();
    }

    public String getCurrentUser() {
        return sharedPreferences.getString(CurrentUser, "NOT_FOUND");
    }

}
