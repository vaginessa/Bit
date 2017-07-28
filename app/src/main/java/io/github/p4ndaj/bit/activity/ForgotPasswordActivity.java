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
import io.github.p4ndaj.bit.utils.FontsUtils;
import io.github.p4ndaj.bit.utils.StringUtils;

public class ForgotPasswordActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView textViewTitle;
    private TextView textViewSummary;

    private Button buttonBack;
    private Button buttonSend;

    private EditText editTextEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        textViewTitle = (TextView) findViewById(R.id.textViewForgotPasswordTitle);
        textViewSummary = (TextView) findViewById(R.id.textViewRetrieveItByEmail);

        buttonBack = (Button) findViewById(R.id.buttonBack);
        buttonSend = (Button) findViewById(R.id.buttonSendEmail);

        editTextEmail = (EditText) findViewById(R.id.editTextEmailForgotPassword);

        buttonBack.setOnClickListener(this);
        buttonSend.setOnClickListener(this);

        setFonts();
    }

    public String getStringEmail() {
        if (editTextEmail.getText().toString().trim().equals("")) {
            Toast.makeText(this, R.string.please_insert_valid_email, Toast.LENGTH_SHORT).show();
            return "NOT_VALID_EMAIL";
        } else {
            return editTextEmail.getText().toString().trim();
        }
    }

    public void setFonts() {
        FontsUtils.setLatoBoldFontTextView(textViewTitle, this);
        FontsUtils.setLatoBoldFontTextView(textViewSummary, this);
        FontsUtils.setLatoRegularFontEditText(editTextEmail, this);
        FontsUtils.setLatoRegularFontButton(buttonBack, this);
        FontsUtils.setLatoRegularFontButton(buttonSend, this);
    }

    @Override
    public void onClick(View v) {
        if (v == buttonBack) {
            Intent intent = new Intent(ForgotPasswordActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        } else if (v == buttonSend) {
            if (StringUtils.isAnEmail(getStringEmail())
                    && !UserPreferences.getInstance(getApplicationContext()).getPassword(getStringEmail()).equals("NOT_FOUND")) {
                // adding code for get password from Realm Database

                // sending email debug
                /*Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("message/rfc822");
                intent.putExtra(Intent.EXTRA_EMAIL, getStringEmail());
                intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.bit_recovery_password));
                intent.putExtra(Intent.EXTRA_TEXT, UserPreferences.getInstance(getApplicationContext()).getPassword(getStringEmail()));

                try {
                    startActivity(Intent.createChooser(intent, getString(R.string.send_email_three_dot)));
                } catch (android.content.ActivityNotFoundException e) {
                    Toast.makeText(this, R.string.please_install_email_client, Toast.LENGTH_SHORT).show();
                }*/

            } else {
                Toast.makeText(this, R.string.please_add_a_valid_or_registered_email, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
