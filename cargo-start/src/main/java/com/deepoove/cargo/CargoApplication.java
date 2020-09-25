package com.deepoove.cargo;

import com.google.common.eventbus.EventBus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CargoApplication {

  @Bean
  public EventBus configEvent() {
    EventBus event = new EventBus();
    return event;
  }

  public static void main(String[] args) {
    SpringApplication.run(CargoApplication.class, args);
  }

}
