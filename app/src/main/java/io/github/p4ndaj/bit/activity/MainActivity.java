package io.github.p4ndaj.bit.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import io.github.p4ndaj.bit.R;
import io.github.p4ndaj.bit.adapter.SavedPasswordAdapter;
import io.github.p4ndaj.bit.interfaces.NavigationAndOnClick;
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

        }
    }
}
