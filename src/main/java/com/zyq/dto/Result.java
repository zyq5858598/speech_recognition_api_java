package com.zyq.dto;

import com.zyq.enums.ResultEnum;
import com.zyq.exception.LogicException;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * API 返回结果封装
 * Created by yuzhou on 2017/5/16.
 */
@Data
@Accessors(chain = true)
public class Result<T> {
  private Integer code;

  private String msg;

  private T data;

  public static <T> Result<T> success(T data) {

    return new Result().setCode(0).setData(data).setMsg("成功");
  }

  public static <T> Result<T> fail(String msg) {

    return fail(ResultEnum.DATA_ERROR.getCode(), msg);
  }

  public static <T> Result<T> fail(Integer code, String msg) {

    return new Result().setCode(code).setMsg(msg);
  }

  public static <T> Result<T> fail(ResultEnum resultEnum) {

    return fail(resultEnum.getCode(), resultEnum.getMsg());
  }

  public static <T> Result<T> fail(ResultEnum resultEnum, String msg) {

    return fail(resultEnum.getCode(), msg);
  }

  public static <T> Result<T> fail(LogicException e) {

    return fail(e.getCode(), e.getMessage());
  }
}
