package com.example.thomasmattsson.galgeleg;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import Data.Player;

import static com.example.thomasmattsson.galgeleg.R.layout.item_simple_itemview;

public class SimpleAdapter extends RecyclerView.Adapter<SimpleAdapter.ViewHolder> {

    private ArrayList<Player> playerList;

    public SimpleAdapter(ArrayList<Player> playerArrayList) {
        playerList = playerArrayList;
    }

    @NonNull
    @Override
    public SimpleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_simple_itemview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SimpleAdapter.ViewHolder holder, int position) {
        holder.mPlayerName.setText(playerList.get(position).getName());
        holder.mPlayerScore.setText(Integer.toString(playerList.get(position).getScore()));
        holder.mPlayerRank.setText(Integer.toString(position+1));
    }

    @Override
    public int getItemCount() {
        return playerList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mPlayerName;
        public TextView mPlayerScore;
        public TextView mPlayerRank;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mPlayerName = (TextView) itemView.findViewById(R.id.listPlayerName);
            mPlayerScore = (TextView) itemView.findViewById(R.id.listPlayerScore);
            mPlayerRank = (TextView) itemView.findViewById(R.id.listRank);
        }
    }
}