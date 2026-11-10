package function;

import jiekou.Match_manager_function;
import role.Match;

import java.util.ArrayList;

public class Match_Manager implements Match_manager_function {
    private ArrayList<Match> matchList = new ArrayList<>();

    @Override
    public void add_Match(Match match) {
        matchList.add(match);
    }

    @Override
    public void delete_Match(int id) {
        matchList.remove(id);
    }

    public ArrayList<Match> getAll() {
        return matchList;
    }

    @Override
    public void print() {
        for (Match match : matchList) {
            System.out.println(match);
        }
    }
}
