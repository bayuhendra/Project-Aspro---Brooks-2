package com.agit.brooks2.common.dto.core;

import com.agit.brooks2.shared.status.Status;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Zaky
 */
public class SecondaryUnitDTO implements Serializable {

    private String idSecondaryUnit;
    private String nameSecondaryUnit;
    private String detailSecondaryUnit;
    private byte dataPhotoSecondaryUnit;
    private String urlPhotoSecondaryUnit;
    private String createdBy;
    private Date createdDate;
    private String modifiedBy;
    private Date modifiedDate;
    private Status status;

    public SecondaryUnitDTO() {
    }

    public SecondaryUnitDTO(String idSecondaryUnit, String nameSecondaryUnit, String detailSecondaryUnit, byte dataPhotoSecondaryUnit, String urlPhotoSecondaryUnit, String createdBy, Date createdDate, String modifiedBy, Date modifiedDate, Status status) {
        this.idSecondaryUnit = idSecondaryUnit;
        this.nameSecondaryUnit = nameSecondaryUnit;
        this.detailSecondaryUnit = detailSecondaryUnit;
        this.dataPhotoSecondaryUnit = dataPhotoSecondaryUnit;
        this.urlPhotoSecondaryUnit = urlPhotoSecondaryUnit;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
        this.modifiedBy = modifiedBy;
        this.modifiedDate = modifiedDate;
        this.status = status;
    }

    @Override
    public String toString() {
        return "SecondaryUnitDTO{" + "idSecondaryUnit=" + idSecondaryUnit + ", nameSecondaryUnit=" + nameSecondaryUnit + ", detailSecondaryUnit=" + detailSecondaryUnit + ", dataPhotoSecondaryUnit=" + dataPhotoSecondaryUnit + ", urlPhotoSecondaryUnit=" + urlPhotoSecondaryUnit + ", createdBy=" + createdBy + ", createdDate=" + createdDate + ", modifiedBy=" + modifiedBy + ", modifiedDate=" + modifiedDate + ", status=" + status + '}';
    }

    public String getIdSecondaryUnit() {
        return idSecondaryUnit;
    }

    public void setIdSecondaryUnit(String idSecondaryUnit) {
        this.idSecondaryUnit = idSecondaryUnit;
    }

    public String getNameSecondaryUnit() {
        return nameSecondaryUnit;
    }

    public void setNameSecondaryUnit(String nameSecondaryUnit) {
        this.nameSecondaryUnit = nameSecondaryUnit;
    }

    public String getDetailSecondaryUnit() {
        return detailSecondaryUnit;
    }

    public void setDetailSecondaryUnit(String detailSecondaryUnit) {
        this.detailSecondaryUnit = detailSecondaryUnit;
    }

    public byte getDataPhotoSecondaryUnit() {
        return dataPhotoSecondaryUnit;
    }

    public void setDataPhotoSecondaryUnit(byte dataPhotoSecondaryUnit) {
        this.dataPhotoSecondaryUnit = dataPhotoSecondaryUnit;
    }

    public String getUrlPhotoSecondaryUnit() {
        return urlPhotoSecondaryUnit;
    }

    public void setUrlPhotoSecondaryUnit(String urlPhotoSecondaryUnit) {
        this.urlPhotoSecondaryUnit = urlPhotoSecondaryUnit;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}
