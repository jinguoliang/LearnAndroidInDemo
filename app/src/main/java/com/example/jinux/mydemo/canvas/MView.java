package com.example.jinux.mydemo.canvas;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposeShader;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import com.example.jinux.mydemo.R;

public class MView extends View {

        private Paint mPaint;

        public MView(Context context) {
            super(context);
            init();
        }

        public MView(Context context, AttributeSet attrs) {
            super(context, attrs);
            init();
        }

        public MView(Context context, AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
            init();
        }

        public MView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
            super(context, attrs, defStyleAttr, defStyleRes);
            init();
        }

        public void init() {
            mPaint = new Paint();
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
            BitmapShader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
            LinearGradient gradient = new LinearGradient(0, 0, 0, 200, Color.parseColor("#ffffff00"),
                    Color.TRANSPARENT, Shader.TileMode.CLAMP);
            ComposeShader shader = new ComposeShader(bitmapShader, gradient,
                    new PorterDuffXfermode(PorterDuff.Mode.DST_ATOP));
            mPaint.setShader(shader);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            canvas.drawRect(20, 20, 400, 400, mPaint);
        }
    }