package com.example.vertx.starter;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;

public class RouterVerticle extends AbstractVerticle {

  private Router router;

  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    router = Router.router(vertx);

    router.route("/").handler(req -> {
        req.response()
          .putHeader("content-type", "text/plain")
          .end("Hello from Vert.x router!");
      }
    );

    router.get("/get").handler(req -> {
      req.response().end("get from Vert.x router");
    });

    // http://localhost:8888/test?page=1&age=10获取参数
//    router.route("/test").handler(req -> {
//      String page = req.request().getParam("page");
//      req.response().putHeader("Content-type", "text/plain").end(page);
//    });

    //For Rest mode http://localhost:8888/test/1/10
//    router.route("/test/:page").handler(req -> {
//      String page = req.request().getParam("page");
//      req.response().putHeader("Content-type", "text/plain").end(page);
//    });

    // get data from body
    router.route().handler(BodyHandler.create());
    // form-data
    // request header Content-type:application/x-www-form-urlencoded
    router.route("/test/form").handler(req -> {
        // need set attribute in the form
        String page = req.request().getFormAttribute("page");
        req.response().putHeader("Content-type", "text/plain").end(page);
      }
    );

    //get data from json
    // request header Content-type:
    router.route("/test/json").handler(req -> {
      JsonObject page = req.getBodyAsJson();
      req.response().putHeader("Content-type", "text/plain").end(page.getString("page").toString());
    });

    vertx.createHttpServer().requestHandler(router).listen(8888, http -> {
      if (http.succeeded()) {
        startPromise.complete();
        System.out.println("HTTP server started on port 8888");
      } else {
        startPromise.fail(http.cause());
      }
    });
  }
}
