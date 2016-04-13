package com.example.service;

import java.util.List;

import com.example.model.Payload;

public interface AggregationService {

	List<Payload> returnAll();
	
	List<Payload> returnAllHystrix();
	
	List<Payload> failAll();
	
	List<Payload> failAllHystrix();
}
