package com.justin;

import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;

public class CounterService extends ScheduledService<Object> {
  private Counter obj;
  public final void setObject(Counter obj) {
	this.obj = obj;
  }
  @Override
  protected Task<Object> createTask() {
	return new Task<Object>() {
	  protected Integer call() {
		obj.setCount(obj.getCount()+1);
		return obj.getCount();
	  }
	};
  }
}
