package com.deepoove.cargo.infrastructure.event;

import com.deepoove.cargo.shared.DomainEventPublisher;
import com.google.common.eventbus.EventBus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GuavaDomainEventPublisher implements DomainEventPublisher {

  @Autowired
  private EventBus eventBus;

  public void publish(Object event) {
    eventBus.post(event);
  }

}