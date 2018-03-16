package com.excercise.alert;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AlertController {

	@Autowired
	private AlertService alertService;

	@RequestMapping(value = "/data", method = RequestMethod.GET)
	public @ResponseBody List<DataModel> getData(@RequestParam(value = "apiKey") String apiKey) {
		return alertService.getData(apiKey);
	}

	@RequestMapping(value = "/alert", method = RequestMethod.GET)
	public @ResponseBody AlertResponse getAlert() {
		return alertService.getAlert();
	}

}