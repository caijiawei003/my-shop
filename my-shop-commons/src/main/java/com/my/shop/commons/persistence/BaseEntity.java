package com.my.shop.commons.persistence;

import java.io.Serializable;
import java.util.Date;

/**
 * @program:my-shop
 * @description:实体类基础父类
 * @author:xxs_cjw
 * @create:2020-02-20 09:38
 **/
public abstract class BaseEntity implements Serializable {
    private Long id;
    private Date updated;
    private Date created;

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
