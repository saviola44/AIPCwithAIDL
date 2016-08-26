package com.example.mariusz.zabawa;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by mariusz on 23.08.16.
 */
public class MessageParcel implements Parcelable {
    private String message1;
    private int messageInt;

    public MessageParcel(String message1, int messageInt) {
        this.message1 = message1;
        this.messageInt = messageInt;
    }

    public void setMessage1(String message1) {
        this.message1 = message1;
    }

    public void setMessageInt(int messageInt) {
        this.messageInt = messageInt;
    }

    public int getMessageInt() {

        return messageInt;
    }

    public String getMessage1() {
        return message1;
    }

    protected MessageParcel(Parcel in) {
        message1 = in.readString();
        messageInt = in.readInt();
    }

    public static final Creator<MessageParcel> CREATOR = new Creator<MessageParcel>() {
        @Override
        public MessageParcel createFromParcel(Parcel in) {
            return new MessageParcel(in);
        }

        @Override
        public MessageParcel[] newArray(int size) {
            return new MessageParcel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(message1);
        parcel.writeInt(messageInt);
    }
}
