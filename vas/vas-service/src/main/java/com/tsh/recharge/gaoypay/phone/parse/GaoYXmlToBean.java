/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.tsh.recharge.gaoypay.phone.parse;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import com.tsh.recharge.foundation.utils.HttpUtils;
import com.tsh.recharge.foundation.utils.JaxbUtil;
import com.tsh.recharge.gaoypay.phone.xmlbean.Accsegment;
import com.tsh.recharge.gaoypay.phone.xmlbean.AllProducts;
import com.tsh.recharge.gaoypay.phone.xmlbean.ItemElement;
import com.tsh.recharge.gaoypay.phone.xmlbean.Mobile;
import com.tsh.recharge.gaoypay.phone.xmlbean.MobileAttr;
import com.tsh.recharge.gaoypay.phone.xmlbean.PostResult;
import com.tsh.recharge.gaoypay.phone.xmlbean.PostResultElement;
import com.tsh.recharge.gaoypay.phone.xmlbean.Product;
import com.tsh.recharge.gaoypay.phone.xmlbean.ProductAttr;
import com.tsh.recharge.gaoypay.phone.xmlbean.ProductElement;

/**
 * 高阳话费接口 xml 转 对象
 * 
 * @author zengzw
 * @date 2016年7月22日
 */
public class GaoYXmlToBean {


    /**
     * 号码段xml转换
     * 
     * @param result
     * @return
     * @throws UnsupportedEncodingException
     */
    public static Mobile parseSegment(String result) throws UnsupportedEncodingException{
        Accsegment lst = JaxbUtil.converyToJavaBean(result,Accsegment.class);

        Mobile mobile = new Mobile();
        for(MobileAttr attr:lst.getMobileAttrs()){
            String name = attr.getName();
            String value = attr.getValue();
            
            if(name.equals("isptype")){
                mobile.setSpType(URLDecoder.decode(value, HttpUtils.charset_utf8));
            }
            else if(name.equals("provincename")){
                mobile.setProvinceName(URLDecoder.decode(value, HttpUtils.charset_utf8));
            }
            else if(name.equals("citycode")){
                mobile.setCityCode(value);
            }
            else if(name.equals("detail")){
                mobile.setDetail(value);
            }

        }
        

        return mobile;

    }
    
    

    /**
     * 话费产品 转换
     * @param result
     * @return
     * @throws UnsupportedEncodingException
     */
    public static List<Product> parseProduct(String result) throws UnsupportedEncodingException{
        AllProducts lst = JaxbUtil.converyToJavaBean(result,AllProducts.class);

        List<Product> lstProduct = new ArrayList<>();
        for(ProductElement elem:lst.getProducts()){
            Product product = new Product();
            for(ProductAttr attr:elem.getProduct()){
                String name = attr.getName();
                String value = attr.getValue();

                if(name.equals("prodId")){
                    product.setProdId(value);  
                }
                else if(name.equals("prodContent")){
                    product.setProdContent(Integer.parseInt(value));
                }
                else if(name.equals("prodPrice")){
                    product.setProdPrice(Double.parseDouble(value));
                }
                else if(name.equals("prodIsptype")){
                    product.setProdIsptype(URLDecoder.decode(value, HttpUtils.charset_utf8));
                }
                else if(name.equals("prodDelaytimes")){
                    product.setProdDelaytimes(URLDecoder.decode(value, HttpUtils.charset_utf8));
                }
                else if(name.equals("prodProvinceid")){
                    product.setProdProvinceid(URLDecoder.decode(value, HttpUtils.charset_utf8));
                }
                else if(name.equals("prodType")){
                    product.setProdType(URLDecoder.decode(value, HttpUtils.charset_utf8));
                }
            }

            lstProduct.add(product);
        }

     
        return lstProduct;
    }

    
    /**
     * 订单查询、充值 返回结果转换
     * 
     * @param xml
     * @return
     */
    public static PostResult parseOrder(String xml){
        PostResultElement element = JaxbUtil.converyToJavaBean(xml, PostResultElement.class);

        PostResult postResult = new PostResult();
        for(ItemElement e : element.getItems()){
            String name = e.getName();
            String value = e.getValue();

            if(name.equals("orderid")){
                postResult.setOrderId(value);
            }else if(name.equals("resultno")){
                postResult.setResultNo(value);

            }else if(name.equals("finishmoney")){
                postResult.setFinishMoney(value);
            }
            else if(name.equals("prodid")){
                postResult.setProdId(value);
            }
            else if(name.equals("tranid")){
                postResult.setTranid(value);

            }else if(name.equals("verifystring")){
                postResult.setVerifyString(value);

            }else if(name.equals("mark")){
                postResult.setMark(value);
            }
        }

        return postResult;
    }
    

}
