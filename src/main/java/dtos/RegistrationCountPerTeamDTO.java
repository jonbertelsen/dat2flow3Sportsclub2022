package dtos;

public class RegistrationCountPerTeamDTO
{
    private String teamId;
    private int count;

    public RegistrationCountPerTeamDTO(String teamId, int count)
    {
        this.teamId = teamId;
        this.count = count;
    }

    @Override
    public String toString()
    {
        return "RegistrationCountPerTeamDTO{" +
                "teamId='" + teamId + '\'' +
                ", count=" + count +
                '}';
    }
}
