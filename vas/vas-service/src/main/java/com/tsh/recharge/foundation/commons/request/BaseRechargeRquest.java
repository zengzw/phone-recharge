/*
 * Use, Copy is subject to authorized license.
 * Copyright (c) 2013, FPX and/or its affiliates. All rights reserved.
 */
package com.tsh.recharge.foundation.commons.request;


import com.tsh.recharge.foundation.commons.Configurations;
import com.tsh.recharge.foundation.commons.exections.SystemCodes;
import com.tsh.recharge.foundation.utils.PropertiesUtility;
import com.tsh.recharge.foundation.utils.StringUtils;

/**
 *
 * @author zengzw
 * @date 2016年7月26日
 */
public class BaseRechargeRquest {

    /**
     * 获取错误对象
     * 
     * @param code
     * @param value
     * @return
     */
    public static ResponseMessage getErrorMessage(String code,String inParameters){
        //根据Code 获取message
        ResponseMessage resultMessage = new ResponseMessage();
        resultMessage.setCode(code);
        resultMessage.setInParametes(inParameters);

        return resultMessage;
    }

    /**
     * 获取错误对象
     * 
     * @param code
     * @param value
     * @return
     */
    public static ResponseMessage getErrorMessage(String code,String value,String type){
        //根据Code 获取message
        ResponseMessage resultMessage = new ResponseMessage();
        resultMessage.setCode(code);
        resultMessage.setValue(value);
        resultMessage.setMessage(getPropertiesMessage(type, code));

        return resultMessage;
    }

    /**
     * 获取错误对象
     * 
     * @param code
     * @param value
     * @return
     */
    public static ResponseMessage getErrorMessage(String code,String value,String type,String inParameters){
        //根据Code 获取message
        ResponseMessage resultMessage = new ResponseMessage();
        resultMessage.setCode(code);
        resultMessage.setValue(value);
        resultMessage.setMessage(getPropertiesMessage(type, code));
        resultMessage.setInParametes(inParameters);

        return resultMessage;
    }

    /**
     * 读取资源文件
     * 
     * @param type
     * @param code
     * @return
     */
    private static String getPropertiesMessage(String type,String code){
        if(StringUtils.isEmpty(code)){
            return "";
        }
        
        if(type.equals(Configurations.SP_TYPE_GY)){
            return  PropertiesUtility.getGyPhoneVlaue(code);
        }
        else if(type.equals(Configurations.SP_TYPE_OF)){
            return PropertiesUtility.getOfPhoneVlaue(code);
        }
        
        return null;
    }


    /**
     * 获取错误对象
     * 
     * @param code
     * @param value
     * @return
     */
    public static ResponseMessage getErrorMessage(String code){
        //根据Code 获取message
        ResponseMessage resultMessage = new ResponseMessage();
        resultMessage.setCode(code);
        resultMessage.setMessage("");
        return resultMessage;
    }


    /**
     * 获取成功对象
     * @param value
     * @return
     */
    public static ResponseMessage getSuccessMessage(String value){
        ResponseMessage resultMessage = new ResponseMessage();
        resultMessage.setCode(SystemCodes.SUCCESS_CODE);
        resultMessage.setValue(value);

        return resultMessage;
    }


    /**
     * 获取成功对象
     * @param value
     * @return
     */
    public static ResponseMessage getSuccessMessage(String value,String inParmeter){
        ResponseMessage resultMessage = new ResponseMessage();
        resultMessage.setCode(SystemCodes.SUCCESS_CODE);
        resultMessage.setValue(value);
        resultMessage.setInParametes(inParmeter);

        return resultMessage;
    }


    /**
     * 获取成功对象
     * @param value
     * @return
     */
    public static ResponseMessage getSuccessMessage(String code,String value,String type){
        ResponseMessage resultMessage = new ResponseMessage();
        resultMessage.setCode(code);
        resultMessage.setValue(value);
        resultMessage.setMessage(getPropertiesMessage(type, code));

        return resultMessage;
    }


    /**
     * 获取成功对象
     * @param value
     * @return
     */
    public static ResponseMessage getSuccessMessage(String code,String value,String type,String inParametes){
        ResponseMessage resultMessage = new ResponseMessage();
        resultMessage.setCode(code);
        resultMessage.setValue(value);
        resultMessage.setMessage(getPropertiesMessage(type, code));
        resultMessage.setInParametes(inParametes);

        return resultMessage;
    }
}
