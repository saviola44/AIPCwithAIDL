package com.example.mariusz.zabawa;
import com.example.mariusz.zabawa.MessageParcel;
import com.example.mariusz.zabawa.AIDLCallback;
interface MyAIDLAPIV1 {
    void sayHello();
    void displayParcel(in MessageParcel m);
    void addCallback(in AIDLCallback c);
}


