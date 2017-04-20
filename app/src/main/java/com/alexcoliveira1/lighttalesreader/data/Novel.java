package com.alexcoliveira1.lighttalesreader.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by alex on 18/04/17.
 */

public class Novel implements Parcelable{

    private String title;
    private String url;
    private Integer novelId;
    private String novelSlug;
    private Integer fromChapter;
    private Integer toChapter;
    private List<Chapter> chapters;

    private Novel(Parcel in) {
        title = in.readString();
        url = in.readString();
        novelId = in.readInt();
        novelSlug = in.readString();
        fromChapter = in.readInt();
        toChapter = in.readInt();
        chapters = new LinkedList<>();
        in.readList(chapters, Chapter.class.getClassLoader());
    }

    public Novel(String title, String url, Integer fromChapter, Integer toChapter) {
        this.title = title;
        this.url = url;
        this.novelId = -1;
        this.novelSlug = "";
        this.fromChapter = fromChapter;
        this.toChapter = toChapter;
        this.chapters = new LinkedList<>();
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

    public Integer getNovelId() {
        return novelId;
    }

    public void setNovelId(Integer novelId) {
        this.novelId = novelId;
    }

    public String getNovelSlug() {
        return novelSlug;
    }

    public void setNovelSlug(String novelSlug) {
        this.novelSlug = novelSlug;
    }

    public Integer getFromChapter() {
        return fromChapter;
    }

    public void setFromChapter(Integer fromChapter) {
        this.fromChapter = fromChapter;
    }

    public Integer getToChapter() {
        return toChapter;
    }

    public void setToChapter(Integer toChapter) {
        this.toChapter = toChapter;
    }

    public LinkedList<Chapter> getChapters() {
        return new LinkedList<>(chapters);
    }

    public void setChapters(LinkedList<Chapter> chapters) {
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
        dest.writeInt(novelId);
        dest.writeString(novelSlug);
        dest.writeInt(fromChapter);
        dest.writeInt(toChapter);
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
