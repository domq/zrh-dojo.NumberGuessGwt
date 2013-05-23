package com.google.dojo.numbers.client;

import com.google.dojo.numbers.server.GreetingServiceImpl;
import com.google.gwt.junit.client.GWTTestCase;

public class MastermindTest extends GWTTestCase {
	static String victory_message = "SNAG IT! You have guessed correctly !!!";
	@Override
	public String getModuleName() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void testAlwaysTrue() {
		assertTrue(true);
	}
	
	public void testAcceptanceRightGuess() {
		assertEquals(victory_message,
					 GreetingServiceImpl.MastermindResult("555", 555));
	}
	
	public void testAcceptanceWrongCharAll() {
		assertEquals("0 correct, 0 misplaced", GreetingServiceImpl.MastermindResult("123", 555));
		assertEquals("0 correct, 0 misplaced", GreetingServiceImpl.MastermindResult("124", 555));
	}
	
	public void testAcceptanceOneCorrect() {
		assertEquals("1 correct, 0 misplaced", GreetingServiceImpl.MastermindResult("123", 145));
		assertEquals("1 correct, 0 misplaced", GreetingServiceImpl.MastermindResult("124", 629));
		assertEquals("1 correct, 0 misplaced", GreetingServiceImpl.MastermindResult("124", 734));
		assertEquals("1 correct, 0 misplaced", GreetingServiceImpl.MastermindResult("1245", 7346));
		assertEquals("1 correct, 0 misplaced", GreetingServiceImpl.MastermindResult("1245", 7385));
		
	}
	
	public void testAcceptanceTwoCorrect() {
		assertEquals("2 correct, 0 misplaced", GreetingServiceImpl.MastermindResult("123", 125));
		assertEquals("2 correct, 0 misplaced", GreetingServiceImpl.MastermindResult("124", 624));
		assertEquals("2 correct, 0 misplaced", GreetingServiceImpl.MastermindResult("124", 134));
		assertEquals("2 correct, 0 misplaced", GreetingServiceImpl.MastermindResult("1234", 1784));
	}
	
	public void testAcceptanceMismatchLength() {
		assertEquals("Error: Expected 4 characters", 
				GreetingServiceImpl.MastermindResult("123", 1784));		
		assertEquals("Error: Expected 2 characters", GreetingServiceImpl.MastermindResult("123", 17));
		assertEquals("Error: Expected 3 characters", GreetingServiceImpl.MastermindResult("12345", 137));
		assertEquals("Error: Expected 2 characters", GreetingServiceImpl.MastermindResult("1", 17));
		assertEquals("Error: Expected 1 character", GreetingServiceImpl.MastermindResult("11", 1));
	}
	
	public void testAcceptanceOneMisplaced() {
		assertEquals("0 correct, 1 misplaced", GreetingServiceImpl.MastermindResult("567", 145));
		assertEquals("0 correct, 1 misplaced", GreetingServiceImpl.MastermindResult("2226", 9872));
	}
	
	public void testAcceptanceTwoMisplaced() {
		assertEquals("0 correct, 2 misplaced", GreetingServiceImpl.MastermindResult("567", 758));
	}
	
	public void testAcceptanceThreeMisplaced() {
		assertEquals("0 correct, 3 misplaced", GreetingServiceImpl.MastermindResult("567", 756));
	}
	
	public void testAcceptanceOneCorrectTwoMisplaced() {
		assertEquals("1 correct, 2 misplaced", GreetingServiceImpl.MastermindResult("567", 576));
	}
	
	public void testAcceptanceOneMisplacedButAlsoGood() {
		assertEquals("1 correct, 2 misplaced", GreetingServiceImpl.MastermindResult("565", 556));
		assertEquals("1 correct, 0 misplaced", GreetingServiceImpl.MastermindResult("666", 657));
	}
	public void testSingleDigitNumber() {
		assertEquals("0 correct, 0 misplaced", GreetingServiceImpl.MastermindResult("1", 2));
		assertEquals(victory_message,
				GreetingServiceImpl.MastermindResult("1", 1));
	}
	public void testRegression_error() {
		assertEquals("1 correct, 0 misplaced", GreetingServiceImpl.MastermindResult("999", 596));
	}
}
