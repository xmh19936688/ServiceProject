package serviceproject.simpleservicedemo;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

public class SimpleService extends Service {

    private static final int NOTIFICATION_ID = 1001;

    int count = 0;
    private NotificationCompat.Builder builder;
    private ProgressThread progressThread;
    private boolean isFinish=false;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("xmh-service", "created");
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        Log.e("xmh-service", "start");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("xmh-service", "start-command");
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
        builder = new NotificationCompat.Builder(this);
        builder.setOngoing(true)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.drawable.ic_launcher)
                .setTicker("ticker")
                .setContentTitle("title")
                .setContentText("content" + count)
                .setContentIntent(pendingIntent);
        startForeground(NOTIFICATION_ID, builder.build());
        isFinish=false;
        progressThread = new ProgressThread();
        progressThread.start();
        return super.onStartCommand(intent, flags, startId);
    }

    void updateNotify() {
        count++;
        builder.setContentText("content" + count);

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }

    void removeNotify(){
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.cancel(NOTIFICATION_ID);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e("xmh-service", "bind");
        return null;
    }

    @Override
    public void onDestroy() {
        Log.e("xmh-service", "destory");
        isFinish=true;
        super.onDestroy();
    }

    class ProgressThread extends Thread {
        @Override
        public void run() {
            super.run();
            while (true) {
                Log.e("xmh-service","run"+count);
                if(isFinish){
                    removeNotify();
                    return;
                }
                try {
                    Thread.sleep(300);
                    updateNotify();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
