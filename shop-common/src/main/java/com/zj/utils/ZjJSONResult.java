package com.zj.utils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

/**
 * @author 张建
 * @version V1.0
 * @Package com.icoom.utils
 * @date 2020/3/19 23:21
 * @Copyright © 安徽华泓信息技术有限公司
 * @description: 自定义响应数据结构
 * 本类可以提供给H5/IOS/安卓/公众号/小程序 使用
 * 前端你接收此类数据（json object）后，可自行根据业务去实现相关功能
 * <p>
 * 200：表示成功
 * 500：表示错误，错误信息在msg字段
 * 501：bean验证错误，不管多少个错误，都以map返回
 * 502：拦截器拦截到用户token出错
 * 555：异常抛出信息
 * 556：用户qq校验异常
 */

public class ZjJSONResult {
    /**
     * 自定义json对象
     */
    private static final ObjectMapper MAPPER = new ObjectMapper();
    /**
     * 响应业务状态
     */
    private Integer status;
    /**
     * 响应中的数据
     */
    private Object data;
    /**
     * 响应消息
     */
    private String msg;

    @JsonIgnore
    private String ok;//不使用


    public static ZjJSONResult build(Integer status, String msg, Object data) {
        return new ZjJSONResult(status, msg, data);
    }

    public static ZjJSONResult ok(Object data) {
        return new ZjJSONResult(data);
    }

    public static ZjJSONResult ok() {
        return new ZjJSONResult(null);
    }

    public static ZjJSONResult errorMsg(String msg) {
        return new ZjJSONResult(500, msg, null);
    }

    public static ZjJSONResult errorMap(Object data) {
        return new ZjJSONResult(501, "error", data);
    }

    public static ZjJSONResult errorTokenMsg(String msg) {
        return new ZjJSONResult(502, msg, null);
    }

    public static ZjJSONResult errorException(String msg) {
        return new ZjJSONResult(555, msg, null);
    }

    public static ZjJSONResult errorUserQQ(String msg) {
        return new ZjJSONResult(556, msg, null);
    }

    public ZjJSONResult() {

    }

    public static ZjJSONResult build(Integer status, String msg) {
        return new ZjJSONResult(status, msg, null);
    }

    public ZjJSONResult(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public ZjJSONResult(Object data) {
        this.status = 200;
        this.msg = "OK";
        this.data = data;
    }

    public Boolean isOK() {
        return this.status == 200;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }


    /**
     * @Description: 将json结果集转化为ZjJSONResult对象, 需要转换的对象是一个类
     * @Param: jsonData
     * @Param: clazz
     * @return:
     * @Author: zj(119855181 @ qq.com)
     * @date: 2020/3/20
     */
    public static ZjJSONResult formatToPojo(String jsonData, Class<?> clazz) {
        try {
            if (clazz == null) {
                return MAPPER.readValue(jsonData, ZjJSONResult.class);
            }
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (clazz != null) {
                if (data.isObject()) {
                    obj = MAPPER.readValue(data.traverse(), clazz);
                } else if (data.isTextual()) {
                    obj = MAPPER.readValue(data.asText(), clazz);
                }
            }
            return build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
        } catch (Exception e) {
            return null;
        }
    }


    /**
     * @Description: 没有object对象的转化
     * @Param: json
     * @return:
     * @Author: zj(119855181 @ qq.com)
     * @date: 2020/3/20
     */
    public static ZjJSONResult format(String json) {
        try {
            return MAPPER.readValue(json, ZjJSONResult.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * @Description: Object是集合转化, 需要转换的对象是一个list
     * @Param: jsonData
     * @Param: clazz
     * @return:
     * @Author: zj(119855181 @ qq.com)
     * @date: 2020/3/20
     */
    public static ZjJSONResult formatToList(String jsonData, Class<?> clazz) {
        try {
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (data.isArray() && data.size() > 0) {
                obj = MAPPER.readValue(data.traverse(),
                        MAPPER.getTypeFactory().constructCollectionType(List.class, clazz));
            }
            return build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
        } catch (Exception e) {
            return null;
        }
    }

    public String getOk() {
        return ok;
    }

    public void setOk(String ok) {
        this.ok = ok;
    }


}
