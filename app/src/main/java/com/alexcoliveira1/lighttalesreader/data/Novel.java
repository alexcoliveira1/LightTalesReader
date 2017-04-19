package com.alexcoliveira1.lighttalesreader.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by alex on 18/04/17.
 */

public class Novel implements Parcelable{

    private String title;
    private String url;
    private List<Chapter> chapters;

    private Novel(Parcel in) {
        title = in.readString();
        url = in.readString();
        in.readList(chapters, List.class.getClassLoader());
    }

    public Novel(String title, String url, List<Chapter> chapters) {
        this.title = title;
        this.url = url;
        this.chapters = chapters;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Chapter> getChapters() {
        return chapters;
    }

    public void setChapters(List<Chapter> chapters) {
        this.chapters = chapters;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(url);
        dest.writeList(chapters);
    }

    public static final Creator<Novel> CREATOR = new Creator<Novel>() {
        @Override
        public Novel createFromParcel(Parcel in) {
            return new Novel(in);
        }

        @Override
        public Novel[] newArray(int size) {
            return new Novel[size];
        }
    };
}
