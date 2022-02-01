package com.playermaker.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.playermaker.service.PlayerMakerService;
import com.playermaker.service.PlayerMakerService.PlayerInput;
import com.playermaker.service.PlayerMakerService.PlayerOutput;

import lombok.extern.slf4j.Slf4j;


/**
 * 
 * @author Vinogura Oren
 *
 */

@Validated
@Slf4j
@RestController("/api")
public class PlayerMakerController {

	@Autowired
	PlayerMakerService playerMakerService;
	
	
	@PostMapping("/attended")
	public PlayerOutput getTheMostAttended(@RequestBody @Valid PlayerInput input) throws Exception{
		log.info("PlayerMakerController begin");
		PlayerInput in = new PlayerInput();
		in.setRequiredTopPlayers(input.getRequiredTopPlayers());
		in.setParticipatedPlayers(input.getParticipatedPlayers());
		return playerMakerService.getMostAttended(in);
	}
	
}
