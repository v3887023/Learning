package com.example.learning.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.util.Pair;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.learning.BaseFragment;
import com.example.learning.FragmentUtil;
import com.example.learning.Instantiable;
import com.example.learning.PhotoBean;
import com.example.learning.R;
import com.example.learning.SimpleItemDecoration;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by zhangcx@nemo-inc.com
 * Date: 2019-09-01
 * <p>
 * Description:
 */
public class PhotoFragment extends BaseFragment {
    public final static Instantiable<PhotoFragment> FACTORY = args -> new PhotoFragment();
    private RecyclerView photoRv;
    private List<PhotoBean> photoBeans = new ArrayList<>();
    private PhotoAdapter photoAdapter = new PhotoAdapter(photoBeans);
    private static final String BASE_URL = "https://picsum.photos/v2/list";
    private int page = 1;
    private Gson gson = new Gson();

    @Override
    protected void intViews() {
        photoRv = findViewById(R.id.rv_photo);
        photoRv.setAdapter(photoAdapter);
        photoRv.setLayoutManager(new GridLayoutManager(activity, 2));
        photoRv.addItemDecoration(new SimpleItemDecoration(10));
        photoAdapter.setOnLoadMoreListener(this::requestData, photoRv);
        photoAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            ViewCompat.setTransitionName(view, "image_" + position);
            PhotoFullscreenFragment photoFullscreenFragment = PhotoFullscreenFragment.newInstance(photoBeans.get(position));
            FragmentUtil.loadFragment(getFragmentManager(), R.id.fragment_container, this, photoFullscreenFragment, Pair.create(view, "photo"));
        });
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestData();
    }

    private void requestData() {
        new Thread(() -> {
            OkHttpClient okHttpClient = new OkHttpClient();
            String url = BASE_URL + "?page=" + page;
            Request request = new Request.Builder().url(url).build();
            okHttpClient.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    runOnUiThread(() -> photoAdapter.loadMoreFail());
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    page++;
                    PhotoBean[] photoBeans = gson.fromJson(response.body().string(), PhotoBean[].class);
                    runOnUiThread(() -> {
                        if (photoBeans == null || photoBeans.length == 0) {
                            photoAdapter.loadMoreEnd();
                        } else {
                            photoAdapter.loadMoreComplete();
                            photoAdapter.addData(Arrays.asList(photoBeans));
                        }
                    });
                }
            });
        }).start();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_photo;
    }

    private static class PhotoAdapter extends BaseQuickAdapter<PhotoBean, BaseViewHolder> {

        public PhotoAdapter(@Nullable List<PhotoBean> data) {
            super(R.layout.item_photo, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, PhotoBean item) {
            ImageView photoIv = helper.getView(R.id.iv_photo);
            Glide.with(photoIv).load(item.getThumUrl()).into(photoIv);
            helper.setText(R.id.tv_author, item.getAuthor());

            helper.addOnClickListener(R.id.iv_photo);
        }
    }
}
