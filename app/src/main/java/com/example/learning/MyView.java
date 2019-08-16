package com.example.learning;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

public class MyView extends android.support.v7.widget.AppCompatTextView {
    private static final String TAG = MyView.class.getSimpleName();

    public MyView(Context context, @Nullable AttributeSet attrs) {
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
}
