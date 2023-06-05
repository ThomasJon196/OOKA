package com.example.fuel_verification_algorithm;

import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class FuelVerificationController {

	private static final String template = "Im the FuelVerificator";
	private static String ID = "1";
	private int counter = 0;

	@GetMapping("/")
	public String greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		return template;
	}

	@GetMapping("/status")
	public String get_status() {
		return "ready";
	}

	public void update_status(Status status) {

		final String url = "http://localhost:8080/ui_status";
		RestTemplate restTemplate = new RestTemplate();
		// restTemplate.postForObject(url, "test", String.class);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
		map.add("id", ID);
		map.add("result", status.toString());

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
		ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
	}

	@PostMapping("/fuel/optional_equipment")
	public String executeAlgorithm(
			@RequestParam("configItem1") String configItem1,
			@RequestParam("configItem2") String configItem2,
			@RequestParam("configItem3") String configItem3,
			@RequestParam("configItem4") String configItem4) throws InterruptedException {

		update_status(Status.RUNNING);

		counter += 1;
		if (counter % 3 == 0) {
			TimeUnit.SECONDS.sleep(2);
			update_status(Status.FAILED);
			return "Validation error.";
		} else {
			TimeUnit.SECONDS.sleep(2);
			update_status(Status.SUCCESS);
			return "Fuel equipment validated.";
		}
	}

	public String invoke_next_algorithm() {
		/*
		 * Calls an external endpoint and returns its response.
		 */
		final String uri = "http://localhost:8083/greeting";

		RestTemplate restTemplate = new RestTemplate();
		String result = restTemplate.getForObject(uri, String.class);

		System.out.println(result);
		return result;
	}
}