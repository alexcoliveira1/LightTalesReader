package com.alexcoliveira1.lighttalesreader.network;

/**
 * Created by alex on 19/04/17.
 */

import android.util.Log;

import com.alexcoliveira1.lighttalesreader.data.Chapter;
import com.alexcoliveira1.lighttalesreader.data.ChapterGroup;
import com.alexcoliveira1.lighttalesreader.data.Content;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroController {

    static final String BASE_URL = "http://gravitytales.com/api/";
    static final String TAG = "RetroController";

    public GravityAPI createGravityAPI() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        GravityAPI gravityAPI = retrofit.create(GravityAPI.class);
        return gravityAPI;
    }

    public List<ChapterGroup> getChapterGroups(GravityAPI gravityAPI, Integer novelId) {
        if(gravityAPI == null)
            gravityAPI = createGravityAPI();
        Call<List<ChapterGroup>> call = gravityAPI.loadChapterGroups(novelId);
        try {
            Response<List<ChapterGroup>> response = call.execute();
            if(response.isSuccessful()) {
                Log.d(TAG, response.message());
                Log.d(TAG, response.toString());
                List<ChapterGroup> chapterGroupList = response.body();
                Log.d(TAG, ""+chapterGroupList.size());
                return chapterGroupList;
            } else {
                Log.d(TAG, response.errorBody().toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Chapter> getChapters(GravityAPI gravityAPI, Integer chapterGroupId) {
        if(gravityAPI == null)
            gravityAPI = createGravityAPI();
        Call<List<Chapter>> call = gravityAPI.loadChapters(chapterGroupId);
        try {
            Response<List<Chapter>> response = call.execute();
            if(response.isSuccessful()) {
                Log.d(TAG, response.message());
                Log.d(TAG, response.toString());
                List<Chapter> chapterList = response.body();
                Log.d(TAG, ""+chapterList.size());
                return chapterList;
            } else {
                Log.d(TAG, response.errorBody().toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Content getChapterContent(GravityAPI gravityAPI, Integer chapterId) {
        if(gravityAPI == null)
            gravityAPI = createGravityAPI();
        Log.d(TAG, "ChapterId="+chapterId);
        Call<Content> call = gravityAPI.loadChapterContent(chapterId);
        try {
            Response<Content> response = call.execute();
            if(response.isSuccessful()) {
                Log.d(TAG, response.message());
                Log.d(TAG, response.toString());
                Content chapterContent = response.body();
                Log.d(TAG, ""+chapterContent.getContent().length());
                return chapterContent;
            } else {
                Log.d(TAG, response.errorBody().toString());
            }
        } catch (IOException e) {
            Log.d(TAG, "Exception");
            e.printStackTrace();
        }
        return null;
    }
}