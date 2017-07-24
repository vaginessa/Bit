package io.github.p4ndaj.bit.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import io.github.p4ndaj.bit.R;
import io.github.p4ndaj.bit.db.User;
import io.github.p4ndaj.bit.preferences.ActivityPreferences;
import io.github.p4ndaj.bit.utils.StringUtils;
import io.realm.Realm;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonRegister;

    private EditText editTextEmail;
    private EditText editTextPassword;

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        buttonRegister = (Button) findViewById(R.id.buttonRegister);

        editTextEmail = (EditText) findViewById(R.id.editTextEmailRegister);
        editTextPassword = (EditText) findViewById(R.id.editTextPasswordRegister);

        buttonRegister.setOnClickListener(this);

        editTextEmail.setOnClickListener(this);
        editTextPassword.setOnClickListener(this);

        // initialize user object
        user = new User();

        // Initialize Realm
        // Realm.init(getApplicationContext());

        // Get a Realm instance for this thread
        // Realm realm = Realm.getDefaultInstance();
    }

    @Override
    public void onClick(View v) {
        if (v == buttonRegister) {
            if (isEditTextDataEmpty() || !StringUtils.isAnEmail(getStringEmail())) {
                Toast.makeText(this, R.string.please_fill_all_the_fields, Toast.LENGTH_SHORT).show();
            } else {
                user.setEmail(getStringEmail());
                user.setPassword(getStringPassword());

                // update isFirstRun() boolean
                ActivityPreferences.getInstance(getApplicationContext()).updateIsFirstRun();

                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        } else if (v == editTextEmail) {
            // do nothing
        } else if (v == editTextPassword) {
            // do nothing
        }
    }

    public boolean isEditTextDataEmpty() {
        if (editTextEmail.getText().toString().trim().equals("")
                || editTextPassword.getText().toString().trim().equals("")) {
            return true;
        }
        return false;
    }

    public String getStringEmail() {
        return editTextEmail.getText().toString().trim();
    }

    public String getStringPassword() {
        return editTextPassword.getText().toString().trim();
    }
}
