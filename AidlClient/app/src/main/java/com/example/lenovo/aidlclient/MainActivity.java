package com.example.lenovo.aidlclient;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.lenovo.aidltest.IBook;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    //自定义的接口，和服务端一样
    private IBook binder;
    private ServiceConnection serviceConnection;
    private Button btn;
    private Button btn2;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        serviceConnection=new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {

                binder=IBook.Stub.asInterface(service);
                Log.e("service","connect");
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                Log.e("service","failed");

            }
        };
        Intent intent=new Intent();
        intent.setAction("com.example.lenovo.aidltest.AidlService");
        intent.setPackage("com.example.lenovo.aidltest");
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
        btn=(Button)findViewById(R.id.button);
        btn2=(Button)findViewById(R.id.button2);
        textView=(TextView)findViewById(R.id.textView);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("client","click");
                try {
                    //Book book= binder.show();
                    List<Book> bookList=binder.show();
                    textView.setText(""+bookList.get(0).getId());
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("client","click2");

                try {
                    Book book=new Book(10);
                    List<Book> list=new ArrayList<Book>();
                    list.add(book);
                    binder.save(list);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
