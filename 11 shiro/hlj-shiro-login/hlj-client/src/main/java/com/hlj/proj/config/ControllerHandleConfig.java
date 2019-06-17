package com.hlj.proj.config;

import com.hlj.proj.dto.ResponseBean;
import com.hlj.proj.enums.ResponseEnum;
import com.hlj.proj.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletResponse;

/**
 * @author HealerJean
 * @version 1.0v
 * @ClassName ControllerHandleExceptionConfig
 * @date 2019/5/31  20:19.
 * @Description
 */
@Slf4j
@ControllerAdvice
public class ControllerHandleConfig {


    /**
     * 业务异常，给前台返回异常数据
     * @param e
     * @return
     */
    @ExceptionHandler(value = BusinessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseBean businessExceptionHandler(BusinessException e) {
        log.error("业务异常------------异常信息：{}" , e.getMessage());
        return ResponseBean.buildFailure(e.getCode(),e.getMessage());
    }


    /**
     * 所有异常报错
     * @param e
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(value = HttpStatus.OK)
    public HttpEntity<String> allExceptionHandler(HttpServletResponse response, Exception e) {
        log.error("====系统错误===", e);
        response.setStatus(ResponseEnum.系统错误.code);
        HttpHeaders header = new HttpHeaders();
        header.add("Content-Type", "application/json");
        header.add("Charset", "UTF-8");
        return new HttpEntity<>(ResponseEnum.系统错误.msg, header);
    }
}
