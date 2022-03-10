import dtos.RegistrationCountPerSportDTO;
import dtos.RegistrationCountPerTeamDTO;
import entities.Member;
import entities.Registration;
import persistence.Database;
import persistence.MemberMapper;
import persistence.RegistrationMapper;
import persistence.TeamMapper;

import java.util.List;

public class Main {

    private final static String USER = "root";
    private final static String PASSWORD = "root";
    private final static String URL = "jdbc:mysql://localhost:3306/sportsclub?serverTimezone=CET&useSSL=false&allowPublicKeyRetrieval=true";

    public static void main(String[] args) {

        Database db = new Database(USER, PASSWORD, URL);

        MemberMapper memberMapper = new MemberMapper(db);
        RegistrationMapper registrationMapper = new RegistrationMapper(db);
        TeamMapper teamMapper = new TeamMapper(db);

        List<Member> members = memberMapper.getAllMembers();
        System.out.println("Tilmeldinger pr. hold: ********************************");
        List<RegistrationCountPerTeamDTO> registrations = registrationMapper.getRegistrationCountPerTeam();
        registrations.forEach(System.out::println);

        System.out.println("Tilmeldinger pr. sportsgren: ********************************");
        List<RegistrationCountPerSportDTO> sportRegistrations = registrationMapper.getRegistrationCountPerSport();
        sportRegistrations.forEach(System.out::println);


        showMembers(members);
        showMemberById(memberMapper, 13);


            int newMemberId = insertMember(memberMapper);
            insertRegistration(4, "ten01", 175, teamMapper);
             /*
            deleteMember(newMemberId, memberMapper);
            showMembers(members);
            updateMember(13, memberMapper);
        */
    }

    private static void deleteMember(int memberId, MemberMapper memberMapper) {
        if (memberMapper.deleteMember(memberId)){
            System.out.println("Member with id = " + memberId + " is removed from DB");
        }
    }

    private static int insertMember(MemberMapper memberMapper) {
        Member m1 = new Member("BENNY Olsen", "Banegade 2", 3700, "Rønne", "m", 1967);
        Member m2 = memberMapper.insertMember(m1);
        showMemberById(memberMapper, m2.getMemberId());
        return m2.getMemberId();
    }

    private static void insertRegistration(int member_id, String team_id, int price, TeamMapper teamMapper)
    {
        Registration reg = teamMapper.addToTeam(member_id, team_id, price);
        System.out.println("Ny tilmelding: " + reg);
    }

    private static void updateMember(int memberId, MemberMapper memberMapper) {
        Member m1 = memberMapper.getMemberById(memberId);
        m1.setYear(1970);
        if(memberMapper.updateMember(m1)){
            showMemberById(memberMapper, memberId);
        }
    }

    private static void showMemberById(MemberMapper memberMapper, int memberId) {
        System.out.println("***** Vis medlem nr. 13: *******");
        System.out.println(memberMapper.getMemberById(memberId).toString());
    }

    private static void showMembers(List<Member> members) {
        System.out.println("***** Vis alle medlemmer *******");
        for (Member member : members) {
            System.out.println(member);
        }
    }


}
