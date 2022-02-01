package com.playermaker.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Stream;

public class PlayerMakerUtils {

	public static List<String> setArrayToList(String[][] participatedPlayers){
		List<String> targetList = new ArrayList<String>();
		
		for (int i = 0; i < participatedPlayers.length; i++) {
			targetList.addAll(Arrays.asList(participatedPlayers[i]));
		}
		return targetList;
	}
	
	
	public static int findTheMax(Map<String,Integer> map) {
		Entry<String,Integer> maxEntry = Collections.max(map.entrySet(), (Entry<String,Integer> e1, Entry<String,Integer> e2) -> e1.getValue()
		        .compareTo(e2.getValue()));
		
		return maxEntry.getValue();
		
	}
	
	
	public static ListOfPlayers[] setPlayersParticipateArray(ListOfPlayers[] PlayersParticipateArray, Map<String,Integer> map) {

		for (Map.Entry<String,Integer> entry : map.entrySet()) {
	    	if(PlayersParticipateArray[entry.getValue()]==null) {
	    		PlayersParticipateArray[entry.getValue()] = new ListOfPlayers();
	    	}
	    	PlayersParticipateArray[entry.getValue()].getListOfPlayers().add(entry.getKey());
	    }
		return PlayersParticipateArray;
	}	

	public static String[] setPlayersParticipateArray(String[] PlayersParticipateArray, Map<String,Integer> map) {
		
		for (Map.Entry<String,Integer> entry : map.entrySet()) {
			PlayersParticipateArray[entry.getValue()] = entry.getKey();
		}
		return PlayersParticipateArray;
	}
	
	
	public static List<String> getPlayersParticipateArray(String[] PlayersParticipateArray, int amount) {
	
		List<String> PlayersParticipateList = new ArrayList<String>();
		int count = 1;
		for (int i = PlayersParticipateArray.length-1; i > 0 &&  amount>=count ; i--) {
			if(!(PlayersParticipateArray[i].isEmpty())){
				count++;
				PlayersParticipateList.add(PlayersParticipateArray[i]);
			}
			
		}
		
		return PlayersParticipateList;
	}
	
	public static List<String> getPlayersParticipateArray(ListOfPlayers[] PlayersParticipateArray, int amount) {

		List<String> PlayersParticipateList = new ArrayList<String>();
		int count = 1;
		for (int i = PlayersParticipateArray.length-1; i > 0 &&  amount>=count ; i--) { 
			if(!(PlayersParticipateArray[i].getListOfPlayers().isEmpty())){
				Stream<String> secList =  PlayersParticipateArray[i].getListOfPlayers().stream().sorted();
				count++;
				PlayersParticipateList.add(secList.findFirst().get());
			}
			
		}
		
		return PlayersParticipateList;
	}
}
