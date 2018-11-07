package com.example.thomasmattsson.galgeleg;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class SimpleViewHolder extends RecyclerView.ViewHolder {
    private TextView listRank;

    /**
     * The ViewHolder that will be used to display the data in each item shown
     * in the RecyclerView
     *
     * @param itemView
     *         The layout view group used to display the data
     */
    public SimpleViewHolder(final View itemView) {
        super(itemView);
        listRank = (TextView) itemView.findViewById(R.id.listRank);
    }

    /**
     * Method that is used to bind the data to the ViewHolder
     *
     * @param viewModel
     *         The viewmodel that contains the data
     */
    public void bindData(final SimpleViewModel viewModel) {
        listRank.setText(viewModel.getSimpleText());
    }
}