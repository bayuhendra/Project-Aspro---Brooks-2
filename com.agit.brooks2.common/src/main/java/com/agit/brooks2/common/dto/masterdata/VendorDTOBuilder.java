/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agit.brooks2.common.dto.masterdata;

import com.agit.brooks2.shared.status.Status;
import java.util.Date;

public class VendorDTOBuilder {

    private String vendorID;
    private String namaVendor;
    private Status statusVendor;
    private String createdBy;
    private Date createdDate;
    private String modifiedBy;
    private Date modifiedDate;

    public VendorDTOBuilder() {
    }

    public VendorDTOBuilder setVendorID(String vendorID) {
        this.vendorID = vendorID;
        return this;
    }

    public VendorDTOBuilder setNamaVendor(String namaVendor) {
        this.namaVendor = namaVendor;
        return this;
    }

    public VendorDTOBuilder setStatusVendor(Status statusVendor) {
        this.statusVendor = statusVendor;
        return this;
    }

    public VendorDTOBuilder setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public VendorDTOBuilder setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public VendorDTOBuilder setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
        return this;
    }

    public VendorDTOBuilder setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
        return this;
    }

    public VendorDTO createVendorDTO() {
        return new VendorDTO(vendorID, namaVendor, statusVendor, createdBy, createdDate, modifiedBy, modifiedDate);
    }

}
