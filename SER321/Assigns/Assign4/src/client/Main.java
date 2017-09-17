package movie.client;

import java.io.*;
import java.util.*;
import java.net.URL;
import org.json.JSONObject;
import org.json.JSONArray;

import movie.server.MovieLibraryInterface;
import movie.server.MovieLibrary;
import movie.server.MovieDescription;
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
						"'get <title>' and 'end'");
				}
				input = br.readLine();
			}

		} catch(Exception e ) {
			e.printStackTrace();
		}

	}
}
