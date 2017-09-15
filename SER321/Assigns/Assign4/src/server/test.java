package movie.server;

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
 * Purpose: Testing the MovieLibrary and MovieDescription classes.
 * <p/>
 * @author Kyler Smith (ksmith91@asu.edu) CIDSE - Software Engineering,
 *                        ASU at the Polytechnic campus
 * @file    test.java
 * @date    September, 2017
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

import edu.asu.ser.jsonrpc.common.JsonRpcException;
import edu.asu.ser.jsonrpc.server.HttpServer;

public class test {

	/**
	* Entry point for the program.
	*/
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

                                        MovieDescription md = ml.searchTitle(title);

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



}











