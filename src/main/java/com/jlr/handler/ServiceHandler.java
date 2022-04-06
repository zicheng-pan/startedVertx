package com.jlr.handler;

import co.paralleluniverse.fibers.Suspendable;
import com.jlr.fiber.FiberUtils;
import com.jlr.webmodel.CommonReturnType;
import com.sun.xml.internal.ws.client.RequestContext;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;

public interface ServiceHandler {
  static Handler<RoutingContext> create(ServiceHandler handler) {
    return routingContext ->
      FiberUtils.fiberExecute(() -> {
        CommonReturnType data = handler.doService(routingContext.request());



//        routingContext.response().write();
      });

  }

  @Suspendable
  CommonReturnType doService(HttpServerRequest requestContext);
}
