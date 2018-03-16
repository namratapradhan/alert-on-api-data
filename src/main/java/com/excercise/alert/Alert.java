package com.excercise.alert;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Alert {

	private LocalDateTime timestamp;
	private String name;
	private Integer sum;

	@JsonIgnore
	private Boolean thresholdReached = false;

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSum() {
		return sum;
	}

	public void setSum(Integer sum) {
		this.sum = sum;
	}

	public Boolean getThresholdReached() {
		return thresholdReached;
	}

	public void setThresholdReached(Boolean thresholdReached) {
		this.thresholdReached = thresholdReached;
	}
}
