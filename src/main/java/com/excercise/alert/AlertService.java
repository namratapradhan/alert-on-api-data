package com.excercise.alert;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class AlertService {

	@Autowired
	private Environment env;

	@Autowired
	private RestTemplate restTemplate;

	public List<DataModel> getData(String apiKey) {
		UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(env.getProperty("alert.data.url"))
				.queryParam("api-key", apiKey);
		ResponseEntity<List<DataModel>> response = restTemplate.exchange(uriBuilder.build().toUri(), HttpMethod.GET,
				null, new ParameterizedTypeReference<List<DataModel>>() {
				});
		return response.getBody();
	}

	public AlertResponse getAlert(Long epochStartDate) {
		try {
			// add 24 hours
			Long epochEndDate = epochStartDate + 86400000;
			Integer threshold = Integer.parseInt(env.getProperty("alert.threshold"));
			// Retrieve data
			List<DataModel> dataList = getData(env.getProperty("alert.api.key"));
			// filter date to 24 hour
			dataList = dataList.stream().filter(d -> (d.getDate() >= epochStartDate && d.getDate() <= epochEndDate))
					.collect(Collectors.toList());
			List<Alert> alerts = analysis(threshold, dataList);
			return buildResponse(Conversion.fromEpochToString(epochStartDate),
					Conversion.fromEpochToString(epochEndDate), alerts);
		} catch (Exception ex) {
			throw ex;
		}
	}

	private List<Alert> analysis(Integer threshold, List<DataModel> dataList) {
		List<Alert> alerts = new ArrayList<>();
		Map<String, Alert> alertsMap = new HashMap<>();
		for (DataModel data : dataList) {
			Alert alert = new Alert();
			Alert alertInMap = alertsMap.get(data.getName());
			// if key does not exists, add alert to map with its name as key and set sum
			if (alertInMap == null) {
				alert.setSum(data.getValue());
				alertsMap.put(data.getName(), alert);
			} else if (!alertInMap.getThresholdReached() && alertInMap.getSum() < 2000) {
				alert.setSum(data.getValue() + alertInMap.getSum());
				if (alert.getSum() >= threshold) {
					alert.setThresholdReached(true);
					alert.setName(data.getName());
					// We need this for sorting
					alert.setLocalDateTime(Conversion.fromEpochToLocalDateTime(data.getDate()));
					alert.setTimestamp(Conversion.fromEpochToString(data.getDate()));
					alerts.add(alert);
				}
				alertsMap.put(data.getName(), alert);
			}
		}
		return alerts;
	}

	private AlertResponse buildResponse(String startDate, String endDate, List<Alert> alerts) {
		AlertResponse alertResponse = new AlertResponse();
		alertResponse.setStart(startDate);
		alertResponse.setEnd(endDate);
		// Sort chronologically
		alerts.sort(Comparator.comparing(Alert::getLocalDateTime));
		alertResponse.setAlerts(alerts);
		return alertResponse;
	}

}
