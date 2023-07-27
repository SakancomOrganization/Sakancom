package models;

import enums.SaleStatus;

import java.util.Date;

public class SaleContract {
    private Date date;
    private User tenant;
    private SaleStatus saleStatus;

    public SaleContract(Date date, User tenant, SaleStatus saleStatus) {
        this.date = date;
        this.tenant = tenant;
        this.saleStatus = saleStatus;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getTenant() {
        return tenant;
    }

    public void setTenant(User tenant) {
        this.tenant = tenant;
    }

    public SaleStatus getSaleStatus() {
        return saleStatus;
    }

    public void setSaleStatus(SaleStatus saleStatus) {
        this.saleStatus = saleStatus;
    }
}
