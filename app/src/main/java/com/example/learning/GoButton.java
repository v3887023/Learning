package com.example.learning;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Toast;

public class GoButton extends android.support.v7.widget.AppCompatButton {
    private static final String TAG = GoButton.class.getSimpleName();
    private boolean pressed = false;
    private long lastPressedMillis;
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (pressed) {
                long duration = System.currentTimeMillis() - lastPressedMillis;
                Log.d(TAG, "duration: " + duration);
                if (onPressedListener != null) {
                    onPressedListener.onPressed(duration);
                }
                postDelayed(this, 33);
            }
        }
    };

    public GoButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean performClick() {
        return super.performClick();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, "action down");
                pressed = true;
                lastPressedMillis = System.currentTimeMillis();
                post(runnable);
                break;
            case MotionEvent.ACTION_UP:
                Log.d(TAG, "action up");
                removeCallbacks(runnable);
                pressed = false;
                if (onPressedListener != null) {
                    onPressedListener.onCancel(System.currentTimeMillis() - lastPressedMillis);
                }
                performClick();
                break;
            default:
                break;
        }
        return super.onTouchEvent(event);
    }

    private OnPressedListener onPressedListener;

    public void setOnPressedListener(OnPressedListener onPressedListener) {
        this.onPressedListener = onPressedListener;
    }

    public interface OnPressedListener {
        void onPressed(long duration);

        void onCancel(long duration);
    }
}
