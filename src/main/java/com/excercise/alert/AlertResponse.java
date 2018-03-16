package com.excercise.alert;

import java.time.LocalDateTime;
import java.util.Collection;

public class AlertResponse {

	private LocalDateTime start;
	private LocalDateTime end;
	private Collection<Alert> alerts;

	public Collection<Alert> getAlerts() {
		return alerts;
	}

	public LocalDateTime getEnd() {
		return end;
	}

	public LocalDateTime getStart() {
		return start;
	}

	public void setAlerts(Collection<Alert> alerts) {
		this.alerts = alerts;
	}

	public void setEnd(LocalDateTime end) {
		this.end = end;
	}

	public void setStart(LocalDateTime start) {
		this.start = start;
	}

}
