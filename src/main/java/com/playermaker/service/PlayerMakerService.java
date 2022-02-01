package com.playermaker.service;

import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 
 * @author Vinogura Oren
 *
 */
@Component
public interface PlayerMakerService {

	/**
	 * Api service: receive an array of players that were participated in all the games and return the top N players that participated the most
	 * @param in PlayerInput Object { int requiredTopPlayers,  String[][] participatedPlayers }

}
	 * @return PlayerOutput Object that contains List<String> of the most participated players
	 * 
	 */
	PlayerOutput getMostAttended(PlayerInput in);
	
	@Getter
	@Setter
	public static class PlayerInput{
		
		@Min(1)
		private int requiredTopPlayers;
		
		@NotEmpty
		private String[][] participatedPlayers;
	}
	
	@Getter
	@Setter
	@ToString
	public static class PlayerOutput{
		private List<String> participatedPlayers;
	}
}
