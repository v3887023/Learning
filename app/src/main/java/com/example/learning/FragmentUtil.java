package com.example.learning;

import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

public class FragmentUtil {

    public static void loadFragment(FragmentManager fragmentManager, @IdRes int id, Fragment fromFragment, Fragment toFragment, boolean addToBackStack) {
        loadFragment(fragmentManager, id, fromFragment, toFragment, addToBackStack, true);
    }

    public static void loadFragment(FragmentManager fragmentManager, @IdRes int id, Fragment fromFragment, Fragment toFragment, boolean addToBackStack, boolean animate) {
        if (fragmentManager == null) {
            throw new IllegalArgumentException("fragmentManager must not be null");
        }

        if (toFragment == null) {
            throw new IllegalArgumentException("toFragment must not be null");
        }

        FragmentTransaction transaction = fragmentManager.beginTransaction();

        if (animate) {
            transaction.setCustomAnimations(R.animator.slide_left_in, R.animator.slide_left_out, R.animator.slide_right_in, R.animator.slide_right_out);
        }

        transaction.add(id, toFragment);

        if (fromFragment != null) {
            transaction.hide(fromFragment);
        }

        if (addToBackStack) {
            transaction.addToBackStack(toFragment.toString());
        }

        transaction.commit();
    }
}
