package com.example.mariusz.zabawa;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button newServiceBtn;
    Button endServiceBtn;
    Button sendServiceBtn;
    MyAIDLAPIV1 myAIDLService;
    ServiceConnection con = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            myAIDLService = MyAIDLAPIV1.Stub.asInterface(iBinder);

            //myService = ((MyService.LocalBinder)iBinder).getService();
            //int x = ((MyService.LocalBinder)iBinder).getZero();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            myAIDLService = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        newServiceBtn = (Button) findViewById(R.id.newServicebtn);
        endServiceBtn = (Button) findViewById(R.id.endServicebtn);
        sendServiceBtn = (Button) findViewById(R.id.sendServicebtn);
        newServiceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myService = new Intent(getApplicationContext(), MyService.class);
                Log.d("MYService", "uruchamiam nową usługe");
                //startService(myService);
                bindService(myService, con, BIND_AUTO_CREATE);
            }
        });
        endServiceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(myAIDLService!=null){
                    Log.d("MYService", "onClick, zatrzymuje usługe");
                    unbindService(con);
                    myAIDLService=null;
                }
            }
        });
        sendServiceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "wysyłam dane do usługi", Toast.LENGTH_LONG).show();
                Log.d("MYService", "onClick, wysylam dane do usługi");
                MessageParcel m = new MessageParcel("jakas wiadomosc", 321);
                try {
                    myAIDLService.sayHello();
                    myAIDLService.displayParcel(m);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
