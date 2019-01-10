package com.example.thomasmattsson.galgeleg;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import Data.Words;

public class WordAdapter extends RecyclerView.Adapter<WordAdapter.ViewHolder> {

    private ArrayList<Words> wordList;

    public WordAdapter(ArrayList<Words> wordArrayList) {
        wordList = wordArrayList;
    }

    @NonNull
    @Override
    public WordAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.wordlist_itemview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WordAdapter.ViewHolder holder, int position) {
        holder.mWordId.setText(wordList.get(position).getId());
        holder.mWord.setText(wordList.get(position).getWord());
        holder.mDefinition.setText(wordList.get(position).getDefinition());
    }

    @Override
    public int getItemCount() {
        return wordList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mWordId;
        public TextView mWord;
        public TextView mDefinition;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mWordId = (TextView) itemView.findViewById(R.id.id);
            mWord = (TextView) itemView.findViewById(R.id.ord);
            mDefinition = (TextView) itemView.findViewById(R.id.definition);
        }
    }
}
