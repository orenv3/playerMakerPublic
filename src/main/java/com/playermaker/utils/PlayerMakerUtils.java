package com.playermaker.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Stream;

/**
 * 
 * @author Vinogura Oren
 *
 */
public class PlayerMakerUtils {

	/**
	 * Method that convert the array String[][] to List
	 * 
	 * @param participatedPlayers String[][] that contains players names 
	 * @return List<String> that contains players names of the 'participatedPlayers'
	 */
	public static List<String> setArrayToList(String[][] participatedPlayers){
		List<String> targetList = new ArrayList<String>();
		
		for (int i = 0; i < participatedPlayers.length; i++) {
			targetList.addAll(Arrays.asList(participatedPlayers[i]));
		}
		return targetList;
	}
	
	
	/**
	 * Method that finds and returns the maximum participation of one or more players
	 * @param map Map that contains players name in the key with value of the amount of their participation
	 * @return maximum amount(int) in the map
	 */
	public static int findTheMax(Map<String,Integer> map) {
		Entry<String,Integer> maxEntry = Collections.max(map.entrySet(), (Entry<String,Integer> e1, Entry<String,Integer> e2) -> e1.getValue()
		        .compareTo(e2.getValue()));
		
		return maxEntry.getValue();
		
	}
	
	/**
	 * Method that sets the keys and values from the map into an array. 
	 * The Value of the map is the index of the array and the key of the map is the value of the array
	 * The method returns ListOfPlayers[] while each index that is not null is an ArrayList of participated players name   
	 * 
	 * @param PlayersParticipateArray ListOfPlayers[] array
	 * @param map Map of players with value of the amount of their participation
	 * @return ListOfPlayers[] while the index of the array is the amount of the player participation and the size is the MAX participated 
	 */
	public static ListOfPlayers[] setPlayersParticipateArray(ListOfPlayers[] PlayersParticipateArray, Map<String,Integer> map) {

		for (Map.Entry<String,Integer> entry : map.entrySet()) {
	    	if(PlayersParticipateArray[entry.getValue()]==null) {
	    		PlayersParticipateArray[entry.getValue()] = new ListOfPlayers();
	    	}
	    	PlayersParticipateArray[entry.getValue()].getListOfPlayers().add(entry.getKey());
	    }
		return PlayersParticipateArray;
	}	

	/**
	 * 
	 * Method that sets the keys and values from the map into an array. 
	 * The Value of the map is the index of the array and the key of the map is the value of the array
	 * The method returns String[] while each index that is not null is a String of participated player name.
	 * If there is more than one name with the same amount of participation then the last value that set in the array is the one to get. 
	 *       
	 * 
	 * @param PlayersParticipateArray String[] array
	 * @param map Map of players with value of the amount of their participation
	 * @return String[] while the index of the array is the amount of the player participation and the size is the MAX participated
	 */
	public static String[] setPlayersParticipateArray(String[] PlayersParticipateArray, Map<String,Integer> map) {
		
		for (Map.Entry<String,Integer> entry : map.entrySet()) {
			PlayersParticipateArray[entry.getValue()] = entry.getKey();
		}
		return PlayersParticipateArray;
	}
	
	
	/**
	 * Method that returns the top N players that participated the most
	 * Runtime O(n)
	 * 
	 * @param PlayersParticipateArray String[] while the index of the array is the amount of the player participation and the value is the player name with the current amount of participation
	 * @param amount The amount of players that participated the most(top N players that participated the most)
	 * @return List<String> of top N players that participated the most
	 */
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
	
	/**
	 * Method that returns the top N players that participated the most
	 * Runtime O(nLog(n))
	 * 
	 * @param PlayersParticipateArray ListOfPlayers[] while the index of the array is the amount of the player participation and the value is a list of players names with the current amount of participation
	 * @param amount The amount of players that participated the most(top N players that participated the most)
	 * @return List<String> of top N players that participated the most
	 */
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
