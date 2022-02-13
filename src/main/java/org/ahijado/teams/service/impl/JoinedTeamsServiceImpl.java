package org.ahijado.teams.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.ahijado.teams.model.PersonalInfo;
import org.ahijado.teams.model.TeamInfo;
import org.ahijado.teams.model.TeamList;
import org.ahijado.teams.service.JoinedTeamsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class JoinedTeamsServiceImpl extends BaseService implements JoinedTeamsService {

    @Value("${app.services.joined-teams.url}")
    private String serviceUrl;

    @Value("${app.services.joined-teams.method}")
    private HttpMethod method;

    @Value("${app.services.joined-teams.pattern}")
    private String pattern;

    @Override
    public List<TeamInfo> getJoinedTeams() {
        List<TeamInfo> result = new ArrayList<>();
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<TeamList> response = restTemplate.exchange(serviceUrl, method, getHttpEntity(), TeamList.class);
        if (response.getStatusCode().is2xxSuccessful()) {
            response.getBody().getValue().parallelStream().filter(team -> team.getDisplayName().matches(pattern)).forEach(result::add);
        }
        return result;
    }
}
