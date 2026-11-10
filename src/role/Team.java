package role;

import java.util.ArrayList;

public class Team {
    private int Team_ID;
    private String Team_Name;
    private String Team_Description;
    private ArrayList<Member> members = new ArrayList<>();

    public Team(int team_ID, String team_Name, String team_Description) {
        Team_ID = team_ID;
        Team_Name = team_Name;
        Team_Description = team_Description;
    }

    public void addMember(Member member) {
        members.add(member);
    }

    public ArrayList<Member> getMembers() {
        return members;
    }
    public int getTeam_ID() {
        return Team_ID;
    }

    public String getTeam_Name() {
        return Team_Name;
    }

    public String getTeam_Description() {
        return Team_Description;
    }

    @Override
    public String toString() {
        return "队伍ID：" + Team_ID + "，队伍名：" + Team_Name + "，描述：" + Team_Description;
    }

}
