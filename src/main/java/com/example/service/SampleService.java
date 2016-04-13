package com.example.service;

import com.example.model.Payload;
import java.util.List;

public interface SampleService {
	
	Payload getOne();
	
	List<Payload> getAll();

	Payload fail();
	
	List<Payload> failAll();
}
