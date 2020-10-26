package com.example.dictionary.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.dictionary.R;
import com.example.dictionary.model.Word;
import com.example.dictionary.repository.WordDBRepository;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.UUID;


public class WordDetailFragment extends BottomSheetDialogFragment {

    public static final String ARGS_WORD_ID = "wordId";
    private EditText mTextViewWord;
    private EditText mTextViewTranslate;
    private LinearLayout mLayoutEdit;
    private LinearLayout mLayoutShare;
    private LinearLayout mLayoutDelete;

    private WordDBRepository mRepository;
    private Word mWord;

    public WordDetailFragment() {
        // Required empty public constructor
    }

    public static WordDetailFragment newInstance(UUID wordId) {
        WordDetailFragment fragment = new WordDetailFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARGS_WORD_ID, wordId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRepository = WordDBRepository.getInstance(getActivity());
        UUID wordId = (UUID) getArguments().getSerializable(ARGS_WORD_ID);
        mWord = mRepository.getWord(wordId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_word_detail, container, false);

        findViews(view);
        setListeners();
        initViews();

        return view;
    }

    private void findViews(View view) {
        mTextViewWord = view.findViewById(R.id.txtview_word);
        mTextViewTranslate = view.findViewById(R.id.txtview_translate);
        mLayoutEdit = view.findViewById(R.id.layout_edit);
        mLayoutShare = view.findViewById(R.id.layout_share);
        mLayoutDelete = view.findViewById(R.id.layout_delete);
    }

    private void initViews() {
        mTextViewWord.setText(mWord.getEnglishWord());
        mTextViewTranslate.setText(mWord.getPersianWord());
    }

    private void setListeners() {
        mLayoutEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mWord.setEnglishWord(mTextViewWord.getText().toString());
                mWord.setPersianWord(mTextViewTranslate.getText().toString());
                mRepository.updateWord(mWord);

                Intent intent = new Intent();
                getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, intent);
                dismiss();
            }
        });
        mLayoutShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //todo
            }
        });
        mLayoutDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mRepository.deleteWord(mWord);

                Intent intent = new Intent();
                getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, intent);

                dismiss();
            }
        });
    }
}