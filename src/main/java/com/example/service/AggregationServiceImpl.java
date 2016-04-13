package com.example.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.command.SampleCommand;
import com.example.model.Payload;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class AggregationServiceImpl implements AggregationService {

	@Autowired
	private SampleService service;

	@Autowired
	private SampleCommand command;

	final static Logger logger = LoggerFactory.getLogger(AggregationServiceImpl.class);

	public List<Payload> returnAll() {
		List<Payload> payloads = new ArrayList<Payload>();
		try {
			Payload pay = service.getOne();
			payloads = service.getAll();
			payloads.add(pay);
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return payloads;
	}

	@HystrixCommand
	public List<Payload> returnAllHystrix() {
		List<Payload> payloads = new ArrayList<Payload>();
		try {
			Payload pay = command.getOne();
			payloads = command.getAll();
			payloads.add(pay);
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return payloads;
	}

	public List<Payload> failAll() {
		List<Payload> payloads = new ArrayList<Payload>();
		try {
			Payload pay = service.fail();
			payloads = service.failAll();
			payloads.add(pay);
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return payloads;

	}

	@HystrixCommand
	public List<Payload> failAllHystrix() {
		List<Payload> payloads = new ArrayList<Payload>();
		try {
			payloads = command.demoFail();
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return payloads;
	}

}
