package com.playermaker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.playermaker.service.PlayerMakerService;
import com.playermaker.service.PlayerMakerService.PlayerInput;
import com.playermaker.service.PlayerMakerService.PlayerOutput;

import lombok.extern.slf4j.Slf4j;


//@Validated
@CrossOrigin
@Slf4j
@RestController("/api")
public class PlayerMakerController {

	@Autowired
	PlayerMakerService playerMakerService;
	
	@PostMapping("/attended")
	public PlayerOutput getTheMostAttended(@RequestBody PlayerInput input) throws Exception{
		log.info("PlayerMakerController begin");
		
		PlayerInput in = new PlayerInput();
		in.setRequiredTopPlayers(input.getRequiredTopPlayers());
		in.setParticipatedPlayers(input.getParticipatedPlayers());
		return playerMakerService.getMostAttended(in);
	}
}
