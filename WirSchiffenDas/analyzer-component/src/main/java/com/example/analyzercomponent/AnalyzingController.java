package com.example.analyzercomponent;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class AnalyzingController {

	private static final String template = "Hello, %s!";

	@GetMapping("/greeting")
	public String greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		return "Im The Analyzer";
	}

	@PostMapping("/configuration/test")
	public String executeAlgorithm(@RequestParam("configItem1") String configItem1,
			@RequestParam("configItem2") String configItem2,
			@RequestParam("configItem3") String configItem3, @RequestParam("configItem4") String configItem4) {
		return "Started algorithm...";
	}

	@GetMapping("/get_status")
	public String get_event() {
		final String uri = "http://localhost:8082/status";

		RestTemplate restTemplate = new RestTemplate();
		String result = restTemplate.getForObject(uri, String.class);

		System.out.println(result);
		return result;
	}

}