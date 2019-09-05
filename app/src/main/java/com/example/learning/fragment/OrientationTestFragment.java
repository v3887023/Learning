package com.example.learning.fragment;

import com.example.learning.BaseFragment;
import com.example.learning.Instantiable;
import com.example.learning.R;

/**
 * Created by zhangcx@nemo-inc.com
 * Date: 2019-09-05
 * <p>
 * Description:
 */
public class OrientationTestFragment extends BaseFragment {

    public static final Instantiable<OrientationTestFragment> FACTORY = args -> new OrientationTestFragment();

    @Override
    protected void intViews() {
        findViewById(R.id.btn_test).setOnClickListener(v -> {
            if (isOrientationPortrait()) {
                requestOrientationLandscape();
            } else {
                requestOrientationPortrait();
            }
        });
    }

    @Override
    public boolean fullScreen() {
        return true;
    }

    @Override
    public boolean handleBackPressedEvent() {
        restoreOrientation();

        return super.handleBackPressedEvent();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_orientation_test;
    }
}
