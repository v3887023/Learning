package com.example.learning;

import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

/**
 * Created by zhangcx@nemo-inc.com
 * Date: 2019-08-31
 * <p>
 * Description:
 */
public class SimpleItemDecoration extends RecyclerView.ItemDecoration {
    private static final String TAG = SimpleItemDecoration.class.getSimpleName();
    private int padding;

    public SimpleItemDecoration(int padding) {
        this.padding = padding;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        RecyclerView.Adapter adapter = parent.getAdapter();
        if (layoutManager == null || adapter == null) {
            return;
        }

        if (layoutManager instanceof LinearLayoutManager) {
            int i = parent.getChildAdapterPosition(view);
            Log.d(TAG, "i: " + i);
            outRect.left = padding;
            outRect.right = padding;

            if (i == 0) {
                outRect.top = padding;
            } else {
                outRect.top = padding / 2;
            }

            if (i == adapter.getItemCount() - 1) {
                outRect.bottom = padding;
            } else {
                outRect.bottom = padding / 2;
            }
        }
    }
}
