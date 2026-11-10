package jiekou;
import role.Member;
public interface TeamMember_manager_function {
    public void add_Member(Member member);
    public void delete_Member(int id);
    public int Member_AverageAcs();
    public void print();
}
