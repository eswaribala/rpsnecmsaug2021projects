package com.nec.circuitbreaker.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nec.circuitbreaker.handlers.CBHandler;
import com.nec.circuitbreaker.models.JWTRequest;

@RestController
public class CBController {
	@Autowired
	private CBHandler cbHandler;

	@PostMapping("/")
	public ResponseEntity<?> getCBAPI(@RequestBody JWTRequest jwtRequest){
            return this.cbHandler.requestHandler(jwtRequest);		
	}
}
