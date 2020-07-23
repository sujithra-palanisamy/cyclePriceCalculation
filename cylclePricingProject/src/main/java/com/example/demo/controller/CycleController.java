package com.example.demo.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.execution.PriceExecutionQueue;
import com.example.demo.execution.PriceTest;
import com.example.demo.service.CyclePriceRequest;

@RestController
public class CycleController {

	@Autowired
	private PriceExecutionQueue priceExecutionQueue;
	
	@Autowired
	private PriceTest priceTest;
	
	@RequestMapping(value="/calculateBycycle", method=RequestMethod.POST)
	public void getBycyclePrice(@RequestBody List<CyclePriceRequest> cyclePriceRequestList){
		this.priceExecutionQueue.execute(cyclePriceRequestList);
	}
	
	
	@RequestMapping(value="v1/calculateBycycle", method=RequestMethod.POST)
	public void getBycyclePriceV1(@RequestBody List<CyclePriceRequest> cyclePriceRequestList){
		this.priceTest.execute(cyclePriceRequestList);
	}
	
}
