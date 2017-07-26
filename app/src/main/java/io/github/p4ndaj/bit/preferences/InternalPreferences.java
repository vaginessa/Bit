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
    private String DebugSavedPasswordAdapter;

    public InternalPreferences(Context context) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        DebugSession = "debug_session";
        LogcatDump = "logcat_dump";
        DebugSavedPasswordAdapter = "debug_saved_password_adapter";
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

    public boolean isDebugAdapterSavedPasswordSetted() {
        return sharedPreferences.getBoolean(DebugSavedPasswordAdapter, false);
    }

    public void setDebugSavedPasswordAdapter(boolean bool) {
        sharedPreferences.edit().putBoolean(DebugSavedPasswordAdapter, bool).apply();
    }
}
