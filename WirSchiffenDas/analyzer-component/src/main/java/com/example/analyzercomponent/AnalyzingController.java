package com.example.analyzercomponent;


import java.io.*;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import reactor.core.publisher.Mono;
import java.time.*;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class AnalyzingController {

	private static final String template = "Hello, %s!";

	@Autowired
	private ComponentAccess component_access;

	@GetMapping("/greeting")
	public String greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		System.out.println("Invoked");
		return "Im The Analyzer";
	}

	@PostMapping("/configuration/test")
	public void executeAlgorithm(@RequestParam("configItem1") String configItem1,
			@RequestParam("configItem2") String configItem2,
			@RequestParam("configItem3") String configItem3, @RequestParam("configItem4") String configItem4) {

		final String url = "http://localhost:8081/fuel/optional_equipment";
		RestTemplate restTemplate = new RestTemplate();
		// restTemplate.postForObject(url, "test", String.class);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		MultiValueMap<String, String> configuration = new LinkedMultiValueMap<>();
		configuration.add("configItem1", configItem1);
		configuration.add("configItem2", configItem2);
		configuration.add("configItem3", configItem3);
		configuration.add("configItem4", configItem4);

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(configuration, headers);
		try {
			ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
		} catch (Exception e) {
			System.out.println("Invokation failed. Service unreachable.");
		}
	}

	@GetMapping("/get_status")
	public Mono<String> get_event() {

		Mono<String> result = component_access.read_component_status();
		// String res = result.

		return result;
	}

}