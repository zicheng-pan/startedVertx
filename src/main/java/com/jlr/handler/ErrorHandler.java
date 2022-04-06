package com.jlr.handler;

import com.jlr.fiber.FiberUtils;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.RoutingContext;

public interface ErrorHandler {


  // JLR 耦合度太大了
  static Handler<RoutingContext> create(ErrorHandler handler) {
    return routingContext -> {
      FiberUtils.fiberExecute(() -> {
        HttpServerRequest request = routingContext.request();
        HttpServerResponse response = routingContext.response();
        handler.handle(request, response);
      });
    };
  }

  void handle(HttpServerRequest request, HttpServerResponse response);
}
