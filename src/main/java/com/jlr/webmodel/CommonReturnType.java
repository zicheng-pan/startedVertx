package com.jlr.webmodel;

import io.vertx.core.json.JsonObject;

import java.util.HashMap;
import java.util.Map;

public class CommonReturnType {

  private String status;
  private Object data;

  public static CommonReturnType create(Object result) {
    return CommonReturnType.create(result, "success");
  }

  public static CommonReturnType create(Object result, String status) {
    CommonReturnType type = new CommonReturnType();
    type.setData(result);
    type.setStatus(status);
    return type;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public Object getData() {
    return data;
  }

  public void setData(Object data) {
    this.data = data;
  }

  public String convertToJson() {
    Map<String, Object> map = new HashMap<>();
    map.put("data", this.data);
    map.put("status", this.status);
    JsonObject jsonObject = new JsonObject(map);
    return jsonObject.toString();
  }

  public static void main(String[] args) {
    CommonReturnType type = CommonReturnType.create("aaaa");
    System.out.println(type.convertToJson());

  }
}
