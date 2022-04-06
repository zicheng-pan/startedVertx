package com.jlr.handler;

import com.jlr.fiber.FiberUtils;
import com.jlr.util.Mongo;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;

public interface FilterHandler {

  static Handler<RoutingContext> create(FilterHandler handler) {
    return routingContext -> {
//      FiberUtils.fiberExecute(() -> {
        handler.doFilter(routingContext);
        routingContext.next();
//      });
    };
  }

  void doFilter(RoutingContext context);
}
