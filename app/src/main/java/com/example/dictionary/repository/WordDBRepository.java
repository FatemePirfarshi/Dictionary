package com.example.dictionary.repository;

import com.example.dictionary.model.Word;

import java.util.List;
import java.util.UUID;

public class WordDBRepository implements IRepository {

    private static WordDBRepository sInstance;

    public static WordDBRepository getInstance(){
        if(sInstance == null)
            sInstance = new WordDBRepository();

        return sInstance;
    }

    private WordDBRepository(){

    }

    @Override
    public List<Word> getWords() {
        return null;
    }

    @Override
    public Word getWord(UUID wordId) {
        return null;
    }

    @Override
    public void insertWord(Word word) {

    }

    @Override
    public void updateWord(Word word) {

    }

    @Override
    public void deleteWord(Word word) {

    }
}
