package com.example.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.model.Payload;

@Service
public class SampleServiceImpl implements SampleService{
	
	final static Logger logger = LoggerFactory.getLogger(SampleServiceImpl.class);
	
	public Payload getOne(){
		int hysterix = (int) Math.ceil(Math.random() * 10);
		if(hysterix % 7 == 0){
			throw new RuntimeException("Ramdom failure" + hysterix);
		}
		Payload payload = new Payload();
		payload.setName("Testy McTesterson");
		payload.setLocation("Kansas City");
		payload.setNotes("I dont like this person.");
		logger.info("----NORMAL----Returning a single record");
		return payload;
	}
	
	public List<Payload> getAll(){
		List<Payload> payloads = new ArrayList<Payload>();
		int hysterix = (int) Math.ceil(Math.random() * 100);
		logger.info("Test" + hysterix);
		for(int i=0; i< 10; i++){
			if(i>1 && hysterix % i == 0){
				throw new RuntimeException("Semi-Random Exception" + i + hysterix);
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

	public List<Payload> failAll() {
		logger.info("#######Total failure#######");
		throw new RuntimeException("Total failure");
	}

	public Payload fail() {
		logger.info("#######Total failure#######");
		throw new RuntimeException("Total failure");
	}

}
