package com.example.thomasmattsson.galgeleg;

import android.support.annotation.NonNull;

public class SimpleViewModel {
    private String playerName;
    private int score;

    /**
     * A viewmodel to hold and modify the data passed into it
     *
     * @param playerName
     *         The initial text
     */
    public SimpleViewModel(@NonNull final String playerName, final int score) {
        setPlayerName(playerName);
        setPlayerScore(score);
    }

    /**
     * Gets the text that has been set
     *
     * @return A String that represents the text that has been set
     */
    @NonNull
    public String getPlayerName() {
        return playerName;
    }

    public int getScore() {
        return score;
    }
    /**
     * While this is a basic project now, we could use this method to modify the
     * text after it was initially set
     *
     * @param playerName
     *         The text that will be displayed in the itemview
     */
    public void setPlayerName(@NonNull final String playerName) {
        this.playerName = playerName;
    }
    public void setPlayerScore(final int score) {
        this.score = score;
    }
}
