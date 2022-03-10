package persistence;

import entities.Member;
import entities.Registration;
import entities.Team;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeamMapper
{

        private Database database;

        public TeamMapper(Database database) {
            this.database = database;
        }

        public List<Team> getAllTeams() {

            List<Team> teamList = new ArrayList<>();

            String sql = "select * from team ";

            try (Connection connection = database.connect()) {
                try (PreparedStatement ps = connection.prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                        String teamId = rs.getString("team_id");
                        int sportId = rs.getInt("sport_id");
                        teamList.add(new Team(teamId, sportId));
                    }
                } catch (SQLException throwables) {
                    // TODO: Make own throwable exception and let it bubble upwards
                    throwables.printStackTrace();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return teamList;
        }


        public Registration addToTeam(int member_id, String team_id, int price){
            Registration registration = null;
            String sql = "insert into registration (member_id, team_id, price) values (?,?,?)";
            try (Connection connection = database.connect()) {
                try (PreparedStatement ps = connection.prepareStatement(sql)) {
                    ps.setInt(1, member_id);
                    ps.setString(2, team_id);
                    ps.setInt(3, price);
                    int rowsAffected = ps.executeUpdate();
                    if (rowsAffected == 1){
                        registration = new Registration(member_id, team_id, price);
                    }
                } catch (SQLException throwables) {
                    // TODO: Make own throwable exception and let it bubble upwards
                    throwables.printStackTrace();
                }
            } catch (SQLException throwables) {
                // TODO: Make own throwable exception and let it bubble upwards
                throwables.printStackTrace();
            }
                return registration;
        }

        private int getGeneratedKey(PreparedStatement ps) throws SQLException
        {
            int newId = 0;
            ResultSet idResultset = ps.getGeneratedKeys();
            if (idResultset.next())
            {
                newId = idResultset.getInt("GENERATED_KEY");
            }
            return newId;
        }

        public boolean updateMember(Member member) {
            boolean result = false;
            String sql =    "update member " +
                            "set name = ?, address = ?, zip = ?, gender = ?, year = ? " +
                            "where member_id = ?";
            try (Connection connection = database.connect()) {
                try (PreparedStatement ps = connection.prepareStatement(sql)) {
                    ps.setString(1, member.getName());
                    ps.setString(2, member.getAddress());
                    ps.setInt(3, member.getZip());
                    ps.setString(4, member.getGender());
                    ps.setInt(5, member.getYear());
                    ps.setInt(6, member.getMemberId());
                    int rowsAffected = ps.executeUpdate();
                    if (rowsAffected == 1){
                        result = true;
                    }
                } catch (SQLException throwables) {
                    // TODO: Make own throwable exception and let it bubble upwards
                    throwables.printStackTrace();
                }
            } catch (SQLException throwables) {
                // TODO: Make own throwable exception and let it bubble upwards
                throwables.printStackTrace();
            }
            return result;
        }
}
