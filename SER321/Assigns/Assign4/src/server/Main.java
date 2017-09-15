package movie.server;

import com.google.gson.JsonObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.*;
import java.io.*;
import java.net.*;

/**
 * Copyright (c) 2017 Kyler Smith,
 * Software Engineering,
 * Arizona State University
 * <p/>
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation version 2
 * of the License.
 * <p/>
 * This program is distributed in the hope that it will be useful,
 * but without any warranty or fitness for a particular purpose.
 * <p/>
 * Please review the GNU General Public License at:
 * http://www.gnu.org/licenses/gpl-2.0.html
 * see also: https://www.gnu.org/licenses/gpl-faq.html
 * so you are aware of the terms and your rights with regard to this software.
 * Or, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301,USA
 * <p/>
 * Purpose: This file is used for implementation of user defined classes MovieDescription.java and MovieLibrary.java
 * <p/>
 * @author Kyler Smith (ksmith91@asu.edu) CIDSE - Software Engineering,
 *                        ASU at the Polytechnic campus
 * @file    Main.java
 * @date    <September, 2017>
 **/

public class Main {

	public static void main(String[] args) throws IOException {

		try {
			
			MovieLibrary ml = new MovieLibrary("movies.json");
			
			BufferedReader stdin = new BufferedReader(
				new InputStreamReader(System.in));
			
			String userInput = stdin.readLine();
			while(!userInput.equals("end")) {

				if(userInput.equals("save")) {
					System.out.println("Writing Movie Library to 'movies.json'.");
					ml.saveToFile("movies.json");
				} else if(userInput.equals("restore")) {
					System.out.println("Restoring from file.");
					ml.restoreFromFile();
				} else if( (userInput.length() > 5) && (userInput.substring(0, 6).equals("search")) ) {
					System.out.println("Search for: " + userInput.substring(7));
					String title = userInput.substring(7);
					title.replaceAll("\\s+","");

					MovieDescription md = searchTitle(title);

					if(md != null)
						ml.add(md); 

				} else {
					System.out.println("Invalid entry, try again.\n" +
						"\tYour options are save, restore, search <title> and end.");
				}
				
				userInput = stdin.readLine();

				if(userInput.equals("end")) {
					System.out.println("Writing Movie Library to 'movieSave.json' and exiting.");
					ml.saveToFile("movieSave.json");
				}	
			}
		} catch(Exception e) {
			System.out.println("An error occurred.");
		}
	}



	public static MovieDescription searchTitle(String s) {
		// http://www.omdbapi.com/?apikey=cdb16783&t=< String to search >
		URL url;
		String tmp, content = "";

		try {
			url = new URL("http://www.omdbapi.com/?apikey=cdb16783&t=" + s);
			URLConnection connection = url.openConnection();
			BufferedReader br = 
				new BufferedReader(new InputStreamReader(
				connection.getInputStream())
			);
			
			while((tmp = br.readLine()) != null)
				content += tmp;
			
			br.close();
			
		} catch(Exception e) {
			return null;
		}	
		
		JsonObject jsonObj = new Gson().fromJson(content, JsonObject.class);
		
		if(jsonObj.has("Error")) {
			System.out.println("Movie not found!");
			return null;
		}
		
		System.out.println("Adding movie: " + jsonObj.get("Title") + " to Movie Library.");
		return new MovieDescription(content);
	}

	
}









