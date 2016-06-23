// IBook.aidl
package com.example.lenovo.aidltest;


// Declare any non-default types here with import statements
import com.example.lenovo.aidlclient.book;

interface IBook {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
     void save(in List<Book> bookList);
     List<Book> show();

}
