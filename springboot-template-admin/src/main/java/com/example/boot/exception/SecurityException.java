package com.example.boot.exception;

import com.example.boot.exception.base.BaseException;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

/**
 * Description: SecurityException, change description here.
 *
 * @author Johnny Miller (鍾俊), email: johnnysviva@outlook.com
 * @changeby Chang__
 * @date 2019-03-23 16:26
 **/
@SuppressWarnings("unused")
@Data
@EqualsAndHashCode(callSuper = true)
public class SecurityException extends BaseException {
    private static final long serialVersionUID = -767157443094687237L;

    public SecurityException(HttpStatus httpStatus) {
        super(httpStatus);
    }

    public SecurityException(HttpStatus httpStatus, Object data) {
        super(httpStatus, data);
    }
}
