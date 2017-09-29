package assign6.movie.client;

import java.io.*;
import java.util.*;
import java.net.*;

import assign6.movie.server.MovieLibrary;
import assign6.movie.server.MovieDescription;

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
 * Purpose: Client stub
 * <p/>
 * @author Kyler Smith (ksmith91@asu.edu) CIDSE - Software Engineering,
 *                        ASU at the Polytechnic campus
 * @file    MovieLibraryClient.java
 * @date    <September, 2017>
 **/



public class MovieLibraryClient extends Object {

   public static void main(String args[]) {

      String host = "localhost";
      String port = "8080";
      
      try {
         if(args.length >= 2){
            host = args[0];
            port = args[1];
         }
        String url = "http://"+host+":"+port+"/";
	System.out.println("Opening connection to: "+url);

	System.out.print("Enter end or a command: ");

        BufferedReader stdin = new BufferedReader(
        	new InputStreamReader(System.in));

       		MovieLibraryProxy mlp = (MovieLibraryProxy)new 
			MovieLibraryProxy(host, Integer.parseInt(port));

	String inStr = stdin.readLine();
	StringTokenizer st = new StringTokenizer(inStr);
	String open = st.nextToken();

	while(!open.equalsIgnoreCase("end")) {
		//mlp.callMethod(inStr);

		if(open.equalsIgnoreCase("gettitles")) {
			/** Have proxy request and return the titles */
			System.out.println("Get Titles Selected.");

			String[] result = mlp.getTitles();
			System.out.println("=== TITLES ===");
			for(String s : result)
				System.out.println(s);

		} else if(open.equalsIgnoreCase("get")) {
			/** Have proxy get the movie info */
			System.out.println("Get selected.");
			String title = st.nextToken();
			while(st.hasMoreTokens())
				title = title+" "+st.nextToken();

			MovieDescription md = mlp.get(title);
			if(md != null)
				System.out.println("=== "+title+" ===\n"+md.toString());
			
		} else if(open.equalsIgnoreCase("remove")) {
			/** Have proxy remove movie from server */
			System.out.println("Remove selected.");
			String title = st.nextToken();
			while(st.hasMoreTokens())
				title = title+" "+st.nextToken();

			System.out.println("Trying to remove: " + title);

			boolean removed = mlp.remove(title);
			System.out.println("=== REMOVED ===\n" + removed);	

		} else if(open.equalsIgnoreCase("add")) {
			/** Have proxy add a movie */
			System.out.println("Add selected.");
			
		} else {
			System.out.println("Unrecognized command.");
		}
		System.out.print("Enter end or a request: ");
		inStr = stdin.readLine();
		st = new StringTokenizer(inStr);
		open = st.nextToken();
	}
	
      }catch (Exception e) {
         e.printStackTrace();
         System.out.println("Oops, you didn't enter the right stuff");
      }
   }
}
