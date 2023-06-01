package com.example.Motor_Verification_Algorithm;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class MotorVerificationController {

	private static final String template = "Im the MotorVerificationController";
	private int counter = 0;

	@GetMapping("/")
	public String greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		return template;
	}

	@GetMapping("/status")
	public String get_status() {
		return "ready";
	}

	@PostMapping("/motor/optional_equipment")
	public String executeAlgorithm(
			@RequestParam("configItem1") String configItem1,
			@RequestParam("configItem2") String configItem2,
			@RequestParam("configItem3") String configItem3,
			@RequestParam("configItem4") String configItem4) throws InterruptedException {

		counter += 1;
		if (counter % 3 == 0) {
			TimeUnit.SECONDS.sleep(2);
			return "Validation error.";
		} else {
			TimeUnit.SECONDS.sleep(2);
			return "Motor equipment validated.";
		}
	}

	public String invoke_next_algorithm() {
		/*
		 * Calls an external endpoint and returns its response.
		 */
		final String uri = "http://localhost:8080/greeting";

		RestTemplate restTemplate = new RestTemplate();
		String result = restTemplate.getForObject(uri, String.class);

		System.out.println(result);
		return result;
	}
}