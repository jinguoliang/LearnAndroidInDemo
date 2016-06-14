package com.example.jinux.mydemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by baidu on 16/5/10.
 */
public class RotatableTextView extends View {

    private String mDirection;
    private String mText;
    private String mTextSize;
    private int mTextColor;

    private TextPaint mPaint;

    public RotatableTextView(Context context) {
        super(context);
    }

    public RotatableTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setBackgroundColor(Color.WHITE);
        mText = "hello";
        mPaint = new TextPaint();
        mPaint.setTextSize(200);
        mPaint.setColor(Color.BLACK);
        mDirection = "bottom";
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        float textWidth = mPaint.measureText(mText);
        float textHeight = mPaint.getTextSize();

        int width;
        int height;
        if (TextUtils.equals(mDirection, "top") || TextUtils.equals(mDirection, "bottom")) {
            width= (int) (textWidth + getPaddingLeft() + getPaddingRight());
            height = (int) textHeight + getPaddingTop() + getPaddingBottom();
        } else {
            width = (int) textHeight + getPaddingTop() + getPaddingBottom();
            height = (int) (textWidth + getPaddingLeft() + getPaddingRight());
        }

        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (TextUtils.equals(mDirection, "top")) {
        } else if(TextUtils.equals(mDirection, "left")) {
            canvas.rotate(-90);
            canvas.translate(-getMeasuredHeight(), 0);
        } else if(TextUtils.equals(mDirection, "right")) {
            canvas.rotate(90);
            canvas.translate(0, -getMeasuredWidth());
        } else if(TextUtils.equals(mDirection, "bottom")) {
            canvas.rotate(180);
            canvas.translate(-getMeasuredWidth(), -getMeasuredHeight());
        }
        canvas.drawText(mText, getPaddingLeft(), 200, mPaint);
    }

    public void setText(String text) {
        mText = text;
    }

    public void setTextSize(int size) {
        mPaint.setTextSize(size);
    }

    public void setTextColor(int color) {
        mPaint.setColor(color);
    }
}
