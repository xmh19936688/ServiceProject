package serviceproject.startservicemoretimes;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MainService extends Service {

    private int count=0;
    private Thread thread;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    private void initThread() {
        thread=new Thread(new Runnable() {
            @Override
            public void run() {
                while (count<10){
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Log.e("xmh-service","count:"+count);
                    count++;
                }
                count=0;
            }
        });
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("xmh-service","start");
        if (thread!=null&&thread.isAlive()){
            //线程正在run
        }else{
            //如果线程已经执行完run方法（进入死亡状态），再想start该线程需要重新new
            initThread();
            thread.start();
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.e("xmh-service","stop");
        super.onDestroy();
    }
}
