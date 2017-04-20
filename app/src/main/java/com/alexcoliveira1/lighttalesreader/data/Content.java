package com.alexcoliveira1.lighttalesreader.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by alex on 20/04/17.
 */

public class Content implements Parcelable{
    private String Content;

    protected Content(Parcel in) {
        Content = in.readString();
    }

    public static final Creator<Content> CREATOR = new Creator<Content>() {
        @Override
        public Content createFromParcel(Parcel in) {
            return new Content(in);
        }

        @Override
        public Content[] newArray(int size) {
            return new Content[size];
        }
    };

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Content);
    }
}
