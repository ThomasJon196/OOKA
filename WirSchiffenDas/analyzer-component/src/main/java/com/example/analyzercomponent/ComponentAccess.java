package com.example.analyzercomponent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreakerFactory;
import org.springframework.stereotype.Service;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;



@Service
public class ComponentAccess {

  private static final Logger LOG = LoggerFactory.getLogger(ComponentAccess.class);

  private final ReactiveCircuitBreaker circuitBreaker;

  public ComponentAccess(ReactiveCircuitBreakerFactory circuitBreakerFactory) {
    this.circuitBreaker = circuitBreakerFactory.create("component_breaker");
  }

  public Mono<String> read_component_status() {
    WebClient webClient = WebClient.builder().baseUrl("http://localhost:8082").build();

    return circuitBreaker.run(webClient.get().uri("/status").retrieve().bodyToMono(String.class), throwable -> {
      LOG.warn("Error making request to book service", throwable);
      return Mono.just("Service unreachable...");
    });
  }
}