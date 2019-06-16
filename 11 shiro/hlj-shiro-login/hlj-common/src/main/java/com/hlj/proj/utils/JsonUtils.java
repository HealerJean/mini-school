package com.hlj.proj.utils;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;


/**
 * @ClassName JsonUtils
 * @Author TD
 * @Date 2019/1/10 10:12
 * @Description Json处理工具
 */
public class JsonUtils {

    public static ObjectMapper objectMapper ;
    public static ObjectMapper objectMapperSensitivity ;


    static {
        //Mapper创建
        objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS,false);
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        //脱敏日志创建
        objectMapperSensitivity = new ObjectMapper();
        objectMapperSensitivity.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapperSensitivity.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
        objectMapperSensitivity.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS,false);
        objectMapperSensitivity.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.registerModule(new JavaTimeModule());

    }

    /**
     * 对象转Json格式字符串----脱敏处理
     * @return
     */
    public static String toJsonStringWithSensitivity(Object pojo){
        try {
            return objectMapperSensitivity.writeValueAsString(pojo);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e.getMessage(),e);
        }
    }
    /**
     * 对象转Json格式字符串
     * @return
     */
    public static String toJsonString(Object pojo){

        try {
            return objectMapper.writeValueAsString(pojo);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e.getMessage(),e);
        }
    }

    /**
     * json格式字符串转对象
     * @param json
     * @param c
     * @return
     */
    public static<T> T jsonToObject(String json,Class<T> c){

        T t = null;
        try {
            t = objectMapper.readValue(json, c);
        } catch (IOException e){
            throw new RuntimeException(e.getMessage(),e);
        }
        return t;
    }

}
