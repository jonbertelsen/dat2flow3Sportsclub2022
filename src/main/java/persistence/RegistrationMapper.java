package persistence;

import dtos.RegistrationCountPerSportDTO;
import dtos.RegistrationCountPerTeamDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RegistrationMapper
{
    private Database database;

    public RegistrationMapper(Database database)  // Dependency Injection  (SOLID)
    {
        this.database = database;
    }

    public List<RegistrationCountPerTeamDTO> getRegistrationCountPerTeam()
    {
        ArrayList<RegistrationCountPerTeamDTO> registrationCountDTOList = new ArrayList<>();

        String sql =
                "select team_id, count(member_id) as count " +
                "from registration " +
                "group by team_id " +
                "order by count desc";

        try (Connection connection = database.connect()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    String teamId = rs.getString("team_id");
                    int count = rs.getInt("count");
                    RegistrationCountPerTeamDTO dto = new RegistrationCountPerTeamDTO(teamId, count);
                    registrationCountDTOList.add(dto);
                }
            } catch (SQLException throwables) {
                // TODO: Make own throwable exception and let it bubble upwards
                throwables.printStackTrace();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return registrationCountDTOList;
    }

    public List<RegistrationCountPerSportDTO> getRegistrationCountPerSport()
    {
        ArrayList<RegistrationCountPerSportDTO> registrationCountDTOList = new ArrayList<>();

        String sql = "select count(*) as count, s.sport " +
            "from sport s " +
            "inner join team t " +
            "using (sport_id) " +
            "inner join registration r " +
            "using (team_id) " +
            "group by s.sport";

        try (Connection connection = database.connect()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    String sport = rs.getString("sport");
                    int count = rs.getInt("count");
                    RegistrationCountPerSportDTO dto = new RegistrationCountPerSportDTO(sport, count);
                    registrationCountDTOList.add(dto);
                }
            } catch (SQLException throwables) {
                // TODO: Make own throwable exception and let it bubble upwards
                throwables.printStackTrace();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return registrationCountDTOList;
    }

}
