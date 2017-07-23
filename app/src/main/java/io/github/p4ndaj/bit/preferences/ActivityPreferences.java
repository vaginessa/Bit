package io.github.p4ndaj.bit.preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;

/**
 * Created by P4ndaJ on 7/23/17.
 */

public class ActivityPreferences {
    private static ActivityPreferences instance;

    private final SharedPreferences sharedPreferences;

    public ActivityPreferences(Context context) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static ActivityPreferences getInstance(@NonNull final Context context) {
        if (instance == null) {
            instance = new ActivityPreferences(context.getApplicationContext());
        }

        return  instance;
    }

    public boolean isFirstRun() {
        return sharedPreferences.getBoolean("FIRST_RUN", true);
    }

    public void updateIsFirstRun() {
        sharedPreferences.edit().putBoolean("FIRST_RUN", true).apply();
    }

}
