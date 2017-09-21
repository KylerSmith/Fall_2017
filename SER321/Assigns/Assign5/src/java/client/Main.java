package movie.client;

import java.io.*;
import java.util.*;
import java.net.URL;
import org.json.JSONObject;
import org.json.JSONArray;

import movie.server.MovieLibraryInterface;
import movie.server.MovieLibrary;
import movie.server.MovieDescription;

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
 * Purpose: Driver for client.
 * <p/>
 * @author Kyler Smith (ksmith91@asu.edu) CIDSE - Software Engineering,
 *                        ASU at the Polytechnic campus
 * @file    Main.java
 * @date    <September, 2017>
 **/


public class Main {


	public static void main(String[] args) {
		String host = "localhost";
		String port = "8080";
	      
		try {
			if(args.length >= 2){
			   host = args[0];
			   port = args[1];
			}

			String url = "http://"+host+":"+port+"/";
			
			System.out.println("Opening connection to: "+url);
			 
			MovieLibraryInterface ml = (MovieLibraryInterface)new MovieLibraryProxy(new URL(url));


			BufferedReader br = new BufferedReader(
				new InputStreamReader(System.in));
			String input = br.readLine();
			while(!input.equals("end")) {
				if(input.equals("getTitles"))
					for(String s : ml.getTitles())
						System.out.println(s);
				else if(input.substring(0, 3).equals("get"))
					ml.get(input.substring(4));
				else if(input.substring(0, 6).equals("remove"))
					ml.remove(input.substring(7));
				else if(input.substring(0,3).equals("add"))
					ml.add(input.substring(4));
				else {
					System.out.println("Choices are:" +
						"'getTitles', 'remove <title>'," + 
						"'get <title>', 'add <title>' and 'end'");
				}
				input = br.readLine();
			}

		} catch(Exception e ) {
			e.printStackTrace();
		}

	}
}






