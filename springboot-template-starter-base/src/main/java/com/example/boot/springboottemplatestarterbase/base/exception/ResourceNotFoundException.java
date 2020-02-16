package com.example.boot.springboottemplatestarterbase.base.exception;

import com.example.boot.springboottemplatestarterbase.base.exception.base.BaseException;
import org.springframework.http.HttpStatus;

/**
 * [资源不存在]的异常类
 *
 * @author Chang
 * @date 2019/8/8 23:51
 */
@SuppressWarnings("unused")
public class ResourceNotFoundException extends BaseException {

    public ResourceNotFoundException() {
        super(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase());
    }

    public ResourceNotFoundException(String message) {
        super(HttpStatus.NOT_FOUND.value(), message);
    }

    public ResourceNotFoundException(HttpStatus httpStatus) {super(httpStatus);}

    public ResourceNotFoundException(HttpStatus httpStatus, String message) {super(httpStatus, message);}
}
