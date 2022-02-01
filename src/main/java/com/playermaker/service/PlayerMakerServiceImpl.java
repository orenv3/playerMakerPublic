package com.playermaker.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.playermaker.errorsHandler.GlobalExceptionHandler;
import com.playermaker.utils.ListOfPlayers;
import com.playermaker.utils.PlayerMakerUtils;

@Component
public class PlayerMakerServiceImpl implements PlayerMakerService{

	private final static Log logger = LogFactory.getLog(PlayerMakerServiceImpl.class);
	
	@Autowired
	GlobalExceptionHandler j;
	
	@Override
	public PlayerOutput getMostAttended(PlayerInput in){
		logger.info("Service: PlayerMakerService implimentation excuted");
		PlayerOutput out = new PlayerOutput();
		Map<String,Integer> participated = new HashMap<String,Integer>();
		
		logger.info("Convert the Array to List");
		List<String> GeneralListOfAllPlayers =  PlayerMakerUtils.setArrayToList(in.getParticipatedPlayers()); 
		
		logger.info("Put the list into HashMap");
		GeneralListOfAllPlayers.stream().forEach(e -> {
			if(participated.get(e.toString()) == null) {
				participated.put(e.toString(), 1);
			}else {
				participated.put(e.toString(),1+participated.get(e.toString()));
			}
		});
		
		logger.info("Find the max value in the HashMap in order to find the the length of the target Array");
		int arrayLangth = PlayerMakerUtils.findTheMax(participated);
//		String[] PlayersParticipateArray = new String[arrayLangth+1];
		ListOfPlayers[] PlayersParticipateArray2 = new ListOfPlayers[arrayLangth+1]; 
		
		logger.info("Convert the HashMap into Array");
//		PlayersParticipateArray = PlayerMakerUtils.setPlayersParticipateArray(PlayersParticipateArray, participated);
		PlayersParticipateArray2 = PlayerMakerUtils.setPlayersParticipateArray(PlayersParticipateArray2, participated);
		
		
		out.setParticipatedPlayers(PlayerMakerUtils.getPlayersParticipateArray(PlayersParticipateArray2, in.getRequiredTopPlayers()));
//		out.setParticipatedPlayers(PlayerMakerUtils.getPlayersParticipateArray(PlayersParticipateArray, in.getRequiredTopPlayers()));
		logger.info("The output is: "+out);
		return out;
	}

}
