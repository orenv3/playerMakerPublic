package com.playermaker;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.playermaker.service.PlayerMakerService;
import com.playermaker.service.PlayerMakerService.PlayerInput;
import com.playermaker.service.PlayerMakerService.PlayerOutput;

@RunWith(SpringRunner.class)
@SpringBootTest
class PlayerMakerApplicationTests {

	@Autowired
	PlayerMakerService playerMakerService;
	
	@Test
	void test1() throws Exception {
		
		String[][] test1 = new String[][]{
								{"Sharon", "Shalom" , "Sharon", "Ronaldo"},
		                        {"Sharon", "Shalom" , "Myke", "Ronaldo"},
		                        {"Yechiel", "Sivan" , "Messi", "Ronaldo"},
		                        {"Yechiel", "Assaf" , "Shalom", "Ronaldo"}};
		PlayerInput in = new PlayerInput();
		in.setRequiredTopPlayers(1);
		in.setParticipatedPlayers(test1);
		
		PlayerOutput apiOut = playerMakerService.getMostAttended(in);
		List<String> answer = new ArrayList<String>();
		List<String> compr = new ArrayList<String>();
		answer = apiOut.getParticipatedPlayers();
		
		compr.add("Ronaldo");
		assertArrayEquals(compr.toArray(), answer.toArray());
		
		in.setRequiredTopPlayers(2);
		apiOut = playerMakerService.getMostAttended(in);
		answer =apiOut.getParticipatedPlayers();
		compr.clear();
		compr.add("Ronaldo");
		compr.add("Shalom");
		assertArrayEquals(compr.toArray(), answer.toArray());
		
//		in.setRequiredTopPlayers(2);
//		apiOut = playerMakerService.getMostAttended(in);
//		answer =apiOut.getParticipatedPlayers();
//		compr.clear();
//		compr.add("Ronaldo");
//		compr.add("Sharon");
//		assertArrayEquals(compr.toArray(), answer.toArray());
		
	}
	
	@Test
	void test2() throws Exception {
		
		String[][] test1 = new String[][]{
			{"Sharon", "Shalom" , "Sharon", "Ronaldo"},
			{"Sharon", "Shalom" , "Myke", "Ronaldo"},
			{"Yechiel", "Sivan" , "Messi", "Sivan"},
			{}};
			
			
			PlayerInput in = new PlayerInput();
			in.setRequiredTopPlayers(1);
			in.setParticipatedPlayers(test1);
			
			PlayerOutput apiOut = playerMakerService.getMostAttended(in);
			List<String> answer = new ArrayList<String>();
			List<String> compr = new ArrayList<String>();
			answer = apiOut.getParticipatedPlayers();
			
			compr.add("Sharon");
			assertArrayEquals(compr.toArray(), answer.toArray());
	}

}
