package com.alexcoliveira1.lighttalesreader.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by alex on 18/04/17.
 */

public class Chapter implements Parcelable{

    private Integer ChapterId;
    private String Name;
    private Double Number;
    private String Slug;
    private String content;

    public Chapter(Integer chapterId, String name, Double number, String slug, String content) {
        ChapterId = chapterId;
        Name = name;
        Number = number;
        Slug = slug;
        this.content = content;
    }

    public Chapter(String Name, String content, Double number) {
        this.Name = Name;
        this.content = content;
        this.Number = number;
    }

    protected Chapter(Parcel in) {
        ChapterId = in.readInt();
        Name = in.readString();
        Number = in.readDouble();
        Slug = in.readString();
        content = in.readString();
    }

    public static final Creator<Chapter> CREATOR = new Creator<Chapter>() {
        @Override
        public Chapter createFromParcel(Parcel in) {
            return new Chapter(in);
        }

        @Override
        public Chapter[] newArray(int size) {
            return new Chapter[size];
        }
    };

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Double getNumber() {
        return Number;
    }

    public void setNumber(Double number) {
        this.Number = number;
    }

    public Integer getChapterId() {
        return ChapterId;
    }

    public void setChapterId(Integer chapterId) {
        ChapterId = chapterId;
    }

    public String getSlug() {
        return Slug;
    }

    public void setSlug(String slug) {
        Slug = slug;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(ChapterId);
        dest.writeString(Name);
        dest.writeDouble(Number);
        dest.writeString(Slug);
        dest.writeString(content);
    }
}
