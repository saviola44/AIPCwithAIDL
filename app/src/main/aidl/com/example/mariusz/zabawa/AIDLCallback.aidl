package com.example.mariusz.zabawa;
import com.example.mariusz.zabawa.MessageParcel;

// Declare any non-default types here with import statements

interface AIDLCallback {
    void displayResponseFromService(in MessageParcel m);
}
