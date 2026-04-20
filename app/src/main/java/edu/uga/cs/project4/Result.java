package edu.uga.cs.project4;

public class Result {
    private long id;
    private final String date;
    private final int score;

    public Result(long id, String date, int score) {
        this.id = id;
        this.date = date;
        this.score = score;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getDate() {
        return date;
    }
    public int getScore() {
        return score;
    }
}
