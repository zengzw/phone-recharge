/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.tsh.recharge.gaoypay.phone.xmlbean;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author zengzw
 * @date 2016年7月21日
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="fill")
public class PostResultElement {
    @XmlElementWrapper(name="items")
    @XmlElement(name="item")
    private List<ItemElement> items;
    
    

    public List<ItemElement> getItems() {
        return items;
    }

    public void setItems(List<ItemElement> items) {
        this.items = items;
    }
}
