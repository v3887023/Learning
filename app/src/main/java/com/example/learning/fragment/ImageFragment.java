package com.example.learning.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.learning.BaseFragment;
import com.example.learning.DimensUtils;
import com.example.learning.DisplayUtil;
import com.example.learning.Instantiable;
import com.example.learning.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangcx@nemo-inc.com
 * Date: 2019-09-05
 * <p>
 * Description:
 */
public class ImageFragment extends BaseFragment {

    public static final Instantiable<ImageFragment> FACTORY = args -> new ImageFragment();
    private static final int MAX_VIDEO_COUNT = 20;
    private static final String TAG = ImageFragment.class.getSimpleName();
    private static int maxOffsetY;
    private FrameLayout fullscreenContainer;
    private List<String> imagePathList;
    private ImageAdapter imageAdapter;
    private RecyclerView imageRv;
    private ViewGroup parent;
    private ImageView imageView;
    private float lastY;
    private float offsetY;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        maxOffsetY = DisplayUtil.getScreenHeight(activity) / 8;

        imagePathList = new ArrayList<>();
        imageAdapter = new ImageAdapter(imagePathList);

        File rootFile = Environment.getExternalStorageDirectory();
        new Thread(() -> findFirstVideoPathInFile(rootFile)).start();
    }

    private void findFirstVideoPathInFile(File file) {
        if (file != null) {
            File[] files = file.listFiles();
            if (files != null) {
                for (File f : files) {
                    if (f.getName().contains(".jpg")) {
                        if (imagePathList.size() < MAX_VIDEO_COUNT) {
                            imagePathList.add(f.getAbsolutePath());
                        } else {
                            runOnUiThread(() -> imageAdapter.notifyDataSetChanged());
                            return;
                        }
                    }

                    findFirstVideoPathInFile(f);
                }
            }
        }
    }

    @Override
    protected void intViews() {
        fullscreenContainer = findViewById(R.id.fullscreen_container);

        imageRv = findViewById(R.id.rv_image);
        imageRv.setLayoutManager(new GridLayoutManager(activity, 3));
        imageRv.setAdapter(imageAdapter);
        imageAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            if (view.getId() == R.id.iv_image && fullscreenContainer.getChildCount() == 0) {
                imageView = (ImageView) view;
                setStatusBar(Color.BLACK);
                imageView.setOnTouchListener((v, event) -> {
                    if (fullscreenContainer.getChildCount() == 0) {
                        return false;
                    }


                    Log.d(TAG, "onTouch");
                    float y = event.getY();
                    switch (event.getActionMasked()) {
                        case MotionEvent.ACTION_DOWN:
                            lastY = y;
                            break;
                        case MotionEvent.ACTION_MOVE:
                            float offsetY = y - lastY;
                            lastY = y;
                            if (offsetY > 0 || this.offsetY > 0) {
                                this.offsetY += offsetY;
                            }

                            if (offsetY > 0 || this.offsetY > 0) {
                                imageView.scrollBy(0, (int) -offsetY);

                                float alpha;
                                if (this.offsetY >= maxOffsetY) {
                                    alpha = 0;
                                } else {
                                    alpha = 1 - this.offsetY / maxOffsetY;
                                }


                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                    int color = Color.argb(alpha, 0, 0, 0);
                                    fullscreenContainer.setBackgroundColor(color);
                                    setStatusBar(color);
                                }
                            } else {
                                fullscreenContainer.setBackgroundColor(Color.BLACK);
                                setStatusBar(Color.BLACK);
                            }
                            break;
                        case MotionEvent.ACTION_UP:
                            if (this.offsetY < maxOffsetY) {
                                imageView.scrollBy(0, (int) this.offsetY);
                                setStatusBar(Color.BLACK);
                                fullscreenContainer.setBackgroundColor(Color.BLACK);
                            } else {
                                fullscreenContainer.removeView(imageView);
                                parent.addView(imageView, new ViewGroup.LayoutParams(DimensUtils.dip2pixel(activity, 100), DimensUtils.dip2pixel(activity, 100)));
                                fullscreenContainer.setVisibility(View.GONE);
                                imageView.scrollTo(0, 0);
                                setStatusBar(getStatusBarColor());
                            }
                            fullscreenContainer.setBackgroundColor(Color.BLACK);
                            this.offsetY = 0;
                            break;
                    }
                    return false;
                });
                parent = (ViewGroup) imageView.getParent();
                parent.removeView(imageView);
                parent.removeView(parent);
                fullscreenContainer.addView(imageView);
                fullscreenContainer.setVisibility(View.VISIBLE);
                ViewGroup.LayoutParams lp = imageView.getLayoutParams();
                lp.width = DisplayUtil.getPortraitScreenWidth(activity);
                lp.height = DisplayUtil.getPortraitScreenHeight(activity);
                imageView.setLayoutParams(lp);
            }
        });
    }

    @Override
    public boolean handleBackPressedEvent() {
        if (fullscreenContainer.getChildCount() > 0) {
            setStatusBar(getStatusBarColor());
            fullscreenContainer.removeView(imageView);
            parent.addView(imageView, new ViewGroup.LayoutParams(DimensUtils.dip2pixel(activity, 100), DimensUtils.dip2pixel(activity, 100)));
            fullscreenContainer.setVisibility(View.GONE);
            return true;
        }

        return false;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_fullscreen_image;
    }

    private class ImageAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
        public ImageAdapter(@Nullable List<String> data) {
            super(R.layout.item_image, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
            new Thread(() -> {
                Bitmap bitmap = BitmapFactory.decodeFile(item);
                runOnUiThread(() -> {
                    helper.setImageBitmap(R.id.iv_image, bitmap);
                });
            }).start();
            helper.addOnClickListener(R.id.iv_image);
        }
    }
}
