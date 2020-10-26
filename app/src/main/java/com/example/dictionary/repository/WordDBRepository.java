package com.example.dictionary.repository;

import android.content.Context;

import androidx.room.Room;

import com.example.dictionary.database.WordDAO;
import com.example.dictionary.database.WordDatabase;
import com.example.dictionary.model.Word;

import java.util.List;
import java.util.UUID;

public class WordDBRepository implements WordDAO {

    private static WordDBRepository sInstance;

    private WordDAO mWordDAO;
    private Context mContext;

    public static WordDBRepository getInstance(Context context){
        if(sInstance == null)
            sInstance = new WordDBRepository(context);

        return sInstance;
    }

    private WordDBRepository(Context context){
        mContext = context.getApplicationContext();

        mWordDAO = Room.databaseBuilder(mContext, WordDatabase.class, "word.db")
                .allowMainThreadQueries()
                .build()
                .getWordDAO();
    }

    @Override
    public List<Word> getWords() {
        return mWordDAO.getWords();
    }

    @Override
    public Word getWord(UUID wordId) {
        return mWordDAO.getWord(wordId);
    }

    @Override
    public void insertWord(Word word) {
        mWordDAO.insertWord(word);
    }

    @Override
    public void updateWord(Word word) {
        mWordDAO.updateWord(word);
    }

    @Override
    public void deleteWord(Word word) {
        mWordDAO.deleteWord(word);
    }
}
