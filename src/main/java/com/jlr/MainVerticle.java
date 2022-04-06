package com.jlr;

import com.jlr.handler.ErrorHandler;
import com.jlr.handler.FilterHandler;
import com.jlr.handler.RootErrorHandler;
import com.jlr.util.Mongo;
import io.vertx.core.*;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.MongoClient;
import io.vertx.ext.web.Router;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainVerticle extends AbstractVerticle {

  @Override
  public void start(Promise<Void> startPromise) throws Exception {

    HttpServer httpServer = setupHttpServer();

    Router router = Router.router(vertx);

    router.routeWithRegex("/.*").handler(FilterHandler.create(new WebFilterStack()));
    registerRoutes(router);
    // global exception handler
    router.route().failureHandler(ErrorHandler.create(new RootErrorHandler()));


    httpServer.requestHandler(router).listen(9999, http -> {
      if (http.succeeded()) {
        startPromise.complete();
        System.out.println("HTTP server started on port 8888");
      } else {
        startPromise.fail(http.cause());
      }
    });
    System.out.println("successfully");
  }

  private void registerRoutes(Router router) {
    // register router
    register(router);
  }

  private void register(Router router) {
//    router.route(HttpMethod.GET,"/hello").handler(ServiceHandler)
    router.get("/get").handler(req -> {
      // for test
//      int a =1/0;

      req.response().end("hello");
    });

    router.get("/error").handler(req -> {
      // for test
      int a = 1 / 0;
      req.response().end("hello");
    });
    router.get("/hello").handler(req -> {

      req.response().end("hello");
    });

    router.get("/userprofile").handler(req -> {
      try {
        Future<List<JsonObject>> one = Mongo.USER_PROFILE.findOne(new JsonObject().put("userId","3f71ab7f-0825-3e26-8578-79f41606d176"));
        one.onSuccess(result -> {
          StringBuffer user = new StringBuffer();
          for (JsonObject obj : result) {
            user.append(obj.toString());
          }
        req.response().end(user.toString());
        });
      } catch (Exception e) {
        e.printStackTrace();
      }
    });

  }


  private HttpServer setupHttpServer() {

    //TODO set SSL cert in httpOpts
    HttpServerOptions httpOpts = new HttpServerOptions();
    httpOpts.setSsl(false);

    return vertx.createHttpServer(httpOpts);
  }


  //1. why use fiber even though using the event loop
  //2. why using async wait even though using the event loop.
}
