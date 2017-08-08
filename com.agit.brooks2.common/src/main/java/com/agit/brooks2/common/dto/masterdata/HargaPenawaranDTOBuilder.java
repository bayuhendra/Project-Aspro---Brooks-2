/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agit.brooks2.common.dto.masterdata;

import com.agit.brooks2.shared.status.Status;
import java.util.Date;

public class HargaPenawaranDTOBuilder {

    private String hargaPenawaranID;
    private String harga;
    private Status status;
    private String createdBy;
    private Date createdDate;
    private String modifiedBy;
    private Date modifiedDate;

    public HargaPenawaranDTOBuilder() {
    }

    public HargaPenawaranDTOBuilder setHargaPenawaranID(String hargaPenawaranID) {
        this.hargaPenawaranID = hargaPenawaranID;
        return this;
    }

    public HargaPenawaranDTOBuilder setHarga(String harga) {
        this.harga = harga;
        return this;
    }

    public HargaPenawaranDTOBuilder setStatus(Status status) {
        this.status = status;
        return this;
    }

    public HargaPenawaranDTOBuilder setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public HargaPenawaranDTOBuilder setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public HargaPenawaranDTOBuilder setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
        return this;
    }

    public HargaPenawaranDTOBuilder setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
        return this;
    }

    public HargaPenawaranDTO createHargaPenawaranDTO() {
        return new HargaPenawaranDTO(hargaPenawaranID, harga, status, createdBy, createdDate, modifiedBy, modifiedDate);
    }

}
