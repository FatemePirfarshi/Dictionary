package com.example.dictionary.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dictionary.R;
import com.example.dictionary.model.Word;

import java.util.List;

public class WordListFragment extends Fragment {

    private RecyclerView mRecyclerView;

    public WordListFragment() {
        // Required empty public constructor
    }

    public static WordListFragment newInstance() {
        WordListFragment fragment = new WordListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_word_list, container, false);

        findViews(view);
        initViews();

        return view;
    }

    private void findViews(View view){
        mRecyclerView = view.findViewById(R.id.recycler_view_word_list);
    }

    private void initViews(){
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private class WordHolder extends RecyclerView.ViewHolder{

        private TextView mTextViewWord;
        private Word mWord;

        public WordHolder(@NonNull View itemView) {
            super(itemView);

            mTextViewWord = itemView.findViewById(R.id.txtview_word);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }

        public void bindWord(Word word){
            mWord = word;
            mTextViewWord.setText(word.getWord());
        }
    }

    private class WordAdapter extends RecyclerView.Adapter<WordHolder>{

        private List<Word> mWords;

        public List<Word> getWords() {
            return mWords;
        }

        public void setWords(List<Word> words) {
            mWords = words;
        }

        public WordAdapter(List<Word> words){
            mWords = words;
        }

        @NonNull
        @Override
        public WordHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.item_word_list ,
                    parent , false);
            WordHolder wordHolder = new WordHolder(view);

            return wordHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull WordHolder holder, int position) {

            Word word = mWords.get(position);
            holder.bindWord(word);
        }

        @Override
        public int getItemCount() {
            return mWords.size();
        }
    }
}