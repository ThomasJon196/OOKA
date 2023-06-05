package com.example.analyzercomponent;

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
		return "Im The Analyzer";
	}

	@PostMapping("/configuration/test")
	public String executeAlgorithm(@RequestParam("configItem1") String configItem1,
			@RequestParam("configItem2") String configItem2,
			@RequestParam("configItem3") String configItem3, @RequestParam("configItem4") String configItem4) {

		final String url = "http://localhost:8081/fuel/optional_equipment";
		RestTemplate restTemplate = new RestTemplate();

		Map<String, Object> params = new HashMap<>(4);
		params.put("configItem1", configItem1);
		params.put("configItem2", configItem2);
		params.put("configItem3", configItem3);
		params.put("configItem4", configItem4);
		String result = restTemplate.getForObject(url, String.class, params);

		System.out.println(result);

		return result;
	}

	@GetMapping("/get_status")
	public Mono<String> get_event() {
		
		Mono<String> result = component_access.read_component_status();
		// String res = result.

		return result;
	}

}