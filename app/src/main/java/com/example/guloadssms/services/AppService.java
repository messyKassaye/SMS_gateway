package com.example.guloadssms.services;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class AppService extends Service {
    private Context context;
    private SmsSenderTask task;

    public AppService(Context context) {
        this.context = context;

        task = new SmsSenderTask(context);

    }

    public AppService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Intent broadcastIntent = new Intent(this.context,ServiceStarterReceiver.class);
        sendBroadcast(broadcastIntent);
        task.stoptimertask();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
