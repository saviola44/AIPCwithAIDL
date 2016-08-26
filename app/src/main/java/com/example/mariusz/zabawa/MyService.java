package com.example.mariusz.zabawa;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Toast;


public class MyService extends Service {
    int licznik=0;
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d("MYService", "Service onBind" + licznik);

        return mBinder;
    }

    private final MyAIDLAPIV1.Stub mBinder = new MyAIDLAPIV1.Stub() {
        @Override
        public void sayHello() throws RemoteException {
            Log.d("MYService", "sayHello method: hello");
        }

        @Override
        public void displayParcel(MessageParcel m) throws RemoteException {
            Log.d("MYService", "displayParcel method");
            displayData(m);
        }
    };


    public void displayData(MessageParcel m){
        Log.d("MYService", "metoda display Data: " + m.getMessage1() + " " + m.getMessageInt());
        //ponizsze nie zadziala bo trzeba Callbacka jeszcze zrobic, bo tak nie da rady w IPC wywolac
        /*Toast.makeText(getApplicationContext(),m.getMessage1() + " " + m.getMessageInt(),
                Toast.LENGTH_LONG).show();*/

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        licznik++;
        Log.d("MYService", "Service onStartCommand " + licznik);
        return Service.START_NOT_STICKY;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("MYService", "Service onCreate hello " + licznik);
    }

    @Override
    public void onDestroy() {
        Log.d("MYService", "Service onDestroy");
        super.onDestroy();
    }
}
