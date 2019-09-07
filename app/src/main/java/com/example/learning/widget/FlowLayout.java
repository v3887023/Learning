package com.example.learning.widget;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by zhangcx@nemo-inc.com
 * Date: 2019-09-06
 * <p>
 * Description:
 */
public class FlowLayout extends ViewGroup {
    private static final String TAG = FlowLayout.class.getSimpleName();

    public FlowLayout(Context context) {
        super(context);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(widthMeasureSpec);

        measureChildren(widthMeasureSpec, heightMeasureSpec);

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return new MarginLayoutParams(p.width, p.height);
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new MarginLayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();

        int parentWidth = getMeasuredWidth();

        int paddingLeft = getPaddingLeft();
        int usedWidth = paddingLeft;
        int usedHeight = getPaddingTop();
        int maxHeight = 0;
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            if (child == null || child.getVisibility() == GONE) {
                continue;
            }

            MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();

            int childLeft = usedWidth + lp.leftMargin;
            int childTop = usedHeight + lp.topMargin;
            int childWidth = child.getMeasuredWidth();
            int childHeight = child.getMeasuredHeight();

            int horizontalMargin = lp.leftMargin + lp.rightMargin;
            int verticalMargin = lp.topMargin + lp.bottomMargin;

            if (usedWidth + childWidth + horizontalMargin > parentWidth) {
                usedHeight = maxHeight;
                usedWidth = paddingLeft;
                childLeft = usedWidth + lp.leftMargin;
                childTop = usedHeight + lp.topMargin;
            }

            setChildFrame(child, childLeft, childTop, childWidth, childHeight);

            usedWidth += childWidth + horizontalMargin;

            if (usedHeight + childHeight + verticalMargin > maxHeight) {
                maxHeight = usedHeight + childHeight + verticalMargin;
            }
        }
    }

    private void setChildFrame(View child, int left, int top, int width, int height) {
        child.layout(left, top, left + width, top + height);
    }
}
