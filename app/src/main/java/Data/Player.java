package Data;


public class Player {

    private int score;


    private int time;
    private String name, date;
    private boolean expanded;

    public Player(String name, int score, int time, String date) {
        this.name = name;
        this.score = score;
        this.time = time;
        this.date = date;
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

    public int getTime() {
        return time;
    }

    public String getDate() {
        return date;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    public boolean isExpanded() {
        return expanded;
    }
}
