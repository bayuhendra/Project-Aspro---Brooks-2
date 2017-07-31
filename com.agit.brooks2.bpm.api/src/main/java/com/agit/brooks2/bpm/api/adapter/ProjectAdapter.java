package com.agit.brooks2.bpm.api.adapter;

/**
 *
 * @author lintang
 */
public interface ProjectAdapter {

//    public void closeProject(String projectID);

    public boolean projectState(String projectID);

    public void informUser(String projectID);

//    public void updateProjectDone(String projectID);
}
