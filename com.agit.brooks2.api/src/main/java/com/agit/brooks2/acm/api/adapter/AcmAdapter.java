package com.agit.brooks2.acm.api.adapter;

import java.util.List;
import org.activiti.engine.identity.User;

/**
 *
 * @author lintang
 */
public interface AcmAdapter {

    public boolean isUserExistInCandidateUser(String userID, List<User> members);

    public User getUserProperty(String userID);

    public List<String> getRoleSetup(String projectID, String roleName);
}
