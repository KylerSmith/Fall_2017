package movie;


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
			
			System.out.println(md.toString());

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












