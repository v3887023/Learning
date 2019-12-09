package com.example.learning;

import android.os.Build;
import android.support.annotation.IdRes;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.util.Pair;
import android.transition.ChangeBounds;
import android.transition.ChangeImageTransform;
import android.transition.ChangeTransform;
import android.transition.Fade;
import android.transition.TransitionSet;
import android.view.View;

import java.util.Collections;
import java.util.List;

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

    public static void loadFragment(FragmentManager fragmentManager, @IdRes int id, Fragment fromFragment, Fragment toFragment, Pair<View, String> pair) {
        loadFragment(fragmentManager, id, fromFragment, toFragment, Collections.singletonList(pair));
    }


    public static void loadFragment(FragmentManager fragmentManager, @IdRes int id, Fragment fromFragment, Fragment toFragment, List<Pair<View, String>> pairList) {
        if (fragmentManager == null) {
            throw new IllegalArgumentException("fragmentManager must not be null");
        }

        if (toFragment == null) {
            throw new IllegalArgumentException("toFragment must not be null");
        }


        toFragment.setSharedElementEnterTransition(new PhotoTransition());
        toFragment.setSharedElementReturnTransition(new PhotoTransition());
        toFragment.setEnterTransition(new Fade());

        if (fromFragment != null) {
            fromFragment.setExitTransition(new Fade());
        }

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setReorderingAllowed(true);
        if (pairList != null) {
            for (Pair<View, String> pair : pairList) {
                transaction.addSharedElement(pair.first, pair.second);
            }
        }

        transaction.replace(id, toFragment);

        transaction.addToBackStack(toFragment.toString()).commit();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private static class PhotoTransition extends TransitionSet {
        public PhotoTransition() {
            setOrdering(ORDERING_TOGETHER);
            addTransition(new ChangeBounds()).
                    addTransition(new ChangeTransform()).
                    addTransition(new ChangeImageTransform());
        }
    }
}
