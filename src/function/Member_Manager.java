package function;
import jiekou.TeamMember_manager_function;
import role.Member;

import java.util.ArrayList;
public class Member_Manager {
    private ArrayList<Member> memberList = new ArrayList<>();

    public void add_Member(Member member) {
        memberList.add(member);
    }

    public void delete_Member(int id) {
        memberList.remove(id);
    }

    public ArrayList<Member> getAll() {
        return memberList;
    }
    public void print() {
        for (Member member : memberList) {
            System.out.println(member);
        }
    }
}
