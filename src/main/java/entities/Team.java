package entities;

public class Team
{
    String team_id;
    int sport_id;

    public Team(String team_id, int sport_id)
    {
        this.team_id = team_id;
        this.sport_id = sport_id;
    }

    @Override
    public String toString()
    {
        return "Team{" +
                "team_id='" + team_id + '\'' +
                ", sport_id=" + sport_id +
                '}';
    }

    public String getTeam_id()
    {
        return team_id;
    }

    public void setTeam_id(String team_id)
    {
        this.team_id = team_id;
    }

    public int getSport_id()
    {
        return sport_id;
    }

    public void setSport_id(int sport_id)
    {
        this.sport_id = sport_id;
    }
}
