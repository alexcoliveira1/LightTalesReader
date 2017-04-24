package com.alexcoliveira1.lighttalesreader.network;

/**
 * Created by alex on 19/04/17.
 */

import com.alexcoliveira1.lighttalesreader.data.Chapter;
import com.alexcoliveira1.lighttalesreader.data.ChapterGroup;
import com.alexcoliveira1.lighttalesreader.data.Content;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GravityAPI {

    @GET("novels/chaptergroups/{novelid}")
    Call<List<ChapterGroup>> loadChapterGroups(@Path("novelid") Integer novelId);

    @GET("novels/chaptergroup/{chapterGroupId}")
    Call<List<Chapter>> loadChapters(@Path("chapterGroupId") Integer chapterGroupId);

    @GET("novels/chapters/{chapterId}")
    Call<Content> loadChapterContent(@Path("chapterId") Integer chapterId);
}