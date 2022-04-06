package com.example.vertx.starter;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.json.JsonObject;
import io.vertx.mysqlclient.MySQLConnectOptions;
import io.vertx.mysqlclient.MySQLPool;
import io.vertx.sqlclient.PoolOptions;
import io.vertx.sqlclient.Row;
import io.vertx.sqlclient.RowSet;

import java.util.ArrayList;
import java.util.List;

public class ConnectToMySql extends AbstractVerticle {

  MySQLConnectOptions connectOptions = new MySQLConnectOptions()
    .setPort(3306)
    .setHost("the-host")
    .setDatabase("the-db")
    .setUser("user")
    .setPassword("secret");

  // Pool options
  PoolOptions poolOptions = new PoolOptions()
    .setMaxSize(5);

  // Create the client pool
  MySQLPool client;

  @Override
  public void start(Promise<Void> startPromise) throws Exception {

    client = MySQLPool.pool(vertx, connectOptions, poolOptions);
    // A simple query
    client
      .query("SELECT * FROM users WHERE id='julien'")
      .execute(ar -> {
        if (ar.succeeded()) {
          RowSet<Row> result = ar.result();
          List<JsonObject> list = new ArrayList();
          ar.result().forEach(item -> {
            JsonObject jsonObject = new JsonObject();
            jsonObject.put("id", item.getValue("id").toString());
          });
          System.out.println("Got " + result.size() + " rows ");
        } else {
          System.out.println("Failure: " + ar.cause().getMessage());
        }

        // Now close the pool
        client.close();
      });
  }
}
