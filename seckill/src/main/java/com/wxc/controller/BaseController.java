package com.wxc.controller;

import com.wxc.error.BuinessException;
import com.wxc.error.EmBuinessError;
import com.wxc.response.CommonReturnType;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class BaseController {
    public static final String CONTENT_TYPE_FORMED="application/x-www-form-urlencoded";
    //定义exceptionhandler解决未被controller层吸收的exception
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Object handleException(HttpServletRequest request,Exception e){
        Map<String,Object> reponseData = new HashMap<>();
        if(e instanceof BuinessException){
            BuinessException exception = (BuinessException)e;
            reponseData.put("errCode",exception.getErrorCode());
            reponseData.put("errMsg",exception.getErrorMsg());
        }else{
            reponseData.put("errCode", EmBuinessError.UNKNOWN_ERROR.getErrorCode());
            reponseData.put("errMsg",EmBuinessError.UNKNOWN_ERROR.getErrorMsg());
        }
        return CommonReturnType.create(reponseData,"fail");

    }
}
