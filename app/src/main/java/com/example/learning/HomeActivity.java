package com.example.learning;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;

import java.util.List;

public class HomeActivity extends AppCompatActivity {
    private RecyclerView subjectRv;
    private SubjectAdapter subjectAdapter;
    private List<Subject> subjectList;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        fragmentManager = getSupportFragmentManager();

        SubjectManager subjectManager = SubjectManager.get();
        SubjectManager.Creator creator = subjectManager.creator();
        creator.register(new Subject("A", "123"), StickyLayoutFragment.FACTORY)
                .register(new Subject("B", "456"), StickyLayoutFragment.FACTORY)
                .register(new Subject("C", "789"), StickyLayoutFragment.FACTORY)
                .register(new Subject("D", "012"), StickyLayoutFragment.FACTORY)
                .register(new Subject("E", "345"), StickyLayoutFragment.FACTORY);

        subjectList = creator.create();

        subjectRv = findViewById(R.id.rv_subject);
        subjectRv.setLayoutManager(new LinearLayoutManager(this));
        subjectAdapter = new SubjectAdapter(subjectList);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int padding = (int) (dm.density * 1);
        subjectRv.addItemDecoration(new SimpleItemDecoration(padding));
        subjectRv.setAdapter(subjectAdapter);

        subjectAdapter.setOnItemClickListener((adapter, view, position) -> {
            Instantiable instantiable = subjectManager.getInstantiableBySubject(subjectList.get(position));
            if (instantiable != null) {
                Object o = instantiable.newInstance(null);
                if (o instanceof BaseFragment) {
                    loadFragment((BaseFragment) o);
                } else if (o instanceof Activity) {
                    Intent intent = new Intent(this, o.getClass());
                    startActivity(intent);
                }
            }
        });
    }

    private void loadFragment(BaseFragment fragment) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.fragment_container, fragment);
        transaction.commit();
    }
}
