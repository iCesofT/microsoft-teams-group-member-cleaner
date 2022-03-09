package org.ahijado.teams.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.ahijado.teams.model.PersonalInfo;
import org.ahijado.teams.service.PersonalService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
@Slf4j
public class PersonalServiceImpl extends BaseService implements PersonalService {

    @Value("${app.services.personal.endpoint}")
    private String serviceUrl;

    @Value("${app.services.personal.method}")
    private HttpMethod method;

    @Override
    public Optional<PersonalInfo> getPersonalInfo() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<PersonalInfo> response = restTemplate.exchange(serviceUrl, method, getHttpEntity(), PersonalInfo.class);
        if (response.getStatusCode().is2xxSuccessful()) {
            return Optional.of(response.getBody());
        } else {
            return Optional.empty();
        }
    }

}
