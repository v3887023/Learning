package com.example.learning;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.FrameLayout;

public class MyViewGroup extends FrameLayout {
    private static final String TAG = MyViewGroup.class.getSimpleName();

    public MyViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        boolean b = super.dispatchTouchEvent(ev);

        Log.d(TAG, "dispatchTouchEvent: " + b);

        return b;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean b = super.onTouchEvent(event);

        Log.d(TAG, "onTouchEvent:" + b);

        return b;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean b = super.onInterceptTouchEvent(ev);

        Log.d(TAG, "onInterceptTouchEvent: " + b);

        return b;
    }
}
