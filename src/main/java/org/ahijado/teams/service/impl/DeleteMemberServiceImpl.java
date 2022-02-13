package org.ahijado.teams.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.ahijado.teams.model.PersonalInfo;
import org.ahijado.teams.model.TeamInfo;
import org.ahijado.teams.model.TeamList;
import org.ahijado.teams.service.DeleteMemberService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class DeleteMemberServiceImpl extends BaseService implements DeleteMemberService {

    @Value("${app.services.delete-member.url}")
    private String serviceUrl;

    @Value("${app.services.delete-member.method}")
    private HttpMethod method;

    @Override
    public void deleteMember(TeamInfo teamInfo, PersonalInfo personalInfo) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Void> response = restTemplate.exchange(serviceUrl, method, getHttpEntity(), Void.class, teamInfo.getId(), personalInfo.getId());
        if (response.getStatusCode().is2xxSuccessful()) {
            log.info("Member {} deleted from team {}", personalInfo.getDisplayName(), teamInfo.getDisplayName());
        }
    }
}
