package com.jlr.fiber;

import co.paralleluniverse.fibers.DefaultFiberScheduler;
import co.paralleluniverse.fibers.Fiber;
import co.paralleluniverse.fibers.FiberScheduler;
import co.paralleluniverse.strands.SuspendableRunnable;

public class FiberUtils {
  public static void fiberExecute(SuspendableRunnable runnable) {
    FiberScheduler scheduler = DefaultFiberScheduler.getInstance();
    new Fiber<>(scheduler, runnable).start();
  }
}
