package io.github.p4ndaj.bit.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import io.github.p4ndaj.bit.lists.Password;
import io.github.p4ndaj.bit.preferences.UserPreferences;


/**
 * Created by P4ndaJ on 7/27/17.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    private static String DATABASE_NAME;

    private static final int DATABASE_VERSION = 1;

    // Passwords table name
    private static final String TABLE_PASSWORDS = "passwords";

    // Passwords table columns name
    private static final String KEY_TITLE = "title";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_TAG = "tag";
    private static final String KEY_ICON = "icon";
    private static final String KEY_ID = "id";
    private static final String KEY_FAVORITE = "favorite";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        DATABASE_NAME = UserPreferences.getInstance(context.getApplicationContext()).getCurrentUser() + ".db";
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_PASSWORDS_TABLE = "CREATE TABLE " + TABLE_PASSWORDS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_TITLE + " TEXT," + KEY_PASSWORD + " TEXT,"
                + KEY_FAVORITE + " INTEGER," + KEY_TAG + " TEXT," + KEY_ICON + " INTEGER" + ")";
        sqLiteDatabase.execSQL(CREATE_PASSWORDS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        // drop old table if this already exist
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_PASSWORDS);

        onCreate(sqLiteDatabase);
    }

    /**
     * Handling methods... (ALL C.R.U.D.) Create, Read, Update, Delete...
     */

    // Method for adding new Password
    public void addPassword(Password password) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, password.getTitle()); // Title
        values.put(KEY_PASSWORD, password.getPassword()); // Password
        values.put(KEY_TAG, password.getTag()); // Tag
        values.put(KEY_ICON, password.getIcon()); // Icon
        values.put(KEY_FAVORITE, password.getFavorite()); // Favorite

        // Inserting Row
        db.insert(TABLE_PASSWORDS, null, values);
        // Closing the Database Connection
        db.close();
    }

    // Method for getting single Password
    public Password getPassword(String Title) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_PASSWORDS, new String[]{KEY_TITLE, KEY_PASSWORD, KEY_FAVORITE, KEY_TAG, KEY_ICON}, KEY_ID + "=?",
                new String[]{String.valueOf(KEY_ID)}, null, null, null, null);

        if (cursor != null) cursor.moveToFirst();

        Password password = new Password(cursor.getString(0), cursor.getString(1), Integer.parseInt(cursor.getString(2)),
                cursor.getString(3), Integer.parseInt(cursor.getString(4)));

        return password;
    }


    // Method for getting all Passwords
    public List<Password> getAllPasswords() {
        List<Password> passwordList = new ArrayList<Password>();

        // Here, we select all query
        String selectQuery = "SELECT  * FROM " + TABLE_PASSWORDS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // Loop until all rows adding to list
        if (cursor.moveToFirst()) {
            do {
                Password password = new Password();
                password.setId(Integer.parseInt(cursor.getString(0)));
                password.setTitle(cursor.getString(1));
                password.setPassword(cursor.getString(2));
                password.setFavorite(Integer.parseInt(cursor.getString(3)));
                password.setTag(cursor.getString(4));
                password.setIcon(Integer.parseInt(cursor.getString(5)));

                // Adding password to the list
                passwordList.add(password);
            } while (cursor.moveToNext());
        }
        return passwordList;
    }

    // Method for getting Passwords count
    public int getPasswordsCount() {
        String countQuery = "SELECT * FROM " + TABLE_PASSWORDS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        return cursor.getCount();

    }

    // Method for update single Password
    public int updatePassword(Password password) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, password.getTitle());
        values.put(KEY_PASSWORD, password.getPassword());
        values.put(KEY_TAG, password.getTag());
        values.put(KEY_ICON, password.getIcon());
        values.put(KEY_FAVORITE, password.getFavorite());

        return db.update(TABLE_PASSWORDS, values, KEY_ID + "=?",
                new String[]{String.valueOf(password.getId())});
    }

    // Method for deleting single Password
    public void deletePassword(Password password) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_PASSWORDS, KEY_ID + "=?",
                new String[]{String.valueOf(password.getId())});
    }
}
