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
import io.github.p4ndaj.bit.preferences.UserPreferences;
import io.github.p4ndaj.bit.utils.DebugUtils;
import io.github.p4ndaj.bit.utils.FontsUtils;
import io.github.p4ndaj.bit.utils.StringUtils;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView textViewForgotPassword;
    private TextView textViewWelcomeBack;
    private TextView textViewEmail;
    private TextView textViewPassword;
    private TextView textViewSignIn;
    private TextView textViewSignUp;

    private Button buttonLogin;

    private EditText editTextEmail;
    private EditText editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        textViewForgotPassword = (TextView) findViewById(R.id.textViewForgotPassword);
        textViewEmail = (TextView) findViewById(R.id.textViewEmailLogin);
        textViewPassword = (TextView) findViewById(R.id.textViewPasswordLogin);
        textViewSignIn = (TextView) findViewById(R.id.textViewSignInToContinue);
        textViewWelcomeBack = (TextView) findViewById(R.id.textViewWelcomeBack);
        textViewSignUp = (TextView) findViewById(R.id.textViewSignUp);

        buttonLogin = (Button) findViewById(R.id.buttonLogin);

        editTextEmail = (EditText) findViewById(R.id.editTextEmailLogin);
        editTextPassword = (EditText) findViewById(R.id.editTextPasswordLogin);

        textViewForgotPassword.setOnClickListener(this);
        textViewSignUp.setOnClickListener(this);

        buttonLogin.setOnClickListener(this);

        setFonts();

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

    public void setFonts() {
        FontsUtils.setLatoRegularFontTextView(textViewEmail, this);
        FontsUtils.setLatoRegularFontTextView(textViewPassword, this);
        FontsUtils.setLatoBoldFontTextView(textViewForgotPassword, this);
        FontsUtils.setLatoBoldFontTextView(textViewSignIn, this);
        FontsUtils.setLatoBoldFontTextView(textViewWelcomeBack, this);
        FontsUtils.setLatoBoldFontTextView(textViewSignUp, this);

        FontsUtils.setLatoRegularFontEditText(editTextEmail, this);
        FontsUtils.setLatoRegularFontEditText(editTextPassword, this);

        FontsUtils.setLatoRegularFontButton(buttonLogin, this);
    }

    public void runMainActivity() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onClick(View v) {
        if (v == textViewForgotPassword) {
            Intent intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
            startActivity(intent);
            finish();
        } else if (v == buttonLogin) {
            if (DebugUtils.isDebugUser(getEmailString(), getPasswordString(), this) == 1) {
                runMainActivity();
            } else if (!StringUtils.isAnEmail(getEmailString())
                    || getEmailString().equals("") || getPasswordString().equals("")) {
                Toast.makeText(this, R.string.please_fill_all_the_fields, Toast.LENGTH_SHORT).show();
                return;
            } else {
                if (!UserPreferences.getInstance(getApplicationContext()).getPassword(getEmailString()).equals("NOT_FOUND")
                        && UserPreferences.getInstance(getApplicationContext()).getPassword(getEmailString()).equals(getPasswordString())) {
                    UserPreferences.getInstance(getApplicationContext()).setCurrentUser(getEmailString());
                    runMainActivity();
                } else {
                    Toast.makeText(this, R.string.please_check_strings_inserted_and_try_again, Toast.LENGTH_SHORT).show();
                }
            }
        } else if (v == textViewSignUp) {
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
