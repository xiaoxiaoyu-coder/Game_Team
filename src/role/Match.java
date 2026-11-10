package role;

import java.util.ArrayList;

public class Match {
    private int Match_ID;
    private String Match_Name;
    private String Match_Description;
    private ArrayList<Team> teams = new ArrayList<>();

    public Match(int match_ID, String match_Name, String match_Description) {
        Match_ID = match_ID;
        Match_Name = match_Name;
        Match_Description = match_Description;
    }

    public void addTeam(Team team) {
        teams.add(team);
    }

    public ArrayList<Team> getTeams() {
        return teams;
    }

    public int getMatch_ID() {
        return Match_ID;
    }

    public String getMatch_Name() {
        return Match_Name;
    }

    public String getMatch_Description() {
        return Match_Description;
    }

    @Override
    public String toString() {
        return "比赛ID：" + Match_ID + "，比赛名：" + Match_Name + "，描述：" + Match_Description;
    }
}
