package com.example.dictionary.model;

import java.util.UUID;

public class Word {

    private UUID mId;
    private String mWord;
    private String mTranslate;

    public UUID getId() {
        return mId;
    }

    public void setId(UUID id) {
        mId = id;
    }

    public String getWord() {
        return mWord;
    }

    public void setWord(String word) {
        mWord = word;
    }

    public String getTranslate() {
        return mTranslate;
    }

    public void setTranslate(String translate) {
        mTranslate = translate;
    }
}
