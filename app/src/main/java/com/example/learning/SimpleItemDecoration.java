package com.example.learning;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by zhangcx@nemo-inc.com
 * Date: 2019-08-31
 * <p>
 * Description:
 */
class SimpleItemDecoration extends RecyclerView.ItemDecoration {
    private final static int DEFAULT_DIVIDER_COLOR = Color.parseColor("#F5F5F5");
    private int padding;
    private Drawable dividerDrawable;

    public SimpleItemDecoration(int paddingPixel) {
        this(paddingPixel, DEFAULT_DIVIDER_COLOR);
    }

    public SimpleItemDecoration(int padding, @ColorInt int dividerColor) {
        this.padding = padding;
        this.dividerDrawable = new ColorDrawable(dividerColor);
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof LinearLayoutManager) {
            int i = parent.indexOfChild(view);
            if (i > 0) {
                outRect.top = padding;
            }
        }
    }

    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDraw(c, parent, state);

        int childCount = parent.getChildCount();
        for (int i = 1; i < childCount; i++) {
            View child = parent.getChildAt(i);
            int top = child.getTop();
            dividerDrawable.setBounds(child.getLeft(), top - padding, child.getRight(), top);
            dividerDrawable.draw(c);
        }
    }
}
