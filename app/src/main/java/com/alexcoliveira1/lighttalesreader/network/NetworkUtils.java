package com.alexcoliveira1.lighttalesreader.network;

import android.util.Log;

import com.alexcoliveira1.lighttalesreader.data.Chapter;
import com.alexcoliveira1.lighttalesreader.data.ChapterGroup;
import com.alexcoliveira1.lighttalesreader.data.Content;
import com.alexcoliveira1.lighttalesreader.data.Novel;
import com.alexcoliveira1.lighttalesreader.data.Novels;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by alex on 18/04/17.
 */

public class NetworkUtils {
    private static String htmlPageUrl = "http://gravitytales.com";
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
                        Novel novel = new Novel(link.text(),hrefText,1,1);
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

    public static Integer getNovelId(Novel novel) {
        String NOVEL_ID_START = "React.createElement(Components.ChapterGroupList, { novelId: ";
        String requestURL = htmlPageUrl+novel.getUrl();
        try {
            Document htmlDocument = Jsoup.connect(requestURL).get();

            Elements menuItems = htmlDocument.body().getElementsByTag("script");
            Log.d(TAG, "scriptElements: " + menuItems.size());
            for (Element e : menuItems) {
                String txt = e.data();
                //Log.d(TAG, e.data());
                if(txt.contains(NOVEL_ID_START)) {
                    String[] temp1 = txt.split("novelId: ")[1].split(",");
                    Integer novelId = Integer.parseInt(temp1[0]);
                    String novelSlug = temp1[1].split("novelSlug: ")[1].split("' [}][)]")[0];
                    Log.d(TAG, "novelid:"+temp1[0]);
                    return novelId;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            Log.d(TAG, e.getStackTrace().toString());
        }
        Log.d(TAG, "NovelId NOT found - " + requestURL);
        return -1;
    }

    public static Novel getNovelChapters(Novel novel) {
        Log.d(TAG, "getNovelChapters");
        Integer novelId = getNovelId(novel);
        if(novelId == -1)
            return null;

        novel.setNovelId(novelId);

        RetroController control = new RetroController();
        GravityAPI gravityAPI = control.createGravityAPI();
        List<ChapterGroup> chapterGroupList = control.getChapterGroups(gravityAPI, novel.getNovelId());

        Integer min=1, max=1;
        for(ChapterGroup e : chapterGroupList) {
            if(e.getFromChapterNumber() < min)
                min = e.getFromChapterNumber();
            if(e.getToChapterNumber() > max)
                max = e.getToChapterNumber();
        }
        Log.d(TAG, "Chapters: "+min+"-"+max);

        novel.setFromChapter(min);
        novel.setToChapter(max);

        LinkedList<Chapter> chapterList = new LinkedList<>();
        for(ChapterGroup e : chapterGroupList) {
            List<Chapter> lchapter = control.getChapters(gravityAPI, e.getChapterGroupId());
            chapterList.addAll(lchapter);
        }

        novel.setChapters(chapterList);

        return novel;
    }

    public static Chapter getChapterContent(Chapter chapter) {
        Log.d(TAG, "getChapterContent");
        Integer chapterId = chapter.getChapterId();
        if(chapterId == -1)
            return null;

        RetroController control = new RetroController();
        GravityAPI gravityAPI = control.createGravityAPI();
        Content chapterContent = control.getChapterContent(gravityAPI, chapterId);

        chapter.setContent(chapterContent.getContent());

        return chapter;
    }

}
