package org.ahijado.teams.service;

import org.ahijado.teams.model.PersonalInfo;
import org.ahijado.teams.model.TeamInfo;

public interface DeleteMemberService {
    void showWhiteList();
    void deleteMember(TeamInfo teamInfo, PersonalInfo personalInfo);
}
