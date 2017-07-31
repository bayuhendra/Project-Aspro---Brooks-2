package com.agit.brooks2.main.viewmodel;

import com.agit.brooks2.common.dto.usermanagement.UserDTO;
import com.agit.brooks2.common.security.SecurityUtil;
import com.agit.brooks2.shared.zul.CommonViewModel;
import com.agit.brooks2.shared.zul.PageNavigation;
import com.agit.brooks2.util.DateUtil;
import java.util.Date;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zul.Window;

/**
 *
 * @author bayutridewanto
 */
public class DashboardVM {

    /* data user */
    private UserDTO user;
    private String userNPWP;
    private String role;
    private String uriPhoto;

    private String src;

    private PageNavigation previous;
    private boolean checked;
    private int pageSize = 3;
    private int activePage = 0;
    private int selectedIndex;
    private int totalSize = 0;

    @Init
    public void init(@ContextParam(ContextType.COMPONENT) Window window) {
        userNPWP = SecurityUtil.getUserName();
        role = SecurityUtil.getUser().getRoleDTO().getRoleID();
        user = SecurityUtil.getUser();
        role = SecurityUtil.getUser().getRoleDTO().getRoleID();
        if (user.getUserSpecificationDTO().getFullName() != null) {
        } else if (user.getUserSpecificationDTO().getFullName() != null) {

        }
        switch (role) {
            default:
                uriPhoto = "/images/background.png";
                break;
        }
    }

    /* helper */
    public String concatUsername(String s1, String s2) {
        return s1.concat(s2);
    }

    public String concatDate(String s1, Date s2) {
        return s1.concat(DateUtil.dateToString(s2, "dd-MMM-yyy HH:mm:ss"));
    }

    @Command({"buttonOk", "buttonClose"})
    public void buttonOk(@ContextParam(ContextType.VIEW) Window window) {
        CommonViewModel.onCloseTab();
    }

    /* Getter&Setter */
    public String getUserNPWP() {
        return userNPWP;
    }

    public void setUserNPWP(String userNPWP) {
        this.userNPWP = userNPWP;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUriPhoto() {
        return uriPhoto;
    }

    public void setUriPhoto(String uriPhoto) {
        this.uriPhoto = uriPhoto;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public PageNavigation getPrevious() {
        return previous;
    }

    public void setPrevious(PageNavigation previous) {
        this.previous = previous;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getActivePage() {
        return activePage;
    }

    public void setActivePage(int activePage) {
        this.activePage = activePage;
    }

    public int getSelectedIndex() {
        return selectedIndex;
    }

    public void setSelectedIndex(int selectedIndex) {
        this.selectedIndex = selectedIndex;
    }

    public int getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(int totalSize) {
        this.totalSize = totalSize;
    }

}
