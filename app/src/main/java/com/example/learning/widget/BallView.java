package com.example.learning.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.widget.Scroller;

/**
 * @Description:
 * @Author: zcx
 * @Copyright: 浙江集商优选电子商务有限公司
 * @CreateDate: 2019/11/27 11:32
 * @Version: 1.0.0
 */
public class BallView extends View {
    private Scroller mScroller;
    private float mLastX;
    private int mOffsetX;
    private float mLastY;
    private int mOffsetY;
    private boolean fling = false;
    private VelocityTracker mVelocityTracker;
    private long mLastDownMillis;

    public BallView(Context context) {
        this(context, null);
    }

    public BallView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BallView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mScroller = new Scroller(context);
        mVelocityTracker = VelocityTracker.obtain();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getRawX();
        float y = event.getRawY();
        int offsetX = (int) (x - mLastX);
        int offsetY = (int) (y - mLastY);
        mLastX = x;
        mLastY = y;
        fling = false;
        mVelocityTracker.addMovement(event);

        long currentTimeMillis = System.currentTimeMillis();

        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                mLastDownMillis = currentTimeMillis;
                break;
            case MotionEvent.ACTION_UP:
                fling = true;
                mVelocityTracker.computeCurrentVelocity(1);
                float xVelocity = mVelocityTracker.getXVelocity();
                float yVelocity = mVelocityTracker.getYVelocity();
                long time = currentTimeMillis - mLastDownMillis;
                smoothScrollBy(-(int) (offsetX + time * xVelocity), -(int) (offsetY + time * yVelocity));
                break;
            case MotionEvent.ACTION_MOVE:
                smoothScrollBy(-offsetX, -offsetY);
                break;
            default:
                break;
        }

        return true;
    }

    public void recover() {
        smoothScrollTo(0, 0);
    }

    @Override
    public void computeScroll() {
        if (fling && mScroller.computeScrollOffset()) {
            ((View) getParent()).scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            invalidate();
        }
    }

    public void smoothScrollTo(int destX, int destY) {
        smoothScrollBy(destX - mScroller.getCurrX(), destY - mScroller.getCurrY());
    }

    public void smoothScrollBy(int dx, int dy) {
        mScroller.startScroll(mScroller.getCurrX(), mScroller.getCurrY(), dx, dy, 1500);
        invalidate();
    }
}
