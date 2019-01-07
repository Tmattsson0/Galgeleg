package com.example.thomasmattsson.galgeleg;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import Data.Player;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private ArrayList<Player> playerList;

    public MyAdapter(ArrayList<Player> playerArrayList) {
        playerList = playerArrayList;
    }

    @NonNull
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.highscore_carditemview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.ViewHolder holder, int position) {
//        holder.mPlayerName.setText(playerList.get(position).getName());
//        holder.mPlayerScore.setText(Integer.toString(playerList.get(position).getScore()));
//        holder.mPlayerRank.setText(Integer.toString(position+1));

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
            mPlayerTime.setText("1000 s");
            mPlayerDate.setText("23/04/96");
        }
    }
}