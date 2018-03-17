package com.excercise.alert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AlertController {

	@Autowired
	private AlertService alertService;

	@RequestMapping(value = "/alert/date/{date}", method = RequestMethod.GET)
	@ResponseBody
	public AlertResponse getAlert(@PathVariable String date) {
		return alertService.getAlert(date);
	}

}