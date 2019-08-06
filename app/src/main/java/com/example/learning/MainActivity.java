package com.example.learning;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private ImageView photoIv;
    private ProgressBar progressBar;
    private EditText messageEt;
    private TextView pathTv;

    private static int bytesToInt(byte[] bytes) {
        return (bytes[3] & 0xff) | ((bytes[2] & 0xff) << 8) | ((bytes[1] & 0xff) << 16) | ((bytes[0] & 0xff) << 24);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startActivity(new Intent(this, SecondActivity.class));

        Log.d(TAG, "LifeCycle: " + TAG + " - onCreate");

        photoIv = findViewById(R.id.iv_photo);
        progressBar = findViewById(R.id.progress_bar);
        messageEt = findViewById(R.id.et_message);
        pathTv = findViewById(R.id.tv_path);

        messageEt.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEND) {
                final String text = messageEt.getText().toString();
                if (TextUtils.isEmpty(text)) {
                    return false;
                }
                messageEt.setText(null);
                photoIv.setImageBitmap(null);
                progressBar.setProgress(0);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Socket socket = new Socket("192.168.1.100", 10086);
                            String message = text + "\n";
                            socket.getOutputStream().write(message.getBytes());

                            InputStream is = socket.getInputStream();
                            byte[] buff = new byte[1024];
                            int i = 0;
                            int n;
                            while ((n = is.read()) != -1) {
                                if (n == '\n') {
                                    break;
                                }
                                buff[i++] = (byte) n;
                            }

                            final String text = new String(buff, 0, i);
                            Log.d(TAG, "text: " + text);
                            runOnUiThread(() -> pathTv.setText(text));

                            byte[] bytes = new byte[4];
                            final int read = is.read(bytes, 0, 4);
                            Log.d(TAG, "read count: " + read);

                            int len;
                            byte[] buf = new byte[1024];
                            final File parent = new File(Environment.getExternalStorageDirectory(), "Files");
                            final boolean mkdirs = parent.mkdirs();
                            Log.d(TAG, "mkdirs: " + mkdirs);
                            final File file = new File(parent, "1.jpg");
                            final boolean newFile = file.createNewFile();
                            Log.d(TAG, "newFile: " + newFile);
                            FileOutputStream fos = new FileOutputStream(file);
                            int totalLength = 0;

                            totalLength = bytesToInt(bytes);

                            Log.d(TAG, "totalLength: " + totalLength);
                            int sum = 0;
                            int progress;
                            int lastProgress = -1;
                            while ((len = is.read(buf)) != -1) {
                                fos.write(buf, 0, len);
                                sum += len;
                                progress = 100 * sum / totalLength;
                                if (progress >= lastProgress + 1) {
                                    Log.d(TAG, "progress:" + progress);
                                    lastProgress = progress;
                                    final int finalProgress = progress;
                                    runOnUiThread(() -> {
                                        if (finalProgress == 100) {
                                            photoIv.setImageBitmap(BitmapFactory.decodeFile(file.getAbsolutePath()));
                                        }
                                        progressBar.setProgress(finalProgress);
                                    });
                                }
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
                return true;
            }
            return false;
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
