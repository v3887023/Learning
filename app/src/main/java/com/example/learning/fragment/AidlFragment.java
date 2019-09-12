package com.example.learning.fragment;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.os.IBinder;
import android.os.RemoteException;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.learning.BaseFragment;
import com.example.learning.Instantiable;
import com.example.learning.R;
import com.example.learning.aidl.IRemoteService;
import com.example.learning.aidl.RemoteService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangcx@nemo-inc.com
 * Date: 2019-09-05
 * <p>
 * Description:
 */
public class AidlFragment extends BaseFragment {

    public static final Instantiable<AidlFragment> FACTORY = args -> new AidlFragment();

    private Button bindButton;
    private Button unbindButton;
    private Button getButton;
    private ListView listView;
    private TextView stateTv;
    private List<String> list;
    private IRemoteService remoteService;
    private boolean serviceBinded = false;
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            remoteService = IRemoteService.Stub.asInterface(service);
            serviceBinded = true;
            showText("服务已绑定", false);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (serviceBinded) {
            activity.unbindService(serviceConnection);
        }
    }

    @Override
    protected void intViews() {
        bindButton = findViewById(R.id.btn_bind);
        unbindButton = findViewById(R.id.btn_unbind);
        getButton = findViewById(R.id.btn_get);
        stateTv = findViewById(R.id.tv_state);
        listView = findViewById(R.id.list_view);

        list = new ArrayList<>();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(activity, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);

        bindButton.setOnClickListener(v -> {
            if (serviceBinded) {
                showText("服务已绑定", true);
            } else {
                Intent intent = new Intent(activity, RemoteService.class);
                activity.bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
            }
        });

        unbindButton.setOnClickListener(v -> {
            if (serviceBinded) {
                activity.unbindService(serviceConnection);
                serviceBinded = false;
                showText("服务已解绑", false);
            } else {
                showText("服务未绑定", true);
            }
        });

        getButton.setOnClickListener(v -> {
            if (serviceBinded) {
                try {
                    list.add(0, String.valueOf(remoteService.getRandomNumber()));
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                adapter.notifyDataSetChanged();
                showText("服务已绑定", false);
            } else {
                showText("服务未绑定", true);
            }
        });
    }

    private void showText(String text, boolean error) {
        stateTv.setText(text);
        if (error) {
            stateTv.setTextColor(Color.RED);
        } else {
            stateTv.setTextColor(Color.GREEN);
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_aidl;
    }
}
