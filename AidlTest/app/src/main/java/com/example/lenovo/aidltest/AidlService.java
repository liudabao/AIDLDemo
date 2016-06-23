package com.example.lenovo.aidltest;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;


import java.util.ArrayList;
import java.util.List;

public class AidlService extends Service {
    List list=new ArrayList<Book>();

    private IBook.Stub binder=new IBook.Stub(){

        @Override
        public void save(List<Book> bookList) throws RemoteException {
            Log.e("aidl", "save");
            if(bookList.size()>0){
               for(Book book: bookList){
                   list.add(book);
               }
            }
        }

        @Override
        public List<Book> show() throws RemoteException {
            Log.e("aidl", "show");
            return list;
        }
    };
    @Override
    public  void onCreate(){
        Log.e("service", "ok");
        super.onCreate();
    }
    @Override
    public IBinder onBind(Intent intent) {

       // return null;
        return binder;
    }



}