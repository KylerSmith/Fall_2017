package movie;

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
 * Purpose: <what this file is for>
 * <p/>
 * @author Kyler Smith (ksmith91@asu.edu) CIDSE - Software Engineering,
 *                        ASU at the Polytechnic campus
 * @file    <FILE>
 * @date    <MONTH, YEAR>
 **/

import com.google.gson.Gson;
import java.util.*;
import java.lang.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.*;
import com.google.gson.JsonObject;

import java.io.*;
import java.net.*;


public class test {

	/**
	* Entry point for the program.
	*/
        public static void main(String[] args) throws IOException {

                MovieLibrary ml = new MovieLibrary("movies.json");

                BufferedReader stdin = new BufferedReader(
                        new InputStreamReader(System.in));

                String userInput = stdin.readLine();
                while(!userInput.equals("end")) {                

			MovieDescription md = searchTitle(userInput);
		
			if(md != null)
				ml.add(md);
			
			if(userInput.equals("save")) {
				ml.saveToFile("movies.json");
			}
			
			userInput = stdin.readLine();
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

		// Check to make sure a vaild movie.
		JsonObject jsonObj = new Gson().fromJson(content, JsonObject.class);
		
		// If it's not, return null.
		if(jsonObj.has("Error")) {
			System.out.println("Movie not found!");
			return null;
		}
		
		System.out.println("Adding movie: " + jsonObj.get("Title") + " to Music Library.");
		return new MovieDescription(content);
	}
}












