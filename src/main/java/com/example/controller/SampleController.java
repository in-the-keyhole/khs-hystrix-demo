package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Payload;
import com.example.service.AggregationService;

@RestController
@RequestMapping(value="/payloads")
public class SampleController {
	
	@Autowired
	private AggregationService service;
	
	@RequestMapping(method = RequestMethod.GET ,produces = "application/json")
	public ResponseEntity<List<Payload>> getAll(){
		List<Payload> pay = service.returnAll();
		return (pay.isEmpty()? new ResponseEntity<List<Payload>>(HttpStatus.INTERNAL_SERVER_ERROR) : new ResponseEntity<List<Payload>>(pay, HttpStatus.OK) );
	}
	
	@RequestMapping(value="/hystrix", method = RequestMethod.GET ,produces = "application/json")
	public ResponseEntity<List<Payload>> getAllHystrix(){
		List<Payload> pay = service.returnAllHystrix();
		return (pay.isEmpty()? new ResponseEntity<List<Payload>>(HttpStatus.SERVICE_UNAVAILABLE) : new ResponseEntity<List<Payload>>(pay, HttpStatus.OK) );
	}
	
	@RequestMapping(value="/fail", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<Payload>> getFails(){
		List<Payload> pay =  service.failAll();
		return (pay.isEmpty()? new ResponseEntity<List<Payload>>(HttpStatus.INTERNAL_SERVER_ERROR) : new ResponseEntity<List<Payload>>(pay, HttpStatus.OK) );
	}
	
	@RequestMapping(value="/fail/hystrix", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<Payload>> getFailsHystrix(){
		List<Payload> pay = service.failAllHystrix();
		return (pay.isEmpty()? new ResponseEntity<List<Payload>>(HttpStatus.SERVICE_UNAVAILABLE) : new ResponseEntity<List<Payload>>(pay, HttpStatus.OK) );
	}
	
	

}
