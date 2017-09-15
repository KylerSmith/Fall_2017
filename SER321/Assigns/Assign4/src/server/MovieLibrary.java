package movie.server;

import com.google.gson.GsonBuilder;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.*;
import java.io.*;
import java.util.*;
import java.net.*;
import edu.asu.ser.jsonrpc.common.JsonRpcException;


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
 * Purpose: Class that deals with a collection of MovieDescription class
 * <p/>
 * @author Kyler Smith (ksmith91@asu.edu) CIDSE - Software Engineering,
 *                        ASU at the Polytechnic campus
 * @file    MovieLibrary.java
 * @date    <August, 2017>
 **/

public class MovieLibrary implements MovieLibraryInterface {

	private static int MAX_MOVIES = 100;

/**
* Properties.
*/
	private Object movies[] = new Object[MAX_MOVIES];
	private int movieCount = 0;

/**
* Constructors
*/
	/**
	* Empty constructor.
	*/
	public MovieLibrary(){}


	/**
	* TODO: Implement the following constructor
	*/
	public MovieLibrary(String jsonFilename) {
		
		String json = "", line;

		try {
			FileReader fr = new FileReader(jsonFilename);
			BufferedReader br = new BufferedReader(fr);

			while((line = br.readLine()) != null) 
				json += line;


			br.close();
		
		} catch(Exception e) {
			System.out.println("Exception caught.");
		}
	
		Gson gson = new Gson();	
		
		/** I got this line from the google docs
		* https://google.github.io/gson/apidocs/com/google/gson/reflect/TypeToken.html
		*/
		Type listType = new TypeToken<Map<String, MovieDescription>>(){}.getType();
		
		Map<String, MovieDescription> jsonMap = gson.fromJson(json, listType);
	
		try {	
			for (Map.Entry<String, MovieDescription> entry : jsonMap.entrySet()) {
				add(entry.getValue()); // adds ea. MovieDescription obj to array
				//System.out.println(entry.getValue().toString());
			}
		} catch(Exception e) {
			System.out.println("An exception has occurred in MovieLibrary.java.");
		}
	}


/**
* Methods
*/

	/**
	* @param clip: MovieDescription information to add to collection
	* @return boolean: 1 if added successfully, 0 if not.
	*/
	public boolean add(MovieDescription clip) throws JsonRpcException {
		
		movies[movieCount] = clip;
		movieCount++;	

		return true;
	}


       /**
        * @param title: The string of the title to remove
        * @return boolean: 1 if remove was successful, 0 if not / no title found.
        */
        public boolean remove(String title) throws JsonRpcException {
               
                for(int i = 0; i < movieCount; i++) {
			MovieDescription m = (MovieDescription) movies[i];
                        if(title.equals(m.getTitle())) {
                                for(int j = i; j <= movieCount; j++) {
                                        if(j+1 == movieCount + 1) {
						movieCount--;
                                                return true;
					}
                                        
                                        movies[j] = movies[j+1];
                                }
				movieCount--;
                                return true;
                        }
                }
                return false;
        }

	/**
	* @param title: A String of the MovieDescription to return.
	* @return MovieDescription
	*/
	public MovieDescription get(String title) throws JsonRpcException {

		for(int i = 0; i < movieCount; i++) {
			MovieDescription m = (MovieDescription) movies[i];
			if(title.equals(m.getTitle())) {
				return (MovieDescription) movies[i];
			}
		}
		return null;
	}

	/**
	* @return String[]: Array of Strings, which are all titles in the library. 
	*/
	public String[] getTitles() throws JsonRpcException {
		String[] titles = new String[movieCount];
		
		for(int i = 0; i < movieCount; i++) {
			MovieDescription m = (MovieDescription) movies[i]; 
			titles[i] = m.getTitle();
		}
		
		return titles;
	}

	/**
	* De-serializes the movies.json file into the MovieLibrary obj
	* @return boolean: Whether it was successful or not
	*/
	public boolean restoreFromFile() throws JsonRpcException {
		
		String json = "", line;

                try {
                        FileReader fr = new FileReader("movies.json");
                        BufferedReader br = new BufferedReader(fr);

                        while((line = br.readLine()) != null)
                                json += line;


                        br.close();

                } catch(Exception e) {
                        System.out.println("Exception caught.");
                	return false;
		}

                Gson gson = new Gson();

                Type listType = new TypeToken<Map<String, MovieDescription>>(){}.getType();
                Map<String, MovieDescription> jsonMap = gson.fromJson(json, listType);
		
                for (Map.Entry<String, MovieDescription> entry : jsonMap.entrySet()) {
                	add(entry.getValue());
                }
		
		return true;
	}

	/**
	* Serializes the MovieLibrary obj into JSON and writes to movies.json
	* @return boolean: Whether it was successful or not.
	*/
	public boolean saveToFile(String filename) throws IOException, JsonRpcException {
		MovieDescription tmp = null;
		Map<String, MovieDescription> jsonMap = new HashMap<>();

		for(int i = 0; i < movieCount; i++) {
			tmp = (MovieDescription) movies[i];
			jsonMap.put(tmp.getTitle(), tmp);
		}
		
		String allJson = new GsonBuilder().setPrettyPrinting().create().toJson(jsonMap);
		
		BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
    		bw.write(allJson);
   		bw.close();

		return true;
	}



	
	/**
	*
	*/
        public MovieDescription searchTitle(String s) throws JsonRpcException {
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




