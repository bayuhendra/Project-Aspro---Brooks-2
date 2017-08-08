/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agit.brooks2.masterdata.domain;

import com.agit.brooks2.shared.status.Status;
import java.util.Date;

public class HargaPenawaranBuilder {

    private String hargaPenawaranID;
    private String harga;
    private Status status;
    private String createdBy;
    private Date createdDate;
    private String modifiedBy;
    private Date modifiedDate;

    public HargaPenawaranBuilder() {
    }

    public HargaPenawaranBuilder setHargaPenawaranID(String hargaPenawaranID) {
        this.hargaPenawaranID = hargaPenawaranID;
        return this;
    }

    public HargaPenawaranBuilder setHarga(String harga) {
        this.harga = harga;
        return this;
    }

    public HargaPenawaranBuilder setStatus(Status status) {
        this.status = status;
        return this;
    }

    public HargaPenawaranBuilder setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public HargaPenawaranBuilder setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public HargaPenawaranBuilder setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
        return this;
    }

    public HargaPenawaranBuilder setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
        return this;
    }

    public HargaPenawaran createHargaPenawaran() {
        return new HargaPenawaran(hargaPenawaranID, harga, status, createdBy, createdDate, modifiedBy, modifiedDate);
    }

}
