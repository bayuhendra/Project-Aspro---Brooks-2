/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agit.brooks2.masterdata.domain.vendor;

import com.agit.brooks2.shared.status.Status;
import java.util.Date;

public class VendorBuilder {

    private String vendorID;
    private String namaVendor;
    private Status statusVendor;
    private String createdBy;
    private Date createdDate;
    private String modifiedBy;
    private Date modifiedDate;

    public VendorBuilder() {
    }

    public VendorBuilder setVendorID(String vendorID) {
        this.vendorID = vendorID;
        return this;
    }

    public VendorBuilder setNamaVendor(String namaVendor) {
        this.namaVendor = namaVendor;
        return this;
    }

    public VendorBuilder setStatusVendor(Status statusVendor) {
        this.statusVendor = statusVendor;
        return this;
    }

    public VendorBuilder setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public VendorBuilder setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public VendorBuilder setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
        return this;
    }

    public VendorBuilder setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
        return this;
    }

    public Vendor createVendor() {
        return new Vendor(vendorID, namaVendor, statusVendor, createdBy, createdDate, modifiedBy, modifiedDate);
    }

}
