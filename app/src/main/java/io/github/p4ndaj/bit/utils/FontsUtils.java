package io.github.p4ndaj.bit.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by P4ndaJ on 7/25/17.
 */

public class FontsUtils {
    public static void setLatoRegularFontTextView(TextView textView, Context context) {
        Typeface typeface = Typeface.createFromAsset(context.getAssets(), "Lato/Lato-Regular.ttf");
        textView.setTypeface(typeface);
    }

    public static void setLatoRegularFontEditText(EditText editText, Context context) {
        Typeface typeface = Typeface.createFromAsset(context.getAssets(), "Lato/Lato-Regular.ttf");
        editText.setTypeface(typeface);
    }

    public static void setLatoRegularFontButton(Button button, Context context) {
        Typeface typeface = Typeface.createFromAsset(context.getAssets(), "Lato/Lato-Regular.ttf");
        button.setTypeface(typeface);
    }
}
