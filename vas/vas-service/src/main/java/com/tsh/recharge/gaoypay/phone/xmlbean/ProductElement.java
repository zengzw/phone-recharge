/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.tsh.recharge.gaoypay.phone.xmlbean;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author zengzw
 * @date 2016年7月21日
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductElement {

    @XmlElement(name="product")
    private List<ProductAttr> product;

    public List<ProductAttr> getProduct() {
        return product;
    }

    public void setProduct(List<ProductAttr> product) {
        this.product = product;
    }

}
