package com.jlr.dao;

import io.vertx.core.Context;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.MongoClient;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;


public class MongoDao {

  private Context context = Vertx.currentContext();
  private MongoClient mongoClient = MongoClient.createShared(context.owner(), new JsonObject()
    .put("connection_string", "mongodb://127.0.0.1:27017/cloudcar"));

  private String collection;

  public MongoDao(String collection) {
    this.collection = collection;
  }

  public Future<List<JsonObject>> findOne(JsonObject query) throws ExecutionException, InterruptedException {
    Promise<List<JsonObject>> promise = Promise.promise();
    mongoClient.find(collection, query, res -> {
      if (res.succeeded()) {
        promise.complete(res.result());
      } else {
        promise.fail(res.cause());
      }
    });
    return promise.future();
  }
}
