package com.example.learning;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

public class StickyLayout extends FrameLayout {
    private static final String TAG = StickyLayout.class.getSimpleName();

    private View headerView;
    private View contentView;
    private int lastY;
    private int offsetFromTop = 0;

    public StickyLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        boolean b = super.dispatchTouchEvent(ev);

        Log.d(TAG, "dispatchTouchEvent: " + b);

        return b;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        headerView = getChildAt(0);
        contentView = getChildAt(1);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        headerView.layout(0, -headerView.getMeasuredHeight(), headerView.getMeasuredWidth(), 0);
        contentView.layout(0, 0, contentView.getMeasuredWidth(), contentView.getMeasuredHeight());
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int y = (int) event.getY();
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_MOVE:
                int offset = (int) ((y - lastY) * 0.8f);

                int temp = offsetFromTop + offset;
                if (temp > headerView.getMeasuredHeight()) {
                    offset = headerView.getMeasuredHeight() - offsetFromTop;
                } else if (temp < 0) {
                    offset = 0 - offsetFromTop;
                }

                offsetFromTop += offset;

                if (offset != 0 && offsetFromTop >= 0 && offsetFromTop <= headerView.getMeasuredHeight()) {
                    scrollBy(0, -offset);
                }
                break;
            case MotionEvent.ACTION_UP:
                if (offsetFromTop < headerView.getMeasuredHeight() / 2) {
                    offsetFromTop = 0;
                    scrollTo(0, 0);
                } else {
                    offsetFromTop = headerView.getMeasuredHeight();
                    scrollTo(0, -headerView.getMeasuredHeight());
                }
                break;
        }

        lastY = y;

        return true;
    }

    private boolean headerViewVisible() {
        return offsetFromTop > 0;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean headerViewVisible = headerViewVisible();

        int y = (int) ev.getY();
        if (ev.getActionMasked() == MotionEvent.ACTION_MOVE) {
            int offset = (y - lastY);
            lastY = y;

            return (offset > 0 && !headerViewVisible && !canContentScrollUp()) ||
                    (offset < 0 && headerViewVisible);
        }

        lastY = y;

        return false;
    }

    private boolean canContentScrollUp() {
        return contentView.canScrollVertically(-1);
    }
}
