package com.playermaker.service;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Component
public interface PlayerMakerService {

	PlayerOutput getMostAttended(PlayerInput in) throws Exception;
	
	@Getter
	@Setter
	public static class PlayerInput{
		private int requiredTopPlayers;
		private String[][] participatedPlayers;
	}
	
	@Getter
	@Setter
	@ToString
	public static class PlayerOutput{
		private List<String> participatedPlayers;
	}
}
