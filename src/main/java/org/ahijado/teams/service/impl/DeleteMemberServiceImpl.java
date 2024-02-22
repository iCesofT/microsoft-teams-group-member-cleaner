package org.ahijado.teams.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ahijado.teams.model.PersonalInfo;
import org.ahijado.teams.model.TeamInfo;
import org.ahijado.teams.service.DeleteMemberService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class DeleteMemberServiceImpl extends BaseService implements DeleteMemberService {

    @Value("${app.services.delete-member.endpoint}")
    private String serviceUrl;

    @Value("${app.services.delete-member.method}")
    private HttpMethod method;

    @Value("${app.services.delete-member.enabled:false}")
    private boolean enabled;

    @Value("${app.services.delete-member.white-list:}")
    private List<String> whiteList = new ArrayList<>();

    private final RestTemplate restTemplate;

    @Override
    public void showWhiteList() {
        log.info("Teams in white list: {}", whiteList.stream().collect(Collectors.joining(", ")));
    }

    @Override
    public void deleteMember(TeamInfo teamInfo, PersonalInfo personalInfo) {
        if (enabled) {
            if (!whiteList.contains(teamInfo.getDisplayName())) {
                ResponseEntity<Void> response = restTemplate.exchange(serviceUrl, method, getHttpEntity(), Void.class, teamInfo.getId(), personalInfo.getId());
                if (response.getStatusCode().is2xxSuccessful()) {
                    log.info("Member {} deleted from team {}", personalInfo.getDisplayName(), teamInfo.getDisplayName());
                }
            } else {
                log.info("Member {} not deleted from team {}", personalInfo.getDisplayName(), teamInfo.getDisplayName());
            }
        }
    }
}
