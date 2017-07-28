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
import io.github.p4ndaj.bit.preferences.ActivityPreferences;
import io.github.p4ndaj.bit.preferences.UserPreferences;
import io.github.p4ndaj.bit.utils.DebugUtils;
import io.github.p4ndaj.bit.utils.FontsUtils;
import io.github.p4ndaj.bit.utils.StringUtils;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView textViewWelcomeDear;
    private TextView textViewSignUp;
    private TextView textViewEmail;
    private TextView textViewPassword;
    private TextView textViewSignIn;

    private Button buttonRegister;

    private EditText editTextEmail;
    private EditText editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        textViewWelcomeDear = (TextView) findViewById(R.id.textViewWelcomeDear);
        textViewSignUp = (TextView) findViewById(R.id.textViewSignupRegister);
        textViewEmail = (TextView) findViewById(R.id.textViewEmailRegister);
        textViewPassword = (TextView) findViewById(R.id.textViewPasswordRegister);
        textViewSignIn = (TextView) findViewById(R.id.textViewSignInRegister);

        buttonRegister = (Button) findViewById(R.id.buttonRegister);

        editTextEmail = (EditText) findViewById(R.id.editTextEmailRegister);
        editTextPassword = (EditText) findViewById(R.id.editTextPasswordRegister);

        textViewSignIn.setOnClickListener(this);

        buttonRegister.setOnClickListener(this);

        editTextEmail.setOnClickListener(this);
        editTextPassword.setOnClickListener(this);

        setFonts();

        // initialize user object
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

    public void setFonts() {
        FontsUtils.setLatoRegularFontTextView(textViewEmail, this);
        FontsUtils.setLatoRegularFontTextView(textViewPassword, this);
        FontsUtils.setLatoBoldFontTextView(textViewSignUp, this);
        FontsUtils.setLatoBoldFontTextView(textViewWelcomeDear, this);
        FontsUtils.setLatoBoldFontTextView(textViewSignIn, this);

        FontsUtils.setLatoRegularFontEditText(editTextEmail, this);
        FontsUtils.setLatoRegularFontEditText(editTextPassword, this);

        FontsUtils.setLatoRegularFontButton(buttonRegister, this);
    }

    public void runMainActivity() {
        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void addUser(String Email, String Password) {
        UserPreferences.getInstance(getApplicationContext()).setPassword(Email, Password);
        UserPreferences.getInstance(getApplicationContext()).setCurrentUser(Email);
    }

    @Override
    public void onClick(View v) {
        if (v == buttonRegister) {
            if (DebugUtils.isDebugUser(getStringEmail(), getStringPassword(), this) == 1) {
                addUser(getStringEmail(), getStringPassword());
                runMainActivity();
            } else if (isEditTextDataEmpty() || !StringUtils.isAnEmail(getStringEmail())) {
                Toast.makeText(this, R.string.please_fill_all_the_fields, Toast.LENGTH_SHORT).show();
            } else {
                if (UserPreferences.getInstance(getApplicationContext()).getPassword(getStringEmail()).equals("NOT_FOUND")) {
                    addUser(getStringEmail(), getStringPassword());
                } else {
                    Toast.makeText(this, R.string.this_account_already_exist, Toast.LENGTH_SHORT).show();
                    return;
                }

                if (ActivityPreferences.getInstance(getApplicationContext()).isFirstRun()) {
                    ActivityPreferences.getInstance(getApplicationContext()).updateIsFirstRun();
                }

                runMainActivity();
            }
        } else if (v == editTextEmail) {
            // do nothing...
        } else if (v == editTextPassword) {
            // do nothing...
        } else if (v == textViewSignIn) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
