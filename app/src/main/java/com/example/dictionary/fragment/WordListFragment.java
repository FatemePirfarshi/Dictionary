package com.example.dictionary.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dictionary.R;
import com.example.dictionary.model.Word;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class WordListFragment extends Fragment {

    public static final String TAG_ADD_WORD_FRAGMENT = "addWordFragment";
    public static final int REQUEST_CODE_ADD_WORD = 0;
    private RecyclerView mRecyclerView;
    private FloatingActionButton mAddButton;

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

        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_word_list, container, false);

        findViews(view);
        initViews();
        setListeners();

        return view;
    }

    private void findViews(View view){
        mRecyclerView = view.findViewById(R.id.recycler_view_word_list);
        mAddButton = view.findViewById(R.id.btn_add);
    }

    private void initViews(){
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private void setListeners(){
        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddWordFragment addWordFragment = new AddWordFragment();
                addWordFragment.setTargetFragment(WordListFragment.this, REQUEST_CODE_ADD_WORD);
                addWordFragment.show(getActivity().getSupportFragmentManager(), TAG_ADD_WORD_FRAGMENT);
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_word_list, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.menu_item_english_mode:
                //todo
                return true;
            case R.id.menu_item_persian_mode:
                //todo
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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
            //todo
//            mTextViewWord.setText(word.getWord());
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