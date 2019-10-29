package com.xinke.common.base;

import java.io.Serializable;

/**
 * @author: zhaihx
 * @description:
 * @date:2019/10/28
 */
public class Response<T> extends BaseEntity implements Serializable {

    /**
     * @Fields SUCCESS : 成功码
     */
    public static final String SUCCESS_CODE = "0000";

    /**
     * @Fields SUCCESS : 成功信息
     */
    public static final String SUCCESS_MSG = "Success";

    /**
     * @Fields serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * 成功返回实体
     */
    public static final Response SUCCESS = new Response(null);

    /**
     * 创建成功返回实体
     * <p>Title: </p>
     * <p>Description: </p>
     * @param data
     */
    public Response(T data) {
        this.setResultCode(SUCCESS_CODE);
        this.setResultMsg(SUCCESS_MSG);
        if(data!=null)
            this.setData(data);
    }

    /**
     * 创建失败返回实体
     * <p>Title: </p>
     * <p>Description: </p>
     * @param errcode
     * @param errmsg
     */
    public Response(String errcode, String errmsg) {
        this.setResultCode(errcode);
        this.setResultMsg(errmsg);
    }

    /**
     * 返回码
     */
    private String resultCode;

    /**
     * 返回描述
     */
    private String resultMsg;

    /**
     * 返回数据对象
     */
    private T data;

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    /**
     *
     * @Title: setSuccessData
     * @Description: 返回成功数据
     * @param data 数据
     */
    public void setSuccessData(T data) {
        this.setResultCode(SUCCESS_CODE);
        this.setResultMsg(SUCCESS_MSG);
        this.setData(data);
    }

    /**
     *
     * @Title: setErrorData
     * @Description: 返回失败数据
     * @param errcode 返回错误码
     * @param errmsg 返回错误描述
     */
    public void setErrorData(String errcode, String errmsg) {
        this.setResultCode(errcode);
        this.setResultMsg(errmsg);
    }

}
