package io.github.p4ndaj.bit.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

import io.github.p4ndaj.bit.R;
import io.github.p4ndaj.bit.preferences.ActivityPreferences;
import io.github.p4ndaj.bit.utils.FontsUtils;

public class SplashActivity extends AppCompatActivity {

    public Intent intent;

    private TextView textViewWelcomeTo;
    private TextView textViewBit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        textViewWelcomeTo = (TextView) findViewById(R.id.textViewWelcomeToSplash);
        textViewBit = (TextView) findViewById(R.id.textViewBitSplash);

        setFonts();

        /* thread for time offset */
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                if (ActivityPreferences.getInstance(getApplicationContext()).isFirstRun()) {
                    intent = new Intent(SplashActivity.this, RegisterActivity.class);
                } else {
                    intent = new Intent(SplashActivity.this, LoginActivity.class);
                }
                startActivity(intent);
                finish();
            }
        }, 3000);
    }

    public void setFonts() {
        FontsUtils.setLatoRegularFontTextView(textViewWelcomeTo, this);
        FontsUtils.setLatoRegularFontTextView(textViewBit, this);
    }
}
