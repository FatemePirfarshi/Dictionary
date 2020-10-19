package com.example.dictionary.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dictionary.R;


public class WordDetailFragment extends Fragment {

    private TextView mTextViewWord;
    private TextView mTextViewTranslate;
    private ImageView mImageViewShare;
    private ImageView mImageViewEdit;
    private ImageView mImageViewDelete;

    public WordDetailFragment() {
        // Required empty public constructor
    }

    public static WordDetailFragment newInstance() {
        WordDetailFragment fragment = new WordDetailFragment();
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
        View view = inflater.inflate(R.layout.fragment_word_detail, container, false);

        findViews(view);

        setListeners();
        initViews();
        return view;
    }

    private void findViews(View view) {
        mTextViewWord = view.findViewById(R.id.txtview_word);
        mTextViewTranslate = view.findViewById(R.id.txtview_translate);
        mImageViewShare = view.findViewById(R.id.imgView_share);
        mImageViewEdit = view.findViewById(R.id.imgView_edit);
        mImageViewDelete = view.findViewById(R.id.imgView_delete);
    }

    private void initViews() {
        //todo
    }

    private void setListeners() {
        mImageViewShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //todo
            }
        });
        mImageViewEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //todo
            }
        });
        mImageViewDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //todo
            }
        });
    }
}