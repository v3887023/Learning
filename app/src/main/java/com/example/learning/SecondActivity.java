package com.example.learning;

import android.content.Intent;
import android.os.Bundle;
import android.os.Process;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class SecondActivity extends AppCompatActivity {

    private static final String TAG = SecondActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        setTitle(TAG);

        Log.d(TAG, "LifeCycle: " + TAG + " -- onCreate");
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        Log.d(TAG, "LifeCycle: " + TAG + " -- onRestart");
    }

    @Override
    protected void onStart() {
        super.onStart();

        Log.d(TAG, "LifeCycle: " + TAG + " -- onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d(TAG, "LifeCycle: " + TAG + " -- onResume");
        finish();
    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.d(TAG, "LifeCycle: " + TAG + " -- onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();

        Log.d(TAG, "LifeCycle: " + TAG + " -- onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.d(TAG, "LifeCycle: " + TAG + " -- onDestroy");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Log.d(TAG, "LifeCycle: " + TAG + " -- onBackPressed");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        Log.d(TAG, "LifeCycle: " + TAG + " -- onSaveInstanceState");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        Log.d(TAG, "LifeCycle: " + TAG + " -- onRestoreInstanceState");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        Log.d(TAG, "LifeCycle: " + TAG + " -- onNewIntent");
    }
}
