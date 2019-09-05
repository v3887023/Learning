package com.example.learning.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.learning.BaseFragment;
import com.example.learning.Instantiable;
import com.example.learning.R;
import com.example.learning.SimpleItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangcx@nemo-inc.com
 * Date: 2019-09-01
 * <p>
 * Description:
 */
public class StickyLayoutFragment extends BaseFragment {
    public final static Instantiable<StickyLayoutFragment> FACTORY = args -> new StickyLayoutFragment();

    @Override
    protected void intViews() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            list.add(String.valueOf(i));
        }

        RecyclerView recyclerView = findViewById(R.id.list_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));

        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);

        int padding = (int) (dm.density * 8);

        recyclerView.addItemDecoration(new SimpleItemDecoration(padding));
        recyclerView.setAdapter(new BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_text, list) {
            @Override
            protected void convert(BaseViewHolder helper, String item) {
                helper.setText(R.id.tv_text, item);
            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_sticky_layout;
    }

    @Override
    public int getStatusBarColor() {
        return getResources().getColor(android.R.color.holo_blue_light);
    }
}
