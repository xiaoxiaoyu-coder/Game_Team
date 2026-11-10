package data;

import function.Match_Manager;
import role.Match;
import role.Member;
import role.Team;

public class DataInit {

    // 初始化测试数据：比赛 -> 战队 -> 选手
    public static void initData(Match_Manager matchManager) {
        Match match1 = new Match(1, "全球总决赛", "S赛，年度最高级别赛事");
        Team team1 = new Team(1, "T1", "LCK 老牌强队");
        team1.addMember(new Member(1, "Faker", "中单"));
        team1.addMember(new Member(2, "Gumayusi", "ADC"));
        Team team2 = new Team(2, "BLG", "LPL 全华班");
        team2.addMember(new Member(3, "Bin", "上单"));
        team2.addMember(new Member(4, "knight", "中单"));
        match1.addTeam(team1);
        match1.addTeam(team2);

        Match match2 = new Match(2, "MSI 季中邀请赛", "季中举办的国际赛事");
        Team team3 = new Team(3, "GEN", "LCK 赛区冠军");
        team3.addMember(new Member(5, "Chovy", "中单"));
        Team team4 = new Team(4, "TES", "LPL 赛区战队");
        team4.addMember(new Member(6, "JackeyLove", "ADC"));
        match2.addTeam(team3);
        match2.addTeam(team4);

        matchManager.add_Match(match1);
        matchManager.add_Match(match2);
    }
}
