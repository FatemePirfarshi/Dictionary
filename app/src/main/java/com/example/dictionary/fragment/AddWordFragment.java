package com.example.dictionary.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.dictionary.R;
import com.example.dictionary.model.Word;
import com.example.dictionary.repository.WordDBRepository;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.UUID;


public class AddWordFragment extends BottomSheetDialogFragment {

    public static final String EXTRA_NEW_WORD = "newWord";
    public static final String ARGS_WORD_ID = "wordId";
    private EditText mEditTextWord, mEditTextTranslate;
    private Button mButtonSave;

    private WordDBRepository mRepository;

    public AddWordFragment() {
        // Required empty public constructor
    }

    public static AddWordFragment newInstance() {
        AddWordFragment fragment = new AddWordFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRepository = WordDBRepository.getInstance(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_word, container, false);

        findViews(view);
        setListeners();

        return view;
    }

    private void findViews(View view) {
        mEditTextWord = view.findViewById(R.id.edittext_word);
        mEditTextTranslate = view.findViewById(R.id.edittext_translate);
        mButtonSave = view.findViewById(R.id.btn_save);
    }

    private void setListeners() {
        mButtonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Word word = new Word();
                word.setEnglishWord(mEditTextWord.getText().toString());
                word.setPersianWord(mEditTextTranslate.getText().toString());

                Intent intent = new Intent();
                intent.putExtra(EXTRA_NEW_WORD, word);
                getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, intent);

                dismiss();
            }
        });
    }
}