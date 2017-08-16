package com.agit.brooks2.common.dto.masterdata;

import com.agit.brooks2.shared.status.Status;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author 3AD
 */
public class VendorDTO implements Serializable {

    private String vendorID;
    private String namaVendor;
    private Status statusVendor;
    private String createdBy;
    private Date createdDate;
    private String modifiedBy;
    private Date modifiedDate;

    public VendorDTO() {
    }

    public VendorDTO(String vendorID, String namaVendor, Status statusVendor, String createdBy, Date createdDate, String modifiedBy, Date modifiedDate) {
        this.vendorID = vendorID;
        this.namaVendor = namaVendor;
        this.statusVendor = statusVendor;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
        this.modifiedBy = modifiedBy;
        this.modifiedDate = modifiedDate;
    }

    public String getVendorID() {
        return vendorID;
    }

    public void setVendorID(String vendorID) {
        this.vendorID = vendorID;
    }

    public String getNamaVendor() {
        return namaVendor;
    }

    public void setNamaVendor(String namaVendor) {
        this.namaVendor = namaVendor;
    }

    public Status getStatusVendor() {
        return statusVendor;
    }

    public void setStatusVendor(Status statusVendor) {
        this.statusVendor = statusVendor;
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
        return "VendorDTO{" + "vendorID=" + vendorID + ", namaVendor=" + namaVendor + ", statusVendor=" + statusVendor + ", createdBy=" + createdBy + ", createdDate=" + createdDate + ", modifiedBy=" + modifiedBy + ", modifiedDate=" + modifiedDate + '}';
    }

}
