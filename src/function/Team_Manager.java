package function;

import jiekou.Team_Manager_function;
import role.Team;

import java.util.ArrayList;

public class Team_Manager implements Team_Manager_function {
    private ArrayList<Team> teamList = new ArrayList<>();
    @Override
    public void add_Team(Team team) {
        teamList.add(team);
    }
    @Override
    public void delete_Team(int id) {
        teamList.remove(id);
    }

    public ArrayList<Team> getAll() {
        return teamList;
    }

    @Override
    public void print() {
        for (Team team : teamList) {
            System.out.println(team);
        }
    }
}
