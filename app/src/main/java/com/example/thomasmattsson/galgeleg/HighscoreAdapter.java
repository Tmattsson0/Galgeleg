package com.example.thomasmattsson.galgeleg;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import Data.Player;

public class HighscoreAdapter extends RecyclerView.Adapter<HighscoreAdapter.ViewHolder> {

    private ArrayList<Player> playerList;

    public HighscoreAdapter(ArrayList<Player> playerArrayList) {
        playerList = playerArrayList;
    }

    @NonNull
    @Override
    public HighscoreAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.highscore_carditemview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HighscoreAdapter.ViewHolder holder, int position) {
        Player player = playerList.get(position);

        holder.bind(player, position);
        holder.itemView.setOnClickListener(v -> {
            boolean expanded = player.isExpanded();
            player.setExpanded(!expanded);
            notifyItemChanged(position);
        });
    }

    @Override
    public int getItemCount() {
        if (playerList == null){
            return 0;
        } else {
            return playerList.size();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mPlayerName;
        public TextView mPlayerScore;
        public TextView mPlayerRank;
        public TextView mPlayerTime;
        public TextView mPlayerDate;

        private View subItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mPlayerName = (TextView) itemView.findViewById(R.id.listPlayerName);
            mPlayerScore = (TextView) itemView.findViewById(R.id.listPlayerScore);
            mPlayerRank = (TextView) itemView.findViewById(R.id.listRank);
            mPlayerTime = (TextView) itemView.findViewById(R.id.listPlayerTime);
            mPlayerDate = (TextView) itemView.findViewById(R.id.listPlayerDate);
            subItem = itemView.findViewById(R.id.sub_item);

        }
        private void bind(Player player, int position) {
            boolean expanded = player.isExpanded();

            subItem.setVisibility(expanded ? View.VISIBLE : View.GONE);

            mPlayerName.setText(player.getName());
            mPlayerScore.setText("Score: " + player.getScore());
            mPlayerRank.setText(Integer.toString(position+1));
            mPlayerTime.setText("Tid: " + Integer.toString(player.getTime()) + " s");
            mPlayerDate.setText("Dato: " + player.getDate());
        }
    }
}