package org.ahijado.teams.service;

import org.ahijado.teams.model.PersonalInfo;

import java.util.Optional;

public interface PersonalService {
    Optional<PersonalInfo> getPersonalInfo();
}
