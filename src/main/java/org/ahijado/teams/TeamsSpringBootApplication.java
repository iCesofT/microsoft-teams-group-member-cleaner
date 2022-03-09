package org.ahijado.teams;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ahijado.teams.model.PersonalInfo;
import org.ahijado.teams.model.TeamInfo;
import org.ahijado.teams.model.TeamList;
import org.ahijado.teams.service.DeleteMemberService;
import org.ahijado.teams.service.JoinedTeamsService;
import org.ahijado.teams.service.PersonalService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
@RequiredArgsConstructor
@Slf4j
public class TeamsSpringBootApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(TeamsSpringBootApplication.class, args);
    }

    private final PersonalService personalService;
    private final JoinedTeamsService joinedTeamsService;
    private final DeleteMemberService deleteMemberService;

    @Override
    public void run(String... args) throws Exception {
        Optional<PersonalInfo> optInfo = personalService.getPersonalInfo();
        if (optInfo.isPresent()) {
            PersonalInfo info = optInfo.get();
            log.info("Personal info: {}", info);

            List<TeamInfo> teamList = joinedTeamsService.getJoinedTeams();
            log.info("Joined teams: {}", teamList);

            teamList.forEach(team -> {
                deleteMemberService.deleteMember(team, info);
            });
        }
    }
}
