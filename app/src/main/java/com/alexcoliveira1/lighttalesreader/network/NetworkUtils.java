package com.alexcoliveira1.lighttalesreader.network;

import android.util.Log;

import com.alexcoliveira1.lighttalesreader.data.Novel;
import com.alexcoliveira1.lighttalesreader.data.Novels;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by alex on 18/04/17.
 */

public class NetworkUtils {
    private static String htmlPageUrl = "http://gravitytales.com/";
    private static final String TAG = "NetworkUtils";

    public static Novels getNovels() {
        Log.d(TAG, "getNovels");
        Novels novels = new Novels();
        try {
            Document htmlDocument = Jsoup.connect(htmlPageUrl).get();

            Elements menuItems = htmlDocument.getElementsByClass("dropdown");

            for (int i =0; i< 2; i++) {
                Element menuItem = menuItems.get(i);
                Elements links1 = menuItem.select("a[href]");
                for (Element link : links1) {
                    String hrefText = link.attr("href");
                    if(hrefText.startsWith("/novel/")) {
                        Novel novel = new Novel(link.text(),hrefText,null);
                        if (i == 0)
                            novels.getTranslatedNovels().add(novel);
                        else
                            novels.getOriginalNovels().add(novel);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d(TAG, novels.getOriginalNovels().size()+"\\\\"+novels.getTranslatedNovels().size());
        return novels;
    }

}
