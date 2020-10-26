package com.example.dictionary.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.UUID;

@Entity(tableName = "wordTable")
public class Word implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "wordId")
    private int wordId;

    @ColumnInfo(name = "uuid")
    private UUID mId;

    @ColumnInfo(name = "english_word")
    private String mEnglishWord;

    @ColumnInfo(name = "persian_word")
    private String mPersianWord;

    public Word(){
        this(UUID.randomUUID());
    }

    public Word(UUID uuid){
        mId = uuid;
    }

    public Word(UUID id, String englishWord, String persianWord){
        mId = id;
        mEnglishWord = englishWord;
        mPersianWord = persianWord;
    }

    public int getWordId() {
        return wordId;
    }

    public void setWordId(int wordId) {
        this.wordId = wordId;
    }

    public UUID getId() {
        return mId;
    }

    public void setId(UUID id) {
        mId = id;
    }

    public String getEnglishWord() {
        return mEnglishWord;
    }

    public void setEnglishWord(String englishWord) {
        mEnglishWord = englishWord;
    }

    public String getPersianWord() {
        return mPersianWord;
    }

    public void setPersianWord(String persianWord) {
        mPersianWord = persianWord;
    }
}
