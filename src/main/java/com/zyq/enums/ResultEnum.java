package com.zyq.enums;


/**
 * Created by yuzhou on 2017/5/22.
 */
public enum  ResultEnum {

    UNKNOWN_ERROR(-1, "未知错误"),
    DATA_ERROR(1, "数据验证错误"),
    BAD_CREDENTIALS(2, "用户名或密码错误"),
    FAIL_TO_SAVE_FILE(3, "文件保存失败"),
    FAIL_TO_DELETE(4, "删除失败"),
    METHOD_ERROR_POST(5, "请按照API中描述的  'GET' 方法提交"),
    METHOD_ERROR_GET(5, "请按照API中描述的 'POST' 方法提交"),
    COMMUNICATION_ERROR(10, "后台通讯异常"),
    SUCCESS(0, "成功");

    private Integer code;
    private String msg;


    ResultEnum(Integer code, String key) {
      this.code = code;
      this.msg = key;
    }

    public String getMsg() {
      return msg;
    }

    public Integer getCode() {
      return code;
    }
  }
