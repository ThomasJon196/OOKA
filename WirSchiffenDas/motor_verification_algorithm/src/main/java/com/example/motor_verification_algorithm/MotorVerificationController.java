package com.example.motor_verification_algorithm;

import java.util.HashMap;
import java.util.Map;

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


	public void update_status(Status status) {

		final String url = "http://localhost:8080/ui_status";
        RestTemplate restTemplate = new RestTemplate();

        //restTemplate.postForObject(url, "test", String.class);

        Map<String, Object> params = new HashMap<>(2);
        params.put("id", 1);
        params.put("status", status.toString());
        String result = restTemplate.getForObject(url, String.class, params);

        System.out.println(result);
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
			update_status(Status.FAILED);
			return "Validation error.";
		} else {
			TimeUnit.SECONDS.sleep(2);
			update_status(Status.SUCCESS);
			return "Motor equipment validated.";
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