package com.jlr;

import com.jlr.handler.FilterHandler;
import com.jlr.util.Mongo;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UserContextFilter implements FilterHandler {
  @Override
  public void doFilter(RoutingContext context) {
    //
    String cookie = context.request().getParam("token");
    System.out.println("get request token:" + cookie);
  }
}
