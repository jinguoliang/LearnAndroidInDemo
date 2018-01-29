package com.example.jinux.mydemo.resouce;

import android.app.Activity;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.text.style.StyleSpan;
import android.widget.TextView;

import com.example.jinux.mydemo.R;

/**
 * Created by Jinux on 16/11/8.
 */

public class SpannableTest extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.spannable_test);
        TextView tv = (TextView) findViewById(R.id.testtext);
        tv.setText(getSpannableText());
    }

    public CharSequence getSpannableText() {
        SpannableStringBuilder builder = new SpannableStringBuilder();
        String text = "hello world";
        builder.setSpan(new StyleSpan(Typeface.BOLD), 0,0, Spanned.SPAN_MARK_MARK);
        builder.append(text);
        builder.setSpan(new StyleSpan(Typeface.BOLD), 0,text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder.append("haha");
        Drawable d = getDrawable(R.drawable.ic_launcher);
        d.setBounds(0, 0, d.getIntrinsicWidth(), d.getIntrinsicHeight());
        builder.setSpan(new ImageSpan(d), text.length(), text.length() ,
                Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        return builder;
    }
}
