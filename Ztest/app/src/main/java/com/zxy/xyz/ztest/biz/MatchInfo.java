package com.zxy.xyz.ztest.biz;

/**
 * Created by 51c on 2017/7/12.
 */

public class MatchInfo {
    public static int ITEM_VIEW_TYPE_HEADER=0;
    public static int ITEM_VIEW_TYPE_BODY=1;
    private String date;
    private String team1;
    private String team2;
    private String score1;
    private String score2;
    private String teamImg1;
    private String teamImg2;
    private String endStr;
    private int type;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTeam1() {
        return team1;
    }

    public void setTeam1(String team1) {
        this.team1 = team1;
    }

    public String getTeam2() {
        return team2;
    }

    public void setTeam2(String team2) {
        this.team2 = team2;
    }

    public String getScore1() {
        return score1;
    }

    public void setScore1(String score1) {
        this.score1 = score1;
    }

    public String getScore2() {
        return score2;
    }

    public void setScore2(String score2) {
        this.score2 = score2;
    }

    public String getTeamImg1() {
        return teamImg1;
    }

    public void setTeamImg1(String teamImg1) {
        this.teamImg1 = teamImg1;
    }

    public String getTeamImg2() {
        return teamImg2;
    }

    public void setTeamImg2(String teamImg2) {
        this.teamImg2 = teamImg2;
    }

    public String getEndStr() {
        return endStr;
    }

    public void setEndStr(String endStr) {
        this.endStr = endStr;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
