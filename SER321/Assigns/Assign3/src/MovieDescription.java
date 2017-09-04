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
 * Purpose: A class that hold information on movies.
 * <p/>
 * @author Kyler Smith (ksmith91@asu.edu) CIDSE - Software Engineering,
 *                        ASU at the Polytechnic campus
 * @file    MovieDescription.java
 * @date    <August, 2017>
 **/

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import java.io.DataInputStream;
import java.io.DataOutputStream;


public class MovieDescription {	
/**
* Class properties.
*/
	private String Title = "";
	private String Rated = "";
	private String Released = "";
	private String Runtime = "";
	private String Plot = "";
	private String Filename = "";

	private String[] Genre = new String[20];
        private String[] Actors = new String[20];

        private int actorCount = 0;
        private int genreCount = 0;
/**
* Constructors.
*/

	public MovieDescription(){};

	public MovieDescription(String title, String rating, String released,
				String runtime, String plot, String filename,
				String genre, String actor) {
		
		this.Title = title;
		this.Rated = rating;
		this.Released = released;
		this.Runtime = runtime;
		this.Plot = plot;
		this.Filename = filename;
		this.Genre[0] = genre;
		this.Actors[0] = actor;

		actorCount++;
		genreCount++;
	}


	public MovieDescription(String content) {
		JsonObject val = new Gson().fromJson(content, JsonObject.class);
		//System.out.println(val.toString());
	
		this.Title = val.get("Title").toString();
		this.Rated = val.get("Rated").toString();
		this.Released = val.get("Released").toString();
		this.Runtime = val.get("Runtime").toString();
		this.Plot = val.get("Plot").toString();
		
		if(val.has("Filename"))
			this.Filename = val.get("Filename").toString();
		
		this.Actors[0] = val.get("Actors").toString();	
		this.Genre[0] = val.get("Genre").toString();
		
	}


/**
* Getters.
*/
	
	public String getTitle()    { return Title; }
	public String getRating()   { return Rated; }
	public String getReleased() { return Released; }
	public String getRuntime()  { return Runtime; }
	public String getPlot()     { return Plot; }
	public String getFilename() { return Filename; }
	
	public String[] getGenre()    { return Genre; }
	public String[] getActors()   { return Actors; }

/**
* Setters.
*/

	public void setTitle(String title)       { this.Title = title; }
	public void setRating(String rating) 	 { this.Rated = rating; }
	public void setReleased(String released) { this.Released = released; }
	public void setRuntime(String runtime) 	 { this.Runtime = runtime; }
	public void setPlot(String plot) 	 { this.Plot = plot; }
	public void setFilename(String filename) { this.Filename = filename; }
	
	public void setGenre(String genre) { 
		this.Genre[genreCount] = genre; 
		actorCount++;
	}
	public void setActors(String actors) { 
		this.Actors[actorCount] = actors; 
		actorCount++;
	}


/**
* Methods
*/

	/**
	* Returns a String version of the MovieDescription obj
	*/
	public String toString() {

		String allActors = "";
		String allGenres = "";

		for(String s : Genre) {
			if(s == null)
				break;
			allGenres += s;
			allGenres += " ";
		}
		for(String s : Actors) {
			if(s == null)
				break;
			allActors += s;
			allActors += " ";
		}

		return  getTitle() + "\n\t" +
		        getRating() + "\n\t" +
			getReleased() + "\n\t" +
			getRuntime() + "\n\t" +
			getPlot() + "\n\t" +
			getFilename() + "\n\t" +
			allGenres + "\n\t" + allActors;
	}


	/**
	* @return Gson: Gson representation of MovieDescription obj
	*/
	public String toJson() {
		return new GsonBuilder().create().toJson(this);
	}


}







