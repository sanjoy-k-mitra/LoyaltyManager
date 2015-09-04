package com.pixisolutions.loyaltymanager.models;

import java.util.Date;

/**
 * Created by sanjoy on 8/28/15.
 */
public class Item {
    protected Long id;
    protected String code;
    protected String name;
    protected Integer point;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }
}
