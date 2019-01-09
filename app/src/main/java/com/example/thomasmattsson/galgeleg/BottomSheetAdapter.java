package com.example.thomasmattsson.galgeleg;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import Data.Words;

public class BottomSheetAdapter extends RecyclerView.Adapter<BottomSheetAdapter.ViewHolder> {

    private ArrayList<Words> wordList;
    private OnItemClickListener mListener;

    public BottomSheetAdapter(ArrayList<Words> wordArrayList) {
        wordList = wordArrayList;
    }

    @NonNull
    @Override
    public BottomSheetAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pickword_itemview, parent, false);
        return new ViewHolder(view, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull BottomSheetAdapter.ViewHolder holder, int position) {
        holder.mWordId.setText(wordList.get(position).getId());
        holder.mWord.setText(wordList.get(position).getWord());
    }

    @Override
    public int getItemCount() {
        return wordList.size();
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mWordId;
        public TextView mWord;

        public ViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);

            mWordId = (TextView) itemView.findViewById(R.id.id);
            mWord = (TextView) itemView.findViewById(R.id.ord);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
