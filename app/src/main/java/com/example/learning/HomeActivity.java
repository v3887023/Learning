package com.example.learning;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

public class HomeActivity extends AppCompatActivity {
    FragmentManager fragmentManager;
    private MainFragment mMainFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        fragmentManager = getSupportFragmentManager();

        if (savedInstanceState == null) {

            mMainFragment = new MainFragment();
            FragmentUtil.loadFragment(fragmentManager, R.id.fragment_container, null, mMainFragment, false, false);
        }
    }

    @Override
    public void onBackPressed() {
        List<Fragment> fragments = fragmentManager.getFragments();

        if (fragments != null && fragments.size() > 0) {
            int size = fragments.size();

            Fragment fragment = fragments.get(size - 1);
            if (fragment instanceof BaseFragment) {
                if (((BaseFragment) fragment).handleBackPressedEvent()) {
                    return;
                }
            }
        }

        super.onBackPressed();
    }
}
