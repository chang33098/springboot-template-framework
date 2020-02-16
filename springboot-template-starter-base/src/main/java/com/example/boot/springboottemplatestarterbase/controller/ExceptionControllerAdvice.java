package com.example.boot.springboottemplatestarterbase.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.json.JSONUtil;
import com.example.boot.springboottemplatestarterbase.base.exception.ResourceNotFoundException;
import com.example.boot.springboottemplatestarterbase.base.exception.base.BaseException;
import com.example.boot.springboottemplatestarterbase.base.response.ResponseBodyBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import java.util.Objects;

/**
 * Description: ExceptionControllerAdvice
 * Change Log: Create by Johnny Miller (鍾俊), Change By Chang__
 *
 * @author Johnny Miller (鍾俊), email: johnnysviva@outlook.com
 * @date 2019-03-02 17:39
 **/
@Slf4j
@ControllerAdvice
public class ExceptionControllerAdvice {

    /**
     * 处理资源不存在所抛出的异常(例: 传入错误的参数, 导致查询返回的结果为null)
     *
     * @param request   HttpRequest
     * @param response  HttpResponse
     * @param exception ResourceNotFoundException
     * @return ResponseBodyBean
     */
    @ExceptionHandler(value = ResourceNotFoundException.class)
    @ResponseBody
    public ResponseBodyBean resourceNotFound(HttpServletRequest request,
                                             HttpServletResponse response,
                                             ResourceNotFoundException exception) {
        log.error("[GlobalExceptionCapture] ResourceNotFoundException: {}", exception);
        response.setStatus(HttpStatus.NOT_FOUND.value());
        return ResponseBodyBean.ofStatus(HttpStatus.NOT_FOUND, exception.getMessage());
    }

    /**
     * 处理HTTP METHOD不支持的异常
     *
     * @param request   HttpRequest
     * @param response  HttpResponse
     * @param exception HttpRequestMethodNotSupportedException
     * @return ResponseBodyBean
     */
    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    @ResponseBody
    public ResponseBodyBean methodNotAllowed(HttpServletRequest request,
                                             HttpServletResponse response,
                                             HttpRequestMethodNotSupportedException exception) {
        log.error("[GlobalExceptionCapture] HttpRequestMethodNotSupportedException: Current method is {}, Support HTTP method = {}",
                exception.getMethod(),
                JSONUtil.toJsonStr(exception.getSupportedHttpMethods()));
        response.setStatus(HttpStatus.METHOD_NOT_ALLOWED.value());
        return ResponseBodyBean.ofStatus(HttpStatus.METHOD_NOT_ALLOWED);
    }

    /**
     * 处理Spring Boot文件上传超出限制的异常
     *
     * @param request   HttpRequest
     * @param response  HttpResponse
     * @param exception MaxUploadSizeExceededException
     * @return ResponseBodyBean
     */
    @ExceptionHandler(value = MaxUploadSizeExceededException.class)
    @ResponseBody
    public ResponseBodyBean maxUploadSizeExceeded(HttpServletRequest request,
                                                  HttpServletResponse response,
                                                  MaxUploadSizeExceededException exception) {
        // TODO: 2019/9/1 后期改为读取配文件中的信息 

        log.error("[GlobalExceptionCapture] MaxUploadSizeExceededException: MaxUploadSize: {}", exception.getMaxUploadSize(), exception);
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return ResponseBodyBean.ofStatus(HttpStatus.INTERNAL_SERVER_ERROR, "上传的文件大小超出了限制 最大容量: " + exception.getMaxUploadSize());
    }

    /**
     * <p>Exception handler.</p>
     * <p><strong>ATTENTION</strong>: In this method, <strong><em>cannot throw any exception</em></strong>.</p>
     *
     * @param request   HTTP request
     * @param exception any kinds of exception occurred in controller
     * @return custom exception info
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseBodyBean globalHandle(HttpServletRequest request,
                                         HttpServletResponse response,
                                         Exception exception) {
        if (exception instanceof MethodArgumentNotValidException) {
            log.error("[GlobalExceptionCapture] MethodArgumentNotValidException: {}", exception.getMessage());
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            return ResponseBodyBean.ofStatus(HttpStatus.BAD_REQUEST,
                    getFieldErrorMessageFromException((MethodArgumentNotValidException) exception),
                    null);
        } else if (exception instanceof ConstraintViolationException) {
            log.error("[GlobalExceptionCapture] ConstraintViolationException: {}", exception.getMessage());
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            return ResponseBodyBean.ofStatus(HttpStatus.BAD_REQUEST,
                    CollUtil.getFirst(((ConstraintViolationException) exception)
                            .getConstraintViolations())
                            .getMessage(), null);
        } else if (exception instanceof BaseException) {
            log.error("[GlobalExceptionCapture] BaseException: Status code: {}, message: {}, data: {}",
                    ((BaseException) exception).getCode(),
                    exception.getMessage(),
                    ((BaseException) exception).getData());
            response.setStatus(((BaseException) exception).getCode());
            return ResponseBodyBean.ofStatus(((BaseException) exception).getCode(),
                    exception.getMessage(),
                    ((BaseException) exception).getData());
        }

        log.error("[GlobalExceptionCapture]: Exception information: {} ", exception.getMessage(), exception);
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return ResponseBodyBean.ofStatus(HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), null);
    }

    /**
     * Get field error message from exception. If two or more fields do not pass Spring Validation check, then will
     * return the 1st error message of the error field.
     *
     * @param exception MethodArgumentNotValidException
     * @return field error message
     */
    private String getFieldErrorMessageFromException(MethodArgumentNotValidException exception) {
        try {
            DefaultMessageSourceResolvable firstErrorField =
                    (DefaultMessageSourceResolvable) Objects.requireNonNull(exception.getBindingResult()
                            .getAllErrors()
                            .get(0)
                            .getArguments())[0];
            String firstErrorFieldName = firstErrorField.getDefaultMessage();
            String firstErrorFieldMessage = exception.getBindingResult().getAllErrors().get(0).getDefaultMessage();
            return firstErrorFieldName + " " + firstErrorFieldMessage;
        } catch (Exception e) {
            log.error("Exception occurred when get field error message from exception. Exception message: {}", e.getMessage(), e);
            return HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();
        }
    }
}
