package com.google.dojo.numbers.server;

import com.google.dojo.numbers.client.GreetingService;
import com.google.dojo.numbers.shared.FieldVerifier;
import com.google.gwt.user.client.Random;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements
		GreetingService {
	
	public static int my_random_number = -1;

	public String greetServer(String input) throws IllegalArgumentException {
		// Verify that the input is valid. 
		if (!FieldVerifier.isValidName(input)) {
			// If the input is not valid, throw an IllegalArgumentException back to
			// the client.
			throw new IllegalArgumentException(
					"Name must be a number betwen 0 and 1000");
		}

		String serverInfo = getServletContext().getServerInfo();
		String userAgent = getThreadLocalRequest().getHeader("User-Agent");

		// Escape data from the client to avoid cross-site script vulnerabilities.
		input = escapeHtml(input);
		userAgent = escapeHtml(userAgent);

		// int my_random_value = Random.nextInt();
		if (my_random_number == -1) {
			my_random_number = (int) (1 + Math.floor (Math.random() * 1000));
		}
		
		return MastermindResult(input, my_random_number);
	}
	
	public static String MastermindResult(String input, int my_random_number) {
		String to_guess = Integer.toString(my_random_number);
		StringBuffer to_guess_buffer = new StringBuffer(to_guess);
		if (input.compareTo(to_guess) == 0) {
			return "SNAG IT! You have guessed correctly !!!"; 
		}

		int len = input.length();
		
		int correct = 0;
		int mismatch = 0;
		if (len == to_guess.length()) {
		    for( int i = 0 ; i < len ; i++ )    {
		    	String Char_observed =  input.substring(i,i+1);
			    if (Char_observed.compareTo(to_guess_buffer.substring(i,i+1)) == 0) {
			    	to_guess_buffer.setCharAt(i, '*');
				    correct = correct + 1;
			    }
			    else {
			    	
			    	if (to_guess_buffer.toString().contains(Char_observed)) {
			    		to_guess_buffer.setCharAt(to_guess_buffer.indexOf(Char_observed) , '*'); 
			    		mismatch = mismatch + 1;
			    	}
			    }
		    }
    		return correct + " correct, " + mismatch + " misplaced";
		} else {
			return "Error: Expected " + to_guess.length() +
					(to_guess.length() > 1 ? " characters" : " character");
		}
	}
	
	/**
	 * Escape an html string. Escaping data received from the client helps to
	 * prevent cross-site script vulnerabilities.
	 * 
	 * @param html the html string to escape
	 * @return the escaped string
	 */
	private String escapeHtml(String html) {
		if (html == null) {
			return null;
		}
		return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;")
				.replaceAll(">", "&gt;");
	}
}
