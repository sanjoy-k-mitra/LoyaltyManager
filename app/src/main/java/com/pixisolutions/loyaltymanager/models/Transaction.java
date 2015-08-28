package com.pixisolutions.loyaltymanager.models;

import java.util.Date;

/**
 * Created by sanjoy on 8/28/15.
 */
public class Transaction {
    private Long id;
    private Offer offer;
    private Integer amount;
    private Date created;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Offer getOffer() {
        return offer;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
