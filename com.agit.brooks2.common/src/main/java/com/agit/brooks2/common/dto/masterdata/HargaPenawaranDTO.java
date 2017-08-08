package com.agit.brooks2.common.dto.masterdata;

import com.agit.brooks2.shared.status.Status;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author 3AD
 */
public class HargaPenawaranDTO implements Serializable {

    private String hargaPenawaranID;
    private String harga;
    private Status status;
    private String createdBy;
    private Date createdDate;
    private String modifiedBy;
    private Date modifiedDate;

    public HargaPenawaranDTO() {
    }

    public HargaPenawaranDTO(String hargaPenawaranID, String harga, Status status, String createdBy, Date createdDate, String modifiedBy, Date modifiedDate) {
        this.hargaPenawaranID = hargaPenawaranID;
        this.harga = harga;
        this.status = status;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
        this.modifiedBy = modifiedBy;
        this.modifiedDate = modifiedDate;
    }

    public String getHargaPenawaranID() {
        return hargaPenawaranID;
    }

    public void setHargaPenawaranID(String hargaPenawaranID) {
        this.hargaPenawaranID = hargaPenawaranID;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
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

    @Override
    public String toString() {
        return "HargaPenawaranDTO{" + "hargaPenawaranID=" + hargaPenawaranID + ", harga=" + harga + ", status=" + status + ", createdBy=" + createdBy + ", createdDate=" + createdDate + ", modifiedBy=" + modifiedBy + ", modifiedDate=" + modifiedDate + '}';
    }

}
