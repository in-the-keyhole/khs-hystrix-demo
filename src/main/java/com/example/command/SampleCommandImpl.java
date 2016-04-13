package com.example.command;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.model.Payload;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class SampleCommandImpl implements SampleCommand{
	
	final static Logger logger = LoggerFactory.getLogger(SampleCommandImpl.class);

	@HystrixCommand(fallbackMethod = "demoFailover")
	public Payload getOne() {
		int hysterix = (int) Math.ceil(Math.random() * 10);
		if(hysterix % 7 == 0){
			throw new RuntimeException("Ramdom failure");
		}
		Payload payload = new Payload();
		payload.setName("Testy McTesterson");
		payload.setLocation("Kansas City");
		payload.setNotes("I dont like this person.");
		return payload;
	}

	
	public Payload demoFailover() {
		Payload pay = new Payload();
		logger.info("******FAILOVER******");
		return pay;
	}

	@HystrixCommand(fallbackMethod="demoAllFailFallback")
	public List<Payload> demoFail() {
		throw new RuntimeException("Total failure");
	}

	@HystrixCommand(fallbackMethod = "demoAllFailover")
	public List<Payload> getAll() {
		List<Payload> payloads = new ArrayList<Payload>();
		int hysterix = (int) Math.ceil(Math.random() * 100);
		for(int i=0; i< 10; i++){
			if(i >1  && hysterix % i == 0){
				throw new RuntimeException("Semi-Random Exception");
			}
			Payload pay = new Payload();
			pay.setName("Thing"+i);
			pay.setLocation("Here:");
			pay.setNotes("Thank goodness for Thing" +i);
			payloads.add(pay);
		}
		logger.info("----NORMAL----Returning a list of records");
		return payloads;
	}

	public List<Payload> demoAllFailover() {
		List<Payload> payloads = new ArrayList<Payload>();
		logger.info("******FAILOVER FOR ALL******");
		return payloads;
	}

	@HystrixCommand
	public List<Payload> demoAllFailFallback() {
		logger.info("#######Total failure#######");
		throw new RuntimeException("Total failure");
	}

}
