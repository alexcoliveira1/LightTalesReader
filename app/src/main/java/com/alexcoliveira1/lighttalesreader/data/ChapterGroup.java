package com.alexcoliveira1.lighttalesreader.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by alex on 19/04/17.
 */

public class ChapterGroup implements Parcelable{
    private Integer ChapterGroupId;
    private Integer FromChapterNumber;
    private Integer ToChapterNumber;
    private Integer NovelId;
    private String Title;


    protected ChapterGroup(Parcel in) {
        ChapterGroupId = in.readInt();
        FromChapterNumber = in.readInt();
        ToChapterNumber = in.readInt();
        NovelId = in.readInt();
        Title = in.readString();
    }

    public Integer getChapterGroupId() {
        return ChapterGroupId;
    }

    public void setChapterGroupId(Integer chapterGroupId) {
        this.ChapterGroupId = chapterGroupId;
    }

    public Integer getFromChapterNumber() {
        return FromChapterNumber;
    }

    public void setFromChapterNumber(Integer fromChapterNumber) {
        this.FromChapterNumber = fromChapterNumber;
    }

    public Integer getNovelId() {
        return NovelId;
    }

    public void setNovelId(Integer novelId) {
        this.NovelId = novelId;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        this.Title = title;
    }

    public Integer getToChapterNumber() {
        return ToChapterNumber;
    }

    public void setToChapterNumber(Integer toChapterNumber) {
        this.ToChapterNumber = toChapterNumber;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(ChapterGroupId);
        dest.writeInt(FromChapterNumber);
        dest.writeInt(ToChapterNumber);
        dest.writeInt(NovelId);
        dest.writeString(Title);
    }

    public static final Creator<ChapterGroup> CREATOR = new Creator<ChapterGroup>() {
        @Override
        public ChapterGroup createFromParcel(Parcel in) {
            return new ChapterGroup(in);
        }

        @Override
        public ChapterGroup[] newArray(int size) {
            return new ChapterGroup[size];
        }
    };
}
