package io.github.p4ndaj.bit.preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;

/**
 * Created by P4ndaJ on 7/25/17.
 */

public class InternalPreferences {
    private static InternalPreferences instance;

    private final SharedPreferences sharedPreferences;

    private String DebugSession;
    private String LogcatDump;

    public InternalPreferences(Context context) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        DebugSession = "debug_session";
        LogcatDump = "logcat_dump";
    }

    public static InternalPreferences getInstance(@NonNull final Context context) {
        if (instance == null) {
            instance = new InternalPreferences(context.getApplicationContext());
        }

        return instance;
    }

    public boolean isDebugSession() {
        return sharedPreferences.getBoolean(DebugSession, false);
    }

    public void setIsDebugSession() {
        sharedPreferences.edit().putBoolean(DebugSession, true).apply();
    }

    public boolean getLogcatDump() {
        return sharedPreferences.getBoolean(LogcatDump, false);
    }

    public void setLogcatDump(boolean bool) {
        sharedPreferences.edit().putBoolean(LogcatDump, bool).apply();
    }
}
