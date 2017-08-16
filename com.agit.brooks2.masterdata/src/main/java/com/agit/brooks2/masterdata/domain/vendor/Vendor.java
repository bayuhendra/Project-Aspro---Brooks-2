package com.agit.brooks2.masterdata.domain.vendor;

import com.agit.brooks2.shared.object.EntityObject;
import com.agit.brooks2.shared.status.Status;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author 3AD
 */
public class Vendor implements EntityObject<Vendor> {

    long id;
    private String vendorID;
    private String namaVendor;
    private Status statusVendor;
    private String createdBy;
    private Date createdDate;
    private String modifiedBy;
    private Date modifiedDate;

    public Vendor() {
    }

    public Vendor(String vendorID, String namaVendor, Status statusVendor, String createdBy, Date createdDate, String modifiedBy, Date modifiedDate) {
        this.vendorID = vendorID;
        this.namaVendor = namaVendor;
        this.statusVendor = statusVendor;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
        this.modifiedBy = modifiedBy;
        this.modifiedDate = modifiedDate;
    }

    public void assignNewVendor(Vendor vendor) {
        this.vendorID = vendor.vendorID;
        this.namaVendor = vendor.namaVendor;
        this.statusVendor = vendor.statusVendor;
        this.modifiedBy = vendor.modifiedBy;
        this.modifiedDate = vendor.modifiedDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + Objects.hashCode(this.vendorID);
        hash = 71 * hash + Objects.hashCode(this.namaVendor);
        hash = 71 * hash + Objects.hashCode(this.statusVendor);
        hash = 71 * hash + Objects.hashCode(this.createdBy);
        hash = 71 * hash + Objects.hashCode(this.createdDate);
        hash = 71 * hash + Objects.hashCode(this.modifiedBy);
        hash = 71 * hash + Objects.hashCode(this.modifiedDate);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Vendor other = (Vendor) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public boolean sameIdentityAs(Vendor other) {
        return this.equals(other);
    }

}
