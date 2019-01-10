package com.example.thomasmattsson.galgeleg;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import Data.JSONReader;
import Data.Singleton;
import Data.Words;
import SpilLogik.GalgeLogik;


// https://www.youtube.com/watch?v=IfpRL2K1hJk
public class BottomSheetDialog extends BottomSheetDialogFragment {

    RecyclerView mRecyclerView;
    BottomSheetAdapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;

//    JSONReader w = new JSONReader();

    Singleton p = Singleton.getInstance();

//    ArrayList<Words> words = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

//            words = p.getWordArrayList();


        View v = inflater.inflate(R.layout.bottom_sheet_layout, container, false);

        mRecyclerView = (RecyclerView) v.findViewById(R.id.wordPickerRecyclerView);
        mLayoutManager = new LinearLayoutManager(getContext());
        mAdapter = new BottomSheetAdapter(p.getWordArrayList());

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new BottomSheetAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                System.out.println(p.getWordArrayList().get(position).getWord());

                Words customWord = p.getWordArrayList().get(position);
                p.setCustomWord(customWord);
                p.setIsCustomWord(true);

                startActivity(new Intent(getContext(), SpilActivity.class));
                dismiss();
            }
        });

        return v;
    }
}
