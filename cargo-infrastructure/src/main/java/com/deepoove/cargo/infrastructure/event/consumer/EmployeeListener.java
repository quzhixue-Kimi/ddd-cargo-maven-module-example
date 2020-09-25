package com.deepoove.cargo.infrastructure.event.consumer;

import javax.annotation.PostConstruct;

import com.deepoove.Cache;
import com.deepoove.cargo.domain.aggregate.employee.EmployeeDeleteDomainEvent;
import com.deepoove.cargo.domain.aggregate.employee.EmployeeSaveDomainEvent;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmployeeListener {

  @Autowired
  private EventBus eventBus;

  @PostConstruct
  public void init() {
    eventBus.register(this);
  }

  @Subscribe
  public void recordEmployeeSave(EmployeeSaveDomainEvent event) {
    System.out.println("employee save domain event......");
    System.out.println(event.getEmployee());
    Cache.redis.put("new", event.getEmployee());
  }

  @Subscribe
  public void recordEmployeeDelete(EmployeeDeleteDomainEvent event) {
    System.out.println("employee delete domain event......");
    System.out.println(event.getId());
  }
}
