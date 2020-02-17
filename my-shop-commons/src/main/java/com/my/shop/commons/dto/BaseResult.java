package com.my.shop.commons.dto;

import java.io.Serializable;
import java.util.BitSet;

/**
 * @program:my-shop
 * @description:基础返回响应类
 * @author:xxs_cjw
 * @create:2020-02-07 10:30
 **/
public class BaseResult implements Serializable {

    public static final int SUCCESS_200 = 200;
    public static final int SUCCESS_500 = 500;

    private int status;
    private String message;

    public static BaseResult success(){
        return createBaseResult(SUCCESS_200,"成功");
    }

    public static BaseResult success(String message){
        return createBaseResult(SUCCESS_200,message);
    }

    public static BaseResult fail(){
        return createBaseResult(SUCCESS_500,"失败");
    }

    public static BaseResult fail(String message){
        return createBaseResult(SUCCESS_500,message);
    }

    public static BaseResult fail(int status,String message){
        return createBaseResult(status,message);
    }

    /**
     * 构建返回对象
     * @param status
     * @param message
     * @return BaseResult
     */
    public static BaseResult createBaseResult(int status,String message){
        BaseResult baseResult = new BaseResult();
        baseResult.setStatus(status);
        baseResult.setMessage(message);
        return baseResult;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
