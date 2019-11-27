package com.example.learning.fragment;

import com.example.learning.BaseFragment;
import com.example.learning.Instantiable;
import com.example.learning.R;
import com.example.learning.widget.BallView;

/**
 * Created by zhangcx@nemo-inc.com
 * Date: 2019-09-05
 * <p>
 * Description:
 */
public class ScrollerTestFragment extends BaseFragment {

    public static final Instantiable<ScrollerTestFragment> FACTORY = args -> new ScrollerTestFragment();

    @Override
    protected void intViews() {
        BallView ballView = findViewById(R.id.ball);
        findViewById(R.id.btn_recover).setOnClickListener(v -> ballView.recover());
    }

    @Override
    public boolean fullScreen() {
        return true;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_scroller_test;
    }
}
