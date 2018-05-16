package com.zyq.exception;

import com.zyq.enums.ResultEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by user on 2018-5-16.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class LogicException extends RuntimeException {

    private Integer code;
    private String message;

    public LogicException(Integer code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public LogicException(String message) {
        this(-1, message);
    }

    public LogicException(ResultEnum resultEnum) {
        this(resultEnum.getCode(), resultEnum.getMsg());
    }

    public LogicException(ResultEnum resultEnum, String msg) {
        this(resultEnum.getCode(), resultEnum.getMsg() + ":" + msg);
    }
}
