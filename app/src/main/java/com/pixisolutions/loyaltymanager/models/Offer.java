package com.pixisolutions.loyaltymanager.models;

import android.provider.BaseColumns;

import java.util.Date;

/**
 * Created by sanjoy on 8/28/15.
 */
public class Offer {
    protected Integer id;
    protected String name;
    protected String description;
    protected Long point;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getPoint() {
        return point;
    }

    public void setPoint(Long point) {
        this.point = point;
    }
}
