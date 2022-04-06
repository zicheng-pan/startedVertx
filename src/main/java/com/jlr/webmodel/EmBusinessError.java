package com.jlr.webmodel;

public enum EmBusinessError implements CommonError {


  PARAMETER_VALIDATION_ERROR(10001, "parameter not valid!"),
  UNKNOWN_ERROR(10002, "未知错误");


  private int errorCode;
  private String errorMsg;

  EmBusinessError(int errorcode, String errorMsg) {
    this.errorCode = errorcode;
    this.errorMsg = errorMsg;
  }

  @Override
  public int getErrCode() {
    return this.errorCode;
  }

  @Override
  public String getErrMsg() {
    return this.errorMsg;
  }

  @Override
  public CommonError setErrMsg(String errMsg) {
    this.errorMsg = errMsg;
    return this;
  }
}
