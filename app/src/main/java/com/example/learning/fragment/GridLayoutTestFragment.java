package com.example.learning.fragment;

import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.learning.BaseFragment;
import com.example.learning.DimensUtils;
import com.example.learning.DisplayUtil;
import com.example.learning.Instantiable;
import com.example.learning.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangcx@nemo-inc.com
 * Date: 2019-09-05
 * <p>
 * Description:
 */
public class GridLayoutTestFragment extends BaseFragment {

    public static final Instantiable<GridLayoutTestFragment> FACTORY = args -> new GridLayoutTestFragment();

    @Override
    protected void intViews() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            list.add(String.valueOf(i));
        }

        int spanCount = 3;

        RecyclerView itemRv = findViewById(R.id.rv_item);
        itemRv.setLayoutManager(new GridLayoutManager(activity, spanCount));

        int paddingPixel = DimensUtils.dip2pixel(activity, 8);
        SimpleGridAdapter adapter = new SimpleGridAdapter(spanCount, paddingPixel, list);
        itemRv.setAdapter(adapter);
        itemRv.addItemDecoration(new SimpleItemDecoration(paddingPixel));

        View headerView = View.inflate(activity, R.layout.layout_header, null);
        adapter.addHeaderView(headerView);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_gridlayout_test;
    }

    @Override
    public int getStatusBarColor() {
        return getResources().getColor(R.color.colorPrimary);
    }

    private class SimpleItemDecoration extends ItemDecoration {
        private final String TAG = SimpleItemDecoration.class.getSimpleName();
        private int padding;

        public SimpleItemDecoration(int padding) {
            this.padding = padding;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
            if (layoutManager instanceof GridLayoutManager) {
                GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;

                int spanCount = gridLayoutManager.getSpanCount();
                GridLayoutManager.SpanSizeLookup spanSizeLookup = gridLayoutManager.getSpanSizeLookup();


                int adapterPosition = parent.getChildAdapterPosition(view);
                int spanIndex = spanSizeLookup.getSpanIndex(adapterPosition, spanCount);
                int spanSize = spanSizeLookup.getSpanSize(adapterPosition);
                Log.d(TAG, "position: " + adapterPosition + ", spanIndex: " + spanIndex);

                if (spanSize == spanCount) {
                    Log.d(TAG, "--------------------------");
                    return;
                }

                int halfPadding = padding / 2;
                outRect.bottom = halfPadding;
                outRect.top = halfPadding;
//                outRect.left = halfPadding;
//                outRect.right = halfPadding;

                if (spanIndex == 0) {
                    outRect.left = padding * 2;
                } else if (spanIndex == spanCount - 1) {
                    outRect.right = padding * 2;
                } else {
                    outRect.left = padding;
                    outRect.right = padding;
                }

                Log.d(TAG, "rect: " + outRect.toShortString());
                Log.d(TAG, "--------------------------");
            }
        }
    }

    private class SimpleGridAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
        private int padding;
        private int spanCount;

        public SimpleGridAdapter(int spanCount, int padding, @Nullable List<String> data) {
            super(R.layout.item_item, data);
            this.padding = padding;
            this.spanCount = spanCount;
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
            TextView textView = helper.getView(R.id.tv_text);
            textView.setText(item);

            textView.post(()->{
                ViewGroup.LayoutParams lp = textView.getLayoutParams();
//                int space = (DisplayUtil.getScreenWidth(mContext) - (spanCount + 3) * padding) / spanCount;
//                Log.d(TAG, "space: " + space);
//                lp.width = space;
//                lp.height = space;
                textView.setLayoutParams(lp);
            });
        }
    }
}
