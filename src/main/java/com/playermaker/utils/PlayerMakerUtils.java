package com.playermaker.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class PlayerMakerUtils {

	public static List<String> setArrayToList(String[][] participatedPlayers){
		List<String> targetList = new ArrayList<String>();
		
		for (int i = 0; i < participatedPlayers.length; i++) {
			targetList.addAll(Arrays.asList(participatedPlayers[i]));
		}
		return targetList;
	}
	
	
	public static int findTheMax(Map<String,Integer> map) {
//		if(map==null)
//		return 0;Exception
		
		Entry<String,Integer> maxEntry = Collections.max(map.entrySet(), (Entry<String,Integer> e1, Entry<String,Integer> e2) -> e1.getValue()
		        .compareTo(e2.getValue()));
		
		return maxEntry.getValue();
		
	}
	
	public static String[] setPlayersParticipateArray(String[] PlayersParticipateArray, Map<String,Integer> map) {
		//Exception
//		Map.Entry<String,Integer> maxEntry = null;
	    for (Map.Entry<String,Integer> entry : map.entrySet()) {
	    	PlayersParticipateArray[entry.getValue()] = entry.getKey();
	    }
		return PlayersParticipateArray;
	}
	
	
	public static List<String> getPlayersParticipateArray(String[] PlayersParticipateArray, int amount) {
		//Exception
		List<String> PlayersParticipateList = new ArrayList<String>();
		int count = 1;
		for (int i = PlayersParticipateArray.length-1; i > 0 &&  amount>=count ; i--) { //PlayersParticipateArray.length-1 amount>=count; i > 0;
			if(!(PlayersParticipateArray[i].isEmpty())){
				
				count++;
				PlayersParticipateList.add(PlayersParticipateArray[i]);
			}
			
		}
		
		return PlayersParticipateList;
	}
}
