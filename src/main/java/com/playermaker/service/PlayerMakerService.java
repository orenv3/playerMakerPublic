package com.playermaker.service;

import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

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
		
		@Min(1)
		private int requiredTopPlayers;
		
//		@NotEmpty
		private String[][] participatedPlayers;
	}
	
	@Getter
	@Setter
	@ToString
	public static class PlayerOutput{
		private List<String> participatedPlayers;
	}
}
