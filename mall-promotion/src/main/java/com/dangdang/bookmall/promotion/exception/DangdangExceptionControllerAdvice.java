package com.dangdang.bookmall.promotion.exception;

import com.dangdang.common.exception.ErrorCodeEnum;
import com.dangdang.common.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zengyuzhi
 * @date 2020/10/21 下午12:59
 */

@Slf4j
@RestControllerAdvice(basePackages = "com.dangdang.bookmall.promotion.controller")
public class DangdangExceptionControllerAdvice {
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public R handleVaildException(MethodArgumentNotValidException e){
        BindingResult bindingResult = e.getBindingResult();
        log.error("数据校验异常"+e.getMessage());

        //errorMap
        Map<String,String> errorMap = new HashMap<>();
        bindingResult.getFieldErrors().forEach((fieldError)->{
            errorMap.put(fieldError.getField(),fieldError.getDefaultMessage());
        });
        return R.error(ErrorCodeEnum.VALID_EXCEPTION.getCode(),ErrorCodeEnum.VALID_EXCEPTION.getMsg()).put("data",errorMap);
    }
}
