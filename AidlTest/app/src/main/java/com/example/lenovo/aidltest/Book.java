package com.example.lenovo.aidltest;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by lenovo on 2016/6/23.
 */
public class Book implements Parcelable {
    private int id;

    public Book() {

    }

    public Book(int id) {
        this.id=id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
    }

    public static final Creator CREATOR = new Creator(){

        @Override
        public Book createFromParcel(Parcel source) {
            // TODO Auto-generated method stub
            // 必须按成员变量声明的顺序读取数据，不然会出现获取数据出错
            return new Book(source.readInt());
        }

        @Override
        public Book[] newArray(int size) {
            // TODO Auto-generated method stub
            return new Book[size];
        }
    };

}
