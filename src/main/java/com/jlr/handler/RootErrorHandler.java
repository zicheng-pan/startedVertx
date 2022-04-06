package com.jlr.handler;

import com.jlr.webmodel.CommonReturnType;
import com.jlr.webmodel.EmBusinessError;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonObject;

import java.util.HashMap;
import java.util.Map;

public class RootErrorHandler implements ErrorHandler {

  @Override
  public void handle(HttpServerRequest request, HttpServerResponse response) {
    response.putHeader(HttpHeaders.CONTENT_TYPE, "application/json");
    Map<String, Object> map = new HashMap<>();

    map.put("errCode", EmBusinessError.UNKNOWN_ERROR.getErrCode());
    map.put("errMsg", EmBusinessError.UNKNOWN_ERROR.getErrMsg());

    JsonObject jsonObject = new JsonObject(map);
    response.end(jsonObject.toString());
  }
}
