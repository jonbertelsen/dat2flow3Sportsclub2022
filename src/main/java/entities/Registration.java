package entities;

public class Registration
{
    private int member_id;
    private String team_id;
    private int price;

    public Registration(int member_id, String team_id, int price)
    {
        this.member_id = member_id;
        this.team_id = team_id;
        this.price = price;
    }

    @Override
    public String toString()
    {
        return "Registration{" +
                "member_id=" + member_id +
                ", team_id='" + team_id + '\'' +
                '}';
    }

    public int getMember_id()
    {
        return member_id;
    }

    public void setMember_id(int member_id)
    {
        this.member_id = member_id;
    }

    public String getTeam_id()
    {
        return team_id;
    }

    public void setTeam_id(String team_id)
    {
        this.team_id = team_id;
    }
}
