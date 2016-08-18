/*
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.tsh.recharge.foundation.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.Properties;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

/**
 *
 * @author zengzw
 * @date 2016年7月27日
 */
public class PropertiesUtility {

    private PropertiesUtility() {
    }

    private  static Properties gaoyProperties = null;

    private  static Properties ofProperties = null;

    public static String getGyPhoneVlaue(String key){
        String result = loadGyPhone().getProperty(key);
        if(StringUtils.isEmpty(result)){
            return result;
        }
        
        try {
            result = new String(result.getBytes("ISO-8859-1"), "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }
    
    
    public static String getOfPhoneVlaue(String key){
        String result = loadOfPhone().getProperty(key);
        if(StringUtils.isEmpty(result)){
            return result;
        }
        
        try {
            result = new String(result.getBytes("ISO-8859-1"), "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }
    
    
    
    
    public static Properties loadGyPhone(){
        if(gaoyProperties != null){
            return gaoyProperties;
        }

        try {
            Resource resource = new ClassPathResource("gypay-phone.properties");
            gaoyProperties =  PropertiesLoaderUtils.loadProperties(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return gaoyProperties;
    }

    public static Properties loadOfPhone(){
        if(ofProperties != null){
            return ofProperties;
        }

        try {
            Resource resource = new ClassPathResource("ofpay-phone.properties");
            ofProperties =  PropertiesLoaderUtils.loadProperties(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ofProperties;
    }



    /**
     * Get the property from the class-path .
     * @param propsName
     *    The property name to which we get the value from .
     * @return Properties
     *     The properties loaded .
     * @throws Exception
     *    Any exception thrown , you will need to catch it .
     */
    public static Properties load(String propsName) throws Exception {
        Properties props = new Properties();
        URL url = ClassLoader.getSystemResource(propsName);
        props.load(url.openStream());
        return props;
    }

    /**
     * Load all the  Properties File
     * @param propsFile
     *    PropsFile which
     * @return Properties
     *    return the properties loaded from the file .
     * @throws IOException
     *  Any IOException thrown , you will need to catch it.
     */ 
    public static Properties load(File propsFile) throws IOException {
        Properties props = new Properties();
        FileInputStream fis = new FileInputStream(propsFile);
        props.load(fis);
        fis.close();
        return props;
    }



}

