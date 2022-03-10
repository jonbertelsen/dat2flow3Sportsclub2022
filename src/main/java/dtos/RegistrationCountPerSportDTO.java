package dtos;

public class RegistrationCountPerSportDTO
{
    private String sportId;
    private int count;

    public RegistrationCountPerSportDTO(String sportId, int count)
    {
        this.sportId = sportId;
        this.count = count;
    }

    @Override
    public String toString()
    {
        return "RegistrationCountPerSportDTO{" +
                "sportId='" + sportId + '\'' +
                ", count=" + count +
                '}';
    }
}
