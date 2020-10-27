package com.example.dictionary.fragment;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dictionary.R;
import com.example.dictionary.model.Word;
import com.example.dictionary.repository.WordDBRepository;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class WordListFragment extends Fragment {

    public static final String TAG_ADD_WORD_FRAGMENT = "addWordFragment";
    public static final int REQUEST_CODE_ADD_WORD = 0;
    public static final int REQUEST_CODE_WORD_DETAIL = 1;
    public static final String TAG = "WLF";
    public static final String TAG_WORD_DETAIL_FRAGMENT = "wordDetailFragment";
    public static final String BUNDLE_LANGUAGE_MODE = "languageMode";
    private RecyclerView mRecyclerView;
    private FloatingActionButton mAddButton;

    private WordDBRepository mRepository;
    private WordAdapter mWordAdapter;

    private boolean isEnglish = true;

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
        mRepository = WordDBRepository.getInstance(getActivity());

        if(savedInstanceState != null)
            isEnglish = savedInstanceState.getBoolean(BUNDLE_LANGUAGE_MODE, true);
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

    private void findViews(View view) {
        mRecyclerView = view.findViewById(R.id.recycler_view_word_list);
        mAddButton = view.findViewById(R.id.btn_add);
    }

    private void initViews() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        setSubTitle();
        updateUI();
    }

    private void setSubTitle() {
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.getSupportActionBar().setSubtitle(mRepository.getWords().size() + " words");
    }

    private void updateUI() {
        List<Word> words = mRepository.getWords();

        if (mWordAdapter == null) {
            mWordAdapter = new WordAdapter(words);
            mRecyclerView.setAdapter(mWordAdapter);
        } else {
            mWordAdapter.setWords(words);
            mWordAdapter.notifyDataSetChanged();
        }
        setSubTitle();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(BUNDLE_LANGUAGE_MODE, isEnglish);
    }

    private void setListeners() {
        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddWordFragment addWordFragment = AddWordFragment.newInstance();
                addWordFragment.setTargetFragment(WordListFragment.this, REQUEST_CODE_ADD_WORD);
                addWordFragment.show(getActivity().getSupportFragmentManager(), TAG_ADD_WORD_FRAGMENT);
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_word_list, menu);
        MenuItem searchItem = menu.findItem(R.id.app_bar_search);

        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));

        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                mWordAdapter.getFilter().filter(s);
                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_item_english_mode:
                isEnglish = true;
                updateUI();
                return true;
            case R.id.menu_item_persian_mode:
                isEnglish = false;
                updateUI();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private class WordHolder extends RecyclerView.ViewHolder {

        private TextView mTextViewWord;
        private Word mWord;

        public WordHolder(@NonNull View itemView) {
            super(itemView);

            mTextViewWord = itemView.findViewById(R.id.txtview_word);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    WordDetailFragment wordDetailFragment = WordDetailFragment.newInstance(mWord.getId());
                    wordDetailFragment.setTargetFragment(WordListFragment.this, REQUEST_CODE_WORD_DETAIL);
                    wordDetailFragment.show(getActivity().getSupportFragmentManager(), TAG_WORD_DETAIL_FRAGMENT);
                }
            });
        }

        public void bindWord(Word word) {
            mWord = word;
            if (isEnglish)
                mTextViewWord.setText(word.getEnglishWord());
            else
                mTextViewWord.setText(word.getPersianWord());
        }
    }

    private class WordAdapter extends RecyclerView.Adapter<WordHolder> implements Filterable {

        private List<Word> mWords;
        private List<Word> mWordsFull;

        public List<Word> getWords() {
            return mWords;
        }

        public void setWords(List<Word> words) {
            mWords = words;
            mWordsFull = new ArrayList<>(words);
            notifyDataSetChanged();
        }

        public WordAdapter(List<Word> words) {
            mWords = words;
            mWordsFull = new ArrayList<>(words);
        }

        @NonNull
        @Override
        public WordHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View view = LayoutInflater
                    .from(getActivity())
                    .inflate(R.layout.item_word_list, parent, false);
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

        @Override
        public Filter getFilter() {
            return new Filter() {

                @Override
                protected FilterResults performFiltering(CharSequence charSequence) {

                    List<Word> filteredList = new ArrayList<>();

                    if (charSequence == null || charSequence.length() == 0)
                        filteredList.addAll(mWordsFull);
                    else {
                        String filter = charSequence.toString().toLowerCase().trim();
                        for (Word word : mWordsFull)
                            if (word.getEnglishWord().toLowerCase().contains(filter) ||
                                    word.getPersianWord().toLowerCase().contains(filter))
                                filteredList.add(word);
                    }
                    FilterResults results = new FilterResults();
                    results.values = filteredList;

                    return results;
                }

                @Override
                protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                    if (mWords != null)
                        mWords.clear();
                    else
                        mWords = new ArrayList<>();

                    if (filterResults != null)
                        mWords.addAll((Collection<? extends Word>) filterResults.values);
                    notifyDataSetChanged();
                }
            };
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode != Activity.RESULT_OK || data == null)
            return;

        if (requestCode == REQUEST_CODE_ADD_WORD) {
            Word word = (Word) data.getSerializableExtra(AddWordFragment.EXTRA_NEW_WORD);
            mRepository.insertWord(word);
            updateUI();
        }
        if (requestCode == REQUEST_CODE_WORD_DETAIL) {
            updateUI();
        }
    }

}