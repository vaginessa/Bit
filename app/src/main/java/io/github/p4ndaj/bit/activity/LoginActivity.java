package io.github.p4ndaj.bit.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import io.github.p4ndaj.bit.R;
import io.github.p4ndaj.bit.db.User;
import io.github.p4ndaj.bit.utils.StringUtils;
import io.realm.Realm;
import io.realm.RealmResults;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView textViewForgotPassword;

    private Button buttonLogin;

    private EditText editTextEmail;
    private EditText editTextPassword;

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        textViewForgotPassword = (TextView) findViewById(R.id.textViewForgotPassword);

        buttonLogin = (Button) findViewById(R.id.buttonLogin);

        editTextEmail = (EditText) findViewById(R.id.editTextEmailLogin);
        editTextPassword = (EditText) findViewById(R.id.editTextPasswordLogin);

        // initialize user object
        user = new User();

        // Initialize Realm
        Realm.init(getApplicationContext());

        // Get a Realm instance for this thread
        Realm realm = Realm.getDefaultInstance();

        final RealmResults<User> user = realm.where(User.class).findAll();
    }

    @Override
    public void onClick(View v) {
        if (v == textViewForgotPassword) {
            Intent intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
            startActivity(intent);
            finish();
        } else if (v == buttonLogin) {
            if (!StringUtils.isAnEmail(getEmailString())
                    || getEmailString().equals("") || getPasswordString().equals("")) {
                Toast.makeText(this, R.string.please_fill_all_the_fields, Toast.LENGTH_SHORT).show();
                return;
            } else {

            }
        }
    }

    public String getEmailString() {
        if (editTextEmail.getText().toString().trim().equals("")) {
            Toast.makeText(this, R.string.please_fill_all_the_fields, Toast.LENGTH_SHORT).show();
            return "NOT_FOUND";
        } else {
            return editTextEmail.getText().toString().trim();
        }
    }

    public String getPasswordString() {
        if (editTextPassword.getText().toString().trim().equals("")) {
            Toast.makeText(this, R.string.please_fill_all_the_fields, Toast.LENGTH_SHORT).show();
            return "NOT_FOUND";
        } else {
            return editTextPassword.getText().toString().trim();
        }
    }
}
