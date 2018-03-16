package com.excercise.alert;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class AlertService {

	private static final Logger log = LoggerFactory.getLogger(AlertService.class);

	@Autowired
	private Environment env;

	@Autowired
	private RestTemplate restTemplate;

	public List<DataModel> getData(String apiKey) {
		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(env.getProperty("alert.data.url"))
				.queryParam("api-key", apiKey);
		ResponseEntity<List<DataModel>> response = restTemplate.exchange(uriBuilder.build().toUri(), HttpMethod.GET,
				null, new ParameterizedTypeReference<List<DataModel>>() {
				});
		return response.getBody();
	}

	public AlertResponse getAlert() {
		AlertResponse alertResponse = new AlertResponse();
		List<DataModel> data = getData("nu11p0int3r!");
		Collection<Alert> alerts = new ArrayList<>();
		Map<String, Alert> alertsMap = new HashMap<>();
		// data.stream();
		// .filter(d -> d.getDate().isEqual(LocalDateTime.now()) ||
		// d.getDate().isAfter(LocalDateTime.now()))
		// .filter(d -> d.getDate().isEqual(LocalDateTime.now()) ||
		// d.getDate().isAfter(LocalDateTime.now()))
		for (DataModel d : data) {
			Alert alertInMap = alertsMap.get(d.getName());
			Alert alert = new Alert();

			int sum = 0;
			// if key does not exists
			if (alertInMap == null) {
				alert.setSum(d.getValue());
				alertsMap.put(d.getName(), alert);
			} else if (!alertInMap.getThresholdReached() && alertInMap.getSum() < 2000) {
				sum = d.getValue() + alertInMap.getSum();
				alert.setSum(sum);
				if (sum >= 2000) {
					alert.setThresholdReached(true);
					alert.setName(d.getName());
					LocalDateTime date = LocalDateTime.ofInstant(Instant.ofEpochMilli(d.getDate()),
							ZoneId.systemDefault());
					alert.setTimestamp(date);
					alerts.add(alert);
				}
				alertsMap.put(d.getName(), alert);
			}
		}

		alertResponse.setStart(LocalDateTime.now());
		alertResponse.setEnd(LocalDateTime.now().plusHours(24));
		alertResponse.setAlerts(alerts);
		// .collect(Collectors.groupingBy(DataModel::getName,
		// Collectors.reducing(0, DataModel::getValue, Integer::sum)));
		return alertResponse;
	}

	// {
	// "start" : "2016/10/20 12:20:33",
	// "end" : "2016/10/30 02:23:13",
	// "alerts": [
	// {
	// "timestamp": "2016/10/20 14:20:31",
	// "name": "Test Lab 3",
	// "sum": 2423
	// },
	// {
	// "timestamp": "2016/10/22 11:43:34",
	// "name": "Test Lab 1",
	// "sum": 2587
	// },
	// {
	// "timestamp": "2016/10/29 09:14:32",
	// "name": "Test Lab 5",
	// "sum": 3233
	// }
	// ]
	// }
}
