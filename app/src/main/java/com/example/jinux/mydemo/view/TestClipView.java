package com.example.jinux.mydemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.example.jinux.mydemo.R;

/**
 * Created by Jinux on 16/8/20.
 */
public class TestClipView extends ImageView {
    private final Drawable mDrawable;
    private final Paint mPaint;

    public TestClipView(Context context) {
        super(context);
        mDrawable = getResources().getDrawable(R.drawable.ic_launcher);
        mDrawable.setBounds(0,0, 500, 500);
        mPaint = new Paint();
        mPaint.setColor(Color.BLUE);
//        setImageDrawable(mDrawable);
//        setImageDrawable();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.BLACK);
        canvas.save();
        canvas.drawCircle(0, 0, 100, mPaint);
        canvas.clipRect(200, 200, 500, 500);
        mDrawable.draw(canvas);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(500, 500);
    }
}
