package io.github.p4ndaj.bit.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import io.github.p4ndaj.bit.lists.User;
import io.github.p4ndaj.bit.preferences.UserPreferences;

/**
 * Created by P4ndaJ on 04/08/17.
 */

public class UserHandler extends SQLiteOpenHelper {

    private static String DATABASE_NAME;

    private static final int DATABASE_VERSION = 1;

    // Passwords table name
    private static final String TABLE_USER = "user";

    // Passwords table columns name
    private static final String KEY_USER = "user";
    private static final String KEY_PASSWORD = "password"; // sha256
    private static final String KEY_ID = "id";

    public UserHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        DATABASE_NAME = UserPreferences.getInstance(context.getApplicationContext()).getCurrentUser() + "-user" + ".db";
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_PASSWORDS_TABLE = "CREATE TABLE " + TABLE_USER + "("
                + KEY_USER + " TEXT PRIMARY KEY," + KEY_PASSWORD + " TEXT" + ")";
        sqLiteDatabase.execSQL(CREATE_PASSWORDS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        // drop old table if this already exist
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);

        onCreate(sqLiteDatabase);
    }

    /**
     * Handling methods... (ALL C.R.U.D.) Create, Read, Update, Delete...
     */

    // Method for adding new Password
    public void addPassword(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_USER, user.getUser()); // User
        values.put(KEY_PASSWORD, user.getPassword()); // Password

        // Inserting Row
        db.insert(TABLE_USER, null, values);
        // Closing the Database Connection
        db.close();
    }

    // Method for getting single Password
    public User getUser(String Title) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_USER, new String[]{KEY_USER, KEY_PASSWORD}, KEY_ID + "=?",
                new String[]{String.valueOf(KEY_ID)}, null, null, null, null);

        if (cursor != null) cursor.moveToFirst();

        User user = new User(cursor.getString(0), cursor.getString(1));

        return user;
    }


    // Method for getting all Passwords
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<User>();

        // Here, we select all query
        String selectQuery = "SELECT  * FROM " + TABLE_USER;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // Loop until all rows adding to list
        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setId(Integer.parseInt(cursor.getString(0)));
                user.setUser(cursor.getString(1));
                user.setPassword(cursor.getString(2));

                // Adding password to the list
                userList.add(user);
            } while (cursor.moveToNext());
        }
        return userList;
    }

    // Method for getting Passwords count
    public int getUsersCount() {
        String countQuery = "SELECT * FROM " + TABLE_USER;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        return cursor.getCount();

    }

    // Method for update single Password
    public int updateUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_USER, user.getUser());
        values.put(KEY_PASSWORD, user.getPassword());

        return db.update(TABLE_USER, values, KEY_ID + "=?",
                new String[]{String.valueOf(user.getId())});
    }

    // Method for deleting single Password
    public void deleteUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_USER, KEY_ID + "=?",
                new String[]{String.valueOf(user.getId())});
    }
}
