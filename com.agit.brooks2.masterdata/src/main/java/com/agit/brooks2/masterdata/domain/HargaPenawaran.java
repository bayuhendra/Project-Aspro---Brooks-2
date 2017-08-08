package com.agit.brooks2.masterdata.domain;

import com.agit.brooks2.shared.object.EntityObject;
import com.agit.brooks2.shared.status.Status;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author 3AD
 */
public class HargaPenawaran implements EntityObject<HargaPenawaran> {

    long id;
    private String hargaPenawaranID;
    private String harga;
    private Status status;
    private String createdBy;
    private Date createdDate;
    private String modifiedBy;
    private Date modifiedDate;

    public HargaPenawaran() {
    }

    public HargaPenawaran(String hargaPenawaranID, String harga, Status status, String createdBy, Date createdDate, String modifiedBy, Date modifiedDate) {
        this.hargaPenawaranID = hargaPenawaranID;
        this.harga = harga;
        this.status = status;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
        this.modifiedBy = modifiedBy;
        this.modifiedDate = modifiedDate;
    }

    public void assignNewHargaPenawaran(HargaPenawaran hargaPenawaran) {
        this.hargaPenawaranID = hargaPenawaran.hargaPenawaranID;
        this.harga = hargaPenawaran.harga;
        this.status = hargaPenawaran.status;
        this.modifiedBy = hargaPenawaran.modifiedBy;
        this.modifiedDate = hargaPenawaran.modifiedDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.hargaPenawaranID);
        hash = 79 * hash + Objects.hashCode(this.harga);
        hash = 79 * hash + Objects.hashCode(this.status);
        hash = 79 * hash + Objects.hashCode(this.createdBy);
        hash = 79 * hash + Objects.hashCode(this.createdDate);
        hash = 79 * hash + Objects.hashCode(this.modifiedBy);
        hash = 79 * hash + Objects.hashCode(this.modifiedDate);
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
        final HargaPenawaran other = (HargaPenawaran) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public boolean sameIdentityAs(HargaPenawaran other) {
        return this.equals(other);
    }

}
