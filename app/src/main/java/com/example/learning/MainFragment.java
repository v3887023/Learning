package com.example.learning;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;

import com.example.learning.fragment.AidlFragment;
import com.example.learning.fragment.FlowLayoutFragment;
import com.example.learning.fragment.ImageFragment;
import com.example.learning.fragment.FullscreenVideoFragment;
import com.example.learning.fragment.GridLayoutTestFragment;
import com.example.learning.fragment.ListViewTestFragment;
import com.example.learning.fragment.OrientationTestFragment;
import com.example.learning.fragment.PhotoFragment;
import com.example.learning.fragment.PopupTopFragment;
import com.example.learning.fragment.ScrollerTestFragment;
import com.example.learning.fragment.ServerFragment;
import com.example.learning.fragment.StickyLayoutFragment;

import java.util.List;

public class MainFragment extends BaseFragment {
    private RecyclerView subjectRv;
    private SubjectAdapter subjectAdapter;
    private List<Subject> subjectList;

    @Override
    protected void intViews() {
        SubjectManager subjectManager = SubjectManager.get();
        SubjectManager.Creator creator = subjectManager.creator();
        creator.register(new Subject("StickyLayout", "自定义的 StickyLayout，不过目前还不完善"), StickyLayoutFragment.FACTORY);
        creator.register(new Subject("ListView 动态设置宽度", "项目里遇到要动态设置 ListView 的宽度，在这里测试一下", true), ListViewTestFragment.FACTORY);
        creator.register(new Subject("旋转屏幕", "BaseFragment 增加控制屏幕方向的方法", true), OrientationTestFragment.FACTORY);
        creator.register(new Subject("图片", "测试 Fragment 的 SharedElement 效果", false), PhotoFragment.FACTORY);
        creator.register(new Subject("顶部弹窗", "模仿系统通知的应用内弹窗"), PopupTopFragment.FACTORY);
        creator.register(new Subject("滑动测试", "滑动、速度测试"), ScrollerTestFragment.FACTORY);
        creator.register(new Subject("Grid Layout 测试", "动态设置 item 的宽高", true), GridLayoutTestFragment.FACTORY);
        creator.register(new Subject("FlowLayout", "尝试自定义一个流式布局 FlowLayout"), FlowLayoutFragment.FACTORY);
        creator.register(new Subject("AIDL", "学习 AIDL"), AidlFragment.FACTORY);
        creator.register(new Subject("文件服务器", "试试能否用 Socket 来给浏览器传文件"), ServerFragment.FACTORY);
        creator.register(new Subject("视频切换全屏测试", "看看能够用别的方式实现全屏切换"), FullscreenVideoFragment.FACTORY);
        creator.register(new Subject("图片浏览", "图片浏览功能，点击看大图，下滑返回"), ImageFragment.FACTORY);

        subjectList = creator.create();

        subjectRv = findViewById(R.id.rv_subject);
        subjectRv.setLayoutManager(new LinearLayoutManager(activity));
        subjectAdapter = new SubjectAdapter(subjectList);

        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);

        int padding = (int) (dm.density * 8);
        subjectRv.addItemDecoration(new SimpleItemDecoration(padding));
        subjectRv.setAdapter(subjectAdapter);

        subjectAdapter.setOnItemClickListener((adapter, view, position) -> {
            Instantiable instantiable = subjectManager.getInstantiableBySubject(subjectList.get(position));
            if (instantiable != null) {
                Object o = instantiable.newInstance(null);
                if (o instanceof BaseFragment) {
                    FragmentUtil.loadFragment(getFragmentManager(), R.id.fragment_container, this, (BaseFragment) o, true);
                } else if (o instanceof Activity) {
                    Intent intent = new Intent(activity, o.getClass());
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_main;
    }
}
