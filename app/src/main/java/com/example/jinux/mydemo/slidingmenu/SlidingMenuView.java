package com.example.jinux.mydemo.slidingmenu;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Scroller;

/**
 * Created by jinux on 6/3/15.
 */
public class SlidingMenuView extends LinearLayout {
    private static final int MENU_WIDTH = 800;
    private FrameLayout menu;
    private FrameLayout content;
    private float sx;
    private float sy;
    private int offset;
    private boolean isMenuOpen;
    private Scroller scroller;

    public SlidingMenuView(Context context) {
        super(context);
        setOrientation(HORIZONTAL);

        scroller = new Scroller(getContext());

        menu = new FrameLayout(getContext());
        LinearLayout.LayoutParams params = new LayoutParams(MENU_WIDTH, LayoutParams.MATCH_PARENT);
        addView(menu, params);

        content = new FrameLayout(getContext());
        params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        addView(content, params);

        offset = MENU_WIDTH;
        scrollTo(offset, 0);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                sx = event.getX();
                isMenuOpen = (offset == 0);
                break;
            case MotionEvent.ACTION_MOVE:
                d("the x = " + sx);
                d("the x = " + event.getX());
                d("the x = " + offset);
                int ofs = (int) (sx - event.getX());
                if (Math.abs(ofs) > 10) {
                    offset += ofs;
                    if (offset > MENU_WIDTH) offset = MENU_WIDTH;
                    if (offset < 0) offset = 0;
                    scrollTo(offset, 0);

                    sx = event.getX();
                }
                break;
            case MotionEvent.ACTION_UP:
                if (offset>MENU_WIDTH/2) smoothScrollTo(MENU_WIDTH);
                if (offset<MENU_WIDTH/2) smoothScrollTo(0);
                break;
        }
        return true;
    }

    private void smoothScrollTo(int pos) {
        d("smoothScrollTo: start = "+offset);
        d("smoothScrollTo: end = "+pos);
        int delta =  pos - offset;
        scroller.startScroll(offset, 0, delta, 0, Math.abs(delta));
        invalidate();
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (scroller.computeScrollOffset()){
            offset = scroller.getCurrX();
            d("computeScrollOffset: end = " + offset);
            scrollTo(offset, 0);
            postInvalidate();
        }
    }

    public void setMenu(View menu) {
        this.menu.addView(menu);
    }


    public void setContent(View content) {
        this.content.addView(content);
    }

    void d(String s) {
        Log.e("sliding", s);
    }
}
