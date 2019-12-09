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

        int size = 0;
        if (fragments != null) {
            for (Fragment fragment : fragments) {
                if (fragment instanceof BaseFragment) {
                    size++;
                }
            }
        }

        if (size > 0) {
            int i = fragments.size() - 1;
            while (i > 0) {
                Fragment fragment = fragments.get(i);
                if (fragment instanceof BaseFragment) {
                    if (((BaseFragment) fragment).handleBackPressedEvent()) {
                        return;
                    }
                }

                i--;
            }
        }

        super.onBackPressed();
    }
}
