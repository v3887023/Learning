package com.example.learning.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.util.Random;

/**
 * Created by zhangcx@nemo-inc.com
 * Date: 2019-09-08
 * <p>
 * Description:
 */
public class RemoteService extends Service {
    private IRemoteService.Stub binder;

    public RemoteService() {
        binder = new RemoteBinder();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    public class RemoteBinder extends IRemoteService.Stub {
        private Random random;

        public RemoteBinder() {
            random = new Random(System.currentTimeMillis());
        }

        @Override
        public int getRandomNumber() {
            return random.nextInt(100);
        }
    }
}
