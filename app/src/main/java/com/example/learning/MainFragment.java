package com.example.learning;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;

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
