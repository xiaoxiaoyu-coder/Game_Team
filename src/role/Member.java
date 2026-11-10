package role;
import jiekou.TeamMember_manager_function;
public class Member {
    private int Member_ID;
    private String Member_Name;
    private String Member_Description;

    public Member(int member_ID, String member_Name, String member_Description) {
        Member_ID = member_ID;
        Member_Name = member_Name;
        Member_Description = member_Description;
    }
    public int getMember_ID() {
        return Member_ID;
    }

    public String getMember_Name() {
        return Member_Name;
    }

    public String getMember_Description() {
        return Member_Description;
    }

    public String toString() {
        return "成员ID：" + Member_ID + "，成员名：" + Member_Name + "，描述：" + Member_Description;
    }
}
