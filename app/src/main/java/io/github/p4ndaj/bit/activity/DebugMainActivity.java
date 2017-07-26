package io.github.p4ndaj.bit.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import io.github.p4ndaj.bit.R;
import io.github.p4ndaj.bit.preferences.InternalPreferences;

public class DebugMainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonRegisterActivity;
    private Button buttonLoginActivity;
    private Button buttonSetSavedPasswordAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debug_main);

        buttonRegisterActivity = (Button) findViewById(R.id.buttonDebugRegisterActivity);
        buttonLoginActivity = (Button) findViewById(R.id.buttonDebugLoginActivity);
        buttonSetSavedPasswordAdapter = (Button) findViewById(R.id.buttonAdapterSavedPasswordDebug);

        setAdapterSavedPassword();

        buttonRegisterActivity.setOnClickListener(this);
        buttonLoginActivity.setOnClickListener(this);
        buttonSetSavedPasswordAdapter.setOnClickListener(this);
    }


    public void setAdapterSavedPassword() {
        if (!InternalPreferences.getInstance(getApplicationContext()).isDebugAdapterSavedPasswordSetted()) {
            buttonSetSavedPasswordAdapter.setText("Set saved password debug adapter");
        } else {
            buttonSetSavedPasswordAdapter.setText("Unset saved password debug adapter");
        }
    }

    @Override
    public void onClick(View v) {
        if (v == buttonRegisterActivity) {
            Intent intent = new Intent(DebugMainActivity.this, RegisterActivity.class);
            startActivity(intent);
        } else if (v == buttonLoginActivity) {
            Intent intent = new Intent(DebugMainActivity.this, LoginActivity.class);
            startActivity(intent);
        } else if (v == buttonSetSavedPasswordAdapter) {
            if (!InternalPreferences.getInstance(getApplicationContext()).isDebugAdapterSavedPasswordSetted()) {
                buttonSetSavedPasswordAdapter.setText("Unset saved password debug adapter");
                InternalPreferences.getInstance(getApplicationContext()).setDebugSavedPasswordAdapter(true);
            } else {
                buttonSetSavedPasswordAdapter.setText("Set saved password debug adapter");
                InternalPreferences.getInstance(getApplicationContext()).setDebugSavedPasswordAdapter(false);
            }
        }
    }
}
