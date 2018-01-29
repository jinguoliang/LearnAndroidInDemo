package com.example.jinux.mydemo.resouce.font;

import android.app.ListActivity;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jinux.mydemo.R;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Jinux on 16/10/13.
 */

public class Fonts extends ListActivity {
    private ListView mListView;
    private String[] mFonts;
    private ArrayList<TextView> tvs;
    private Typeface mCurrentTypeface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fontslist);
        mListView = getListView();
        LinearLayout content = (LinearLayout) findViewById(R.id.content);
        tvs = new ArrayList<>();
        for (int i = 0; i < content.getChildCount(); i++) {
            View v = content.getChildAt(i);
            if (v instanceof TextView) {
                tvs.add((TextView) v);
            }
        }

        try {
            mFonts = getFonts();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "No fonts", Toast.LENGTH_LONG);
            return;
        }

        mListView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.test_list_item, mFonts));
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                changeFont(mFonts[position]);
            }
        });
    }

    private void changeFont(String font) {
        Typeface newFont = Typeface.createFromAsset(getAssets(), "font/" + font);
        for (TextView tv : tvs) {
            tv.setTypeface(newFont);
        }
        mCurrentTypeface = newFont;
    }

    private String[] getFonts() throws IOException {
        AssetManager am = getAssets();
        return am.list("font");
    }
}
