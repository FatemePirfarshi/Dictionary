package com.example.dictionary.activity;

import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;

import com.example.dictionary.fragment.WordListFragment;

public class WordListActivity extends SingleFragmentActivity{

    public static void start(Context context){
        Intent starter = new Intent(context, WordListActivity.class);

        context.startActivity(starter);
    }

    @Override
    public Fragment createFragment() {
        return WordListFragment.newInstance();
    }

}