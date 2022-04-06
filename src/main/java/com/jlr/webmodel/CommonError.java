package com.jlr.webmodel;

public interface CommonError {
  public int getErrCode();

  public String getErrMsg();

  public CommonError setErrMsg(String errMsg);
}
