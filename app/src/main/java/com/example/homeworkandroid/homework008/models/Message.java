package com.example.homeworkandroid.homework008.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Message implements Parcelable {
    String text;
    int SenderID;
    Boolean flagAttached;

    public Message(
            String _text,
            int _SenderID,
            Boolean _flagAttached
    )
    {
        this.text = _text;
        this.SenderID = _SenderID;
        this.flagAttached = _flagAttached;
    }

    protected Message(Parcel in) {
        text = in.readString();
        SenderID = in.readInt();
        byte tmpFlagAttached = in.readByte();
        flagAttached = tmpFlagAttached == 0 ? null : tmpFlagAttached == 1;
    }

    public static final Creator<Message> CREATOR = new Creator<Message>() {
        @Override
        public Message createFromParcel(Parcel in) {
            return new Message(in);
        }

        @Override
        public Message[] newArray(int size) {
            return new Message[size];
        }
    };

    public String getText() {
        return text;
    }

    public void setText(String text) {
        if (text.length() > 0)
            this.text = text;
    }

    public int getSenderID() {
        return SenderID;
    }

    public void setSenderID(int senderID) {
        if (senderID > 0)
            SenderID = senderID;
    }

    public Boolean getFlagAttached() {
        return flagAttached;
    }

    public void setFlagAttached(Boolean flagAttached) {
        this.flagAttached = flagAttached;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(text);
        dest.writeInt(SenderID);
        dest.writeByte((byte) (flagAttached == null ? 0 : flagAttached ? 1 : 2));
    }
}
