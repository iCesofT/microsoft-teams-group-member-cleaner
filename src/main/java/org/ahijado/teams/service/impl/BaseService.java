package org.ahijado.teams.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.util.Collections;

class BaseService {

    @Value("${app.access-token}")
    private String accessToken;

    protected final HttpEntity<?> getHttpEntity() {
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        httpHeaders.set("Authorization", "Bearer " + accessToken);

        return new HttpEntity<>(httpHeaders);
    }
}
