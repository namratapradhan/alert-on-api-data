package com.excercise.alert;

public class DataModel {

	private Long date;
	private String name;
	private Integer value;

	public Long getDate() {
		return date;
	}

	public void setDate(Long date) {
		this.date = date;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "DataModel [date=" + date + ", name=" + name + ", value=" + value + "]";
	}

}
