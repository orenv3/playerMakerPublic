package com.playermaker.utils;

import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ListOfPlayers {

	private ArrayList<String> listOfPlayers;
	public ListOfPlayers() {
		this.listOfPlayers = new ArrayList<String>();
	}
}
