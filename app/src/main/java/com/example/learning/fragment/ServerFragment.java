package com.example.learning.fragment;

import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.learning.BaseFragment;
import com.example.learning.Instantiable;
import com.example.learning.R;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by zhangcx@nemo-inc.com
 * Date: 2019-09-05
 * <p>
 * Description:
 */
public class ServerFragment extends BaseFragment {

    public static final Instantiable<ServerFragment> FACTORY = args -> new ServerFragment();
    private static final String TAG = ServerFragment.class.getSimpleName();
    private volatile boolean running = false;

    @Override
    protected void intViews() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Thread thread = new Thread(() -> {
            try {
                ServerSocket serverSocket = new ServerSocket(10086);
                while (running) {
                    Socket socket = serverSocket.accept();
                    Log.d(TAG, "新连接：" + socket.getInetAddress());
                    File dcimDirectory = new File(Environment.getExternalStorageDirectory(), "DCIM");
                    File[] files = dcimDirectory.listFiles();
                    File file = null;
                    if (files != null) {
                        int i = 0;
                        do {
                            file = files[i];
                        } while (!file.isFile());
                    }

                    if (file != null) {
                        FileInputStream fis = new FileInputStream(file);
                        byte[] buf = new byte[1024];
                        OutputStream os = socket.getOutputStream();
                        int len;
                        while ((len = fis.read(buf)) != -1) {
                            os.write(buf, 0, len);
                        }
                        os.flush();
                        fis.close();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
//        thread.start();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageView imageIv = findViewById(R.id.iv_image);
        Glide.with(this).load("192.168.43.20:10086").into(imageIv);

        new Thread(()->{
            OkHttpClient okHttpClient = new OkHttpClient();
            Request request = new Request.Builder().url("http://192.168.43.20:10086").build();
            okHttpClient.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    Log.d(TAG, "response: " + response.code());
                }
            });
        }).start();

//        new Thread(()->{
//            try {
//                Socket socket = new Socket("192.168.43.20", 10086);
//                InputStream is = socket.getInputStream();
////                byte[] buf = new byte[1024];
////                int len;
////                ByteArrayOutputStream baos = new ByteArrayOutputStream();
////                while ((len = is.read(buf)) != -1) {
////                    baos.write(buf, 0, len);
////                }
////
////                String message = new String(baos.toByteArray());
////                Log.d(TAG, "message: " + message);
//
//                StringBuilder sb = new StringBuilder();
//                String line;
//                BufferedReader reader = new BufferedReader(new InputStreamReader(is));
//                while ((line = reader.readLine()) != null) {
//                    sb.append(line);
//                }
//
//                Log.d(TAG, "message: " + sb.toString());
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }).start();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_server;
    }
}
