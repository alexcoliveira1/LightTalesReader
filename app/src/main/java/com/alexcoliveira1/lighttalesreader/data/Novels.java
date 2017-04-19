package com.alexcoliveira1.lighttalesreader.data;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by alex on 18/04/17.
 */

public class Novels {
    private LinkedList<Novel> translatedNovels;
    private LinkedList<Novel> originalNovels;

    public Novels() {
        this.translatedNovels = new LinkedList<>();
        this.originalNovels = new LinkedList<>();
    }

    public Novels(LinkedList<Novel> translatedNovels, LinkedList<Novel> originalNovels) {
        this.translatedNovels = translatedNovels;
        this.originalNovels = originalNovels;
    }

    public LinkedList<Novel> getTranslatedNovels() {
        return translatedNovels;
    }

    public void setTranslatedNovels(LinkedList<Novel> translatedNovels) {
        this.translatedNovels = translatedNovels;
    }

    public LinkedList<Novel> getOriginalNovels() {
        return originalNovels;
    }

    public void setOriginalNovels(LinkedList<Novel> originalNovels) {
        this.originalNovels = originalNovels;
    }

    public void copy(Novels o) {
        this.getOriginalNovels().clear();
        this.getOriginalNovels().addAll(o.getOriginalNovels());

        this.getTranslatedNovels().clear();
        this.getTranslatedNovels().addAll(o.getTranslatedNovels());
    }
}
