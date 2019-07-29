package com.example.boot.springboottemplatestarter.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.sql.Timestamp;

/**
 * description: 响应载体
 *
 * @author Chang
 * @date 2019/7/29 23:31
 */
@Getter
@Builder
public class Result<T> {

    private Integer status;
    private String message;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp timestamp;
    private T data;

    /**
     *
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Result<T> ofSuccess(T data) {
        return Result.<T>builder().status(HttpStatus.OK.value())
                .message(HttpStatus.OK.getReasonPhrase())
                .timestamp(new Timestamp(System.currentTimeMillis()))
                .data(data)
                .build();
    }

    /**
     *
     *
     * @param message
     * @param <T>
     * @return
     */
    public static <T> Result<T> ofSuccess(String message) {
        return Result.<T>builder().status(HttpStatus.OK.value())
                .message(message)
                .timestamp(new Timestamp(System.currentTimeMillis()))
                .build();
    }

    /**
     *
     *
     * @param data
     * @param message
     * @param <T>
     * @return
     */
    public static <T> Result<T> ofSuccess(T data, String message) {
        return Result.<T>builder().status(HttpStatus.OK.value())
                .message(message)
                .timestamp(new Timestamp(System.currentTimeMillis()))
                .data(data)
                .build();
    }
//
//    public static <T> Result<T> ofError() {
//
//    }
}
