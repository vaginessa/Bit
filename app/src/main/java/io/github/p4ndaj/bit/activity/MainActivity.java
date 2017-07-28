package io.github.p4ndaj.bit.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import io.github.p4ndaj.bit.R;
import io.github.p4ndaj.bit.adapter.SavedPasswordAdapter;
import io.github.p4ndaj.bit.db.DatabaseHandler;
import io.github.p4ndaj.bit.interfaces.NavigationAndOnClick;
import io.github.p4ndaj.bit.lists.Password;
import io.github.p4ndaj.bit.lists.SavedPasswordList;
import io.github.p4ndaj.bit.preferences.InternalPreferences;
import io.github.p4ndaj.bit.utils.FontsUtils;

public class MainActivity extends AppCompatActivity implements NavigationAndOnClick {

    private RecyclerView recyclerView;

    private SavedPasswordAdapter savedPasswordAdapter;

    private List<SavedPasswordList> mainSavedPasswordList = new ArrayList<>();

    private ImageView imageViewNoPasswordSaved;

    private TextView textViewNoPasswordSaved;

    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        imageViewNoPasswordSaved = (ImageView) findViewById(R.id.imageViewNoPasswordSaved);

        textViewNoPasswordSaved = (TextView) findViewById(R.id.textViewNoPasswordSaved);

        fab = (FloatingActionButton) findViewById(R.id.fab);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewSavedPassword);

        fab.setOnClickListener(this);

        setFonts();

        if (InternalPreferences.getInstance(getApplicationContext()).isDebugAdapterSavedPasswordSetted()) {
            setDebugRecyclerViewAdapter();
        }

        if (!InternalPreferences.getInstance(getApplicationContext()).isDatabaseEmpty()) {
            setRecyclerViewAdapter();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    public void showNoPasswordSaved() {
        imageViewNoPasswordSaved.setVisibility(View.VISIBLE);
        textViewNoPasswordSaved.setVisibility(View.VISIBLE);
    }

    public void hideNoPasswordSaved() {
        imageViewNoPasswordSaved.setVisibility(View.GONE);
        textViewNoPasswordSaved.setVisibility(View.GONE);
    }

    public void setFonts() {
        FontsUtils.setLatoRegularFontTextView(textViewNoPasswordSaved, this);
    }


    private void setDebugRecyclerViewAdapter() {
        hideNoPasswordSaved();

        SavedPasswordList savedPasswordList = new SavedPasswordList("Twitter", "01234567", "Social", R.drawable.ic_account_circle_bdbdbd_24dp, true);
        mainSavedPasswordList.add(savedPasswordList);

        savedPasswordAdapter = new SavedPasswordAdapter(mainSavedPasswordList, this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(savedPasswordAdapter);
    }

    /*private void testDatabase() {
        DatabaseHandler db = new DatabaseHandler(this);
        // All CRUD Operations
        Log.d("Insert: ", "Inserting ..");
        db.addPassword(new Password("Twitter", "01234567", "Social", R.drawable.ic_add_black_24dp));
        db.addPassword(new Password("Twitter", "01234567", "Social", R.drawable.ic_add_black_24dp));
        db.addPassword(new Password("Twitter", "01234567", "Social", R.drawable.ic_add_black_24dp));
        db.addPassword(new Password("Twitter", "01234567", "Social", R.drawable.ic_add_black_24dp));
        db.addPassword(new Password("Twitter", "01234567", "Social", R.drawable.ic_add_black_24dp));

        Log.d("Reading:", "Reading all contacts..");
        List<Password> passwordList = db.getAllPasswords();

        for (Password pw : passwordList) {
            String log = "id:" + pw.getId()+"Title:"+pw.getTitle() + "Password: " + pw.getPassword();
            // Debug
            Log.d(":", log);
        }
    }*/

    public void showAddPasswordDialog() {
        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.add_password_layout, null);
        dialogBuilder.setView(dialogView);

        final EditText editTextTitle = dialogView.findViewById(R.id.editTextTitle);
        final EditText editTextPassword = dialogView.findViewById(R.id.editTextPassword);
        final EditText editTextTag = dialogView.findViewById(R.id.editTextTag);

        TextView Title = dialogView.findViewById(R.id.textViewTitle);
        TextView Password = dialogView.findViewById(R.id.textViewPassword);
        TextView Tag = dialogView.findViewById(R.id.textViewTag);
        TextView SelectIcon = dialogView.findViewById(R.id.textViewSelectIcon);
        TextView Header = dialogView.findViewById(R.id.textViewHeader);

        final CheckBox isFavorite = dialogView.findViewById(R.id.checkBoxFavorite);

        FontsUtils.setLatoRegularFontEditText(editTextTitle, this);
        FontsUtils.setLatoRegularFontEditText(editTextPassword, this);
        FontsUtils.setLatoRegularFontEditText(editTextTag, this);

        FontsUtils.setLatoRegularFontTextView(Title, this);
        FontsUtils.setLatoRegularFontTextView(Password, this);
        FontsUtils.setLatoRegularFontTextView(Tag, this);
        FontsUtils.setLatoRegularFontTextView(SelectIcon, this);
        FontsUtils.setLatoRegularFontTextView(Header, this);

        FontsUtils.setLatoRegularFontCheckBox(isFavorite, this);

        dialogBuilder.setPositiveButton(getString(R.string.save), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String Title = editTextTitle.getText().toString().trim();
                String Password = editTextPassword.getText().toString().trim();
                String Tag = editTextTag.getText().toString().trim();

                int Icon = R.drawable.ic_account_circle_bdbdbd_24dp;

                int Favorite = 0;

                if (isFavorite.isChecked()) {
                    Favorite = 1;
                } else {
                    Favorite = 0;
                }

                if (Title.equals("") || Password.equals("") || Tag.equals("")) {
                    Toast.makeText(MainActivity.this, R.string.please_fill_all_the_fields, Toast.LENGTH_SHORT).show();
                } else {
                    // save data on DB
                    Toast.makeText(MainActivity.this, R.string.saving_data, Toast.LENGTH_SHORT).show();

                    DatabaseHandler db = new DatabaseHandler(MainActivity.this);
                    db.addPassword(new Password(Title, Password, Favorite, Tag, Icon));

                    if (InternalPreferences.getInstance(getApplicationContext()).isDatabaseEmpty()) {
                        InternalPreferences.getInstance(getApplicationContext()).setIsDatabaseEmpty(false);
                    }

                    setRecyclerViewAdapter();
                }
            }
        }).create();

        dialogBuilder.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // do nothing ...
            }
        }).create();

        AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();
    }

    public void preparePasswordList() {
        DatabaseHandler db = new DatabaseHandler(this);

        //Log.d("Reading:", "Reading all contacts..");
        List<Password> passwordList = db.getAllPasswords();

        // clear main list
        mainSavedPasswordList.clear();

        for (Password pw : passwordList) {
            //String log = "id:" + pw.getId()+"Title:"+pw.getTitle() + "Password: " + pw.getPassword();
            // Debug
            //Log.d(":", log);

            boolean favorite = false;

            if (pw.getFavorite() == 0) {
                favorite = false;
            } else if (pw.getFavorite() == 1) {
                favorite = true;
            }

            SavedPasswordList savedPasswordList = new SavedPasswordList(pw.getTitle(), pw.getPassword(), pw.getTag(), pw.getIcon(), favorite);
            mainSavedPasswordList.add(savedPasswordList);
        }
    }

    public void setRecyclerViewAdapter() {
        hideNoPasswordSaved();

        preparePasswordList();

        savedPasswordAdapter = new SavedPasswordAdapter(mainSavedPasswordList, this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(savedPasswordAdapter);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            if (InternalPreferences.getInstance(getApplicationContext()).isDebugSession()) {
                Intent intent = new Intent(MainActivity.this, DebugMainActivity.class);
                startActivity(intent);
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View v) {
        if (v == fab) {
            showAddPasswordDialog();
        }
    }
}
