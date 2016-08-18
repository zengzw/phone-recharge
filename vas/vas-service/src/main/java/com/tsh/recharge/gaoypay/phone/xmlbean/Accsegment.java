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
@XmlRootElement(name="accsegment")
public class Accsegment {

    @XmlElementWrapper(name="acc")
    @XmlElement(name="mobile")
    private List<MobileAttr> mobileAttrs;

    public List<MobileAttr> getMobileAttrs() {
        return mobileAttrs;
    }

    public void setMobileAttrs(List<MobileAttr> mobileAttrs) {
        this.mobileAttrs = mobileAttrs;
    }
}
