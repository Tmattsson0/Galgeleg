package com.example.thomasmattsson.galgeleg;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class SimpleViewHolder extends RecyclerView.ViewHolder {
    private TextView listName;
    private TextView listScore;

    /**
     * The ViewHolder that will be used to display the data in each item shown
     * in the RecyclerView
     *
     * @param itemView
     *         The layout view group used to display the data
     */
    public SimpleViewHolder(final View itemView) {
        super(itemView);
        listName = (TextView) itemView.findViewById(R.id.listPlayerName);
        listScore = (TextView) itemView.findViewById(R.id.listPlayerScore);
    }

    /**
     * Method that is used to bind the data to the ViewHolder
     *
     * @param viewModel
     *         The viewmodel that contains the data
     */
    public void bindData(final SimpleViewModel viewModel) {
        listName.setText(viewModel.getPlayerName());
        listScore.setText(viewModel.getScore());
    }
}