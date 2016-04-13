package com.example.command;

import java.util.List;

import com.example.model.Payload;

public interface SampleCommand {

	Payload getOne();
	
	Payload demoFailover();
	
	List<Payload> demoFail();
	
	List<Payload> getAll();
	
	List<Payload> demoAllFailover();
	
	List<Payload> demoAllFailFallback();
}
