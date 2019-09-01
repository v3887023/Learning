package com.example.learning;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "LifeCyclge: " + TAG + " - onCreate");

        List<String> list = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            list.add(String.valueOf(i));
        }
//        ListView listView = findViewById(R.id.list_view);
//        listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list));

        RecyclerView recyclerView = findViewById(R.id.list_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_text, list) {
            @Override
            protected void convert(BaseViewHolder helper, String item) {
                helper.setText(R.id.tv_text, item);
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        Log.d(TAG, "LifeCycle: " + TAG + " - onRestart");
    }

    @Override
    protected void onStart() {
        super.onStart();

        Log.d(TAG, "LifeCycle: " + TAG + " - onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d(TAG, "LifeCycle: " + TAG + " - onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.d(TAG, "LifeCycle: " + TAG + " - onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();

        Log.d(TAG, "LifeCycle: " + TAG + " - onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.d(TAG, "LifeCycle: " + TAG + " - onDestroy");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Log.d(TAG, "LifeCycle: " + TAG + " - onBackPressed");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        Log.d(TAG, "LifeCycle: " + TAG + " - onSaveInstanceState");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        Log.d(TAG, "LifeCycle: " + TAG + " - onRestoreInstanceState");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        Log.d(TAG, "LifeCycle: " + TAG + " - onNewIntent");
    }
}
