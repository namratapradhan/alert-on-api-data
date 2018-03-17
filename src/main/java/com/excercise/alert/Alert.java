package com.excercise.alert;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Alert {

	private String timestamp;
	private String name;
	private Integer sum;
	@JsonIgnore
	private LocalDateTime localDateTime;
	@JsonIgnore
	private Boolean thresholdReached = false;

	public LocalDateTime getLocalDateTime() {
		return localDateTime;
	}

	public void setLocalDateTime(LocalDateTime localDateTime) {
		this.localDateTime = localDateTime;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
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
