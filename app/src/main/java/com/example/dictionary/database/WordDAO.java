package com.example.dictionary.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.dictionary.model.Word;

import java.util.List;
import java.util.UUID;

@Dao
public interface WordDAO {

    @Insert
    void insertWord(Word word);

    @Update
    void updateWord(Word word);

    @Delete
    void deleteWord(Word word);

    @Query("SELECT * FROM wordTable ORDER BY english_word, persian_word asc")
    List<Word> getWords();

    @Query("SELECT * FROM wordTable WHERE uuid = :uuid")
    Word getWord(UUID uuid);

    @Query("SELECT * FROM wordTable ORDER BY english_word asc")
    List<Word> getEnglishWords();

    @Query("SELECT * FROM wordTable ORDER BY persian_word asc")
    List<Word> getPersianWords();
}
