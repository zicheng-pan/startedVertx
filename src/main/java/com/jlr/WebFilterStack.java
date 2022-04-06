package com.jlr;

import com.jlr.handler.FilterHandler;
import io.vertx.ext.web.RoutingContext;

import java.util.ArrayList;
import java.util.List;

public class WebFilterStack implements FilterHandler {

  static List<FilterHandler> filterHandlers = new ArrayList<>();
  static {
    filterHandlers.add(new UserContextFilter());
  }

  @Override
  public void doFilter(RoutingContext context) {
    for (FilterHandler handler:filterHandlers){
      handler.doFilter(context);
    }
  }
}
