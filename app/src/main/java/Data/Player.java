package Data;

import java.util.ArrayList;

public class Player {

    private int score;
    private String name;
//    private ArrayList<Player> playerList = new ArrayList<Player>();
//
//    public ArrayList<Player> getPlayerList() {
//        return playerList;
//    }
//
//    public void setPlayerList(ArrayList<Player> playerList) {
//        this.playerList = playerList;
//    }

    public Player(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
